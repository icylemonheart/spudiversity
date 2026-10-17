package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

import java.util.Random;

@EventBusSubscriber
public class SakuraFantasy extends MobEffect {
    private static final ResourceLocation SPEED_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "sakura_speed");
    private static final ResourceLocation JUMP_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "sakura_jump");

    public SakuraFantasy() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        applySpeedBoost(entity, amplifier);

        applyJumpBoost(entity);

        applySlowFalling(entity);

        if (entity.level().isClientSide && entity.tickCount % 5 == 0) {
            spawnSakuraParticles(entity);
        }

        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(net.minecraft.world.entity.ai.attributes.AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);

        AttributeInstance speedAttr = attributes.getInstance(Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {
            speedAttr.removeModifier(SPEED_MODIFIER_ID);
        }

        AttributeInstance jumpAttr = attributes.getInstance(Attributes.JUMP_STRENGTH);
        if (jumpAttr != null) {
            jumpAttr.removeModifier(JUMP_MODIFIER_ID);
        }
    }

    private void applySpeedBoost(LivingEntity entity, int amplifier) {
        AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {

            speedAttr.removeModifier(SPEED_MODIFIER_ID);

            double speedMultiplier = 0.5D * (amplifier + 1);

            speedAttr.addTransientModifier(new AttributeModifier(
                    SPEED_MODIFIER_ID,
                    speedMultiplier,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }
    }

    private void applyJumpBoost(LivingEntity entity) {
        AttributeInstance jumpAttr = entity.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpAttr != null) {

            jumpAttr.removeModifier(JUMP_MODIFIER_ID);

            jumpAttr.addTransientModifier(new AttributeModifier(
                    JUMP_MODIFIER_ID,
                    0.25D,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
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

        if (entity.hasEffect(SpEffect.SakuraFantasy)) {
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
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
