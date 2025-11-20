package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber
public class SakuraFantasy extends MobEffect {
    private static final UUID SPEED_MODIFIER_UUID = UUID.nameUUIDFromBytes("SAKURA_SPEED".getBytes());
    private static final UUID JUMP_MODIFIER_UUID = UUID.nameUUIDFromBytes("SAKURA_JUMP".getBytes());

    public SakuraFantasy() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        applySpeedBoost(entity, amplifier);

        applyJumpBoost(entity);

        applySlowFalling(entity);

        if (entity.level().isClientSide && entity.tickCount % 5 == 0) {
            spawnSakuraParticles(entity);
        }

        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, net.minecraft.world.entity.ai.attributes.AttributeMap attributes, int amplifier) {
        super.removeAttributeModifiers(entity, attributes, amplifier);

        AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {
            speedAttr.removeModifier(SPEED_MODIFIER_UUID);
        }

        AttributeInstance jumpAttr = entity.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpAttr != null) {
            jumpAttr.removeModifier(JUMP_MODIFIER_UUID);
        }
    }

    private void applySpeedBoost(LivingEntity entity, int amplifier) {
        AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {

            speedAttr.removeModifier(SPEED_MODIFIER_UUID);

            double speedMultiplier = 0.5D * (amplifier + 1);

            speedAttr.addTransientModifier(new AttributeModifier(
                    SPEED_MODIFIER_UUID,
                    "Sakura Speed Boost",
                    speedMultiplier,
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }
    }

    private void applyJumpBoost(LivingEntity entity) {
        AttributeInstance jumpAttr = entity.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpAttr != null) {

            jumpAttr.removeModifier(JUMP_MODIFIER_UUID);

            jumpAttr.addTransientModifier(new AttributeModifier(
                    JUMP_MODIFIER_UUID,
                    "Sakura Jump Boost",
                    0.25D,
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }
    }

    private void applySlowFalling(LivingEntity entity) {
        if (!entity.onGround() && entity.getDeltaMovement().y < 0) {
            Vec3 motion = entity.getDeltaMovement();
            double slowFactor = 0.7;

            if (motion.y < -0.1) {
                entity.setDeltaMovement(motion.x, motion.y * slowFactor, motion.z);

                if (entity.level().isClientSide && entity.tickCount % 3 == 0) {
                    spawnSlowFallingParticles(entity);
                }
            }
        }
    }

    private void spawnSakuraParticles(LivingEntity entity) {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            double x = entity.getX() + (random.nextDouble() - 0.5) * entity.getBbWidth() * 2;
            double y = entity.getY() + random.nextDouble() * entity.getBbHeight();
            double z = entity.getZ() + (random.nextDouble() - 0.5) * entity.getBbWidth() * 2;

            entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.END_ROD,
                    x, y, z,
                    (random.nextDouble() - 0.5) * 0.1,
                    random.nextDouble() * 0.05,
                    (random.nextDouble() - 0.5) * 0.1);
        }
    }

    private void spawnSlowFallingParticles(LivingEntity entity) {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            double x = entity.getX() + (random.nextDouble() - 0.5) * entity.getBbWidth();
            double y = entity.getY() + random.nextDouble() * 0.5;
            double z = entity.getZ() + (random.nextDouble() - 0.5) * entity.getBbWidth();

            entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.CLOUD,
                    x, y, z,
                    0, 0.1, 0);
        }
    }

    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(SpEffect.SakuraFantasy.get())) {
            float reducedDistance = event.getDistance() * 0.5F;
            event.setDistance(reducedDistance);
            if (entity.level().isClientSide) {
                spawnLandingParticles(entity);
            }
        }
    }

    private static void spawnLandingParticles(LivingEntity entity) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double x = entity.getX() + (random.nextDouble() - 0.5) * entity.getBbWidth();
            double y = entity.getY() + 0.1;
            double z = entity.getZ() + (random.nextDouble() - 0.5) * entity.getBbWidth();

            entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.CLOUD,
                    x, y, z,
                    (random.nextDouble() - 0.5) * 0.2,
                    0.1,
                    (random.nextDouble() - 0.5) * 0.2);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}