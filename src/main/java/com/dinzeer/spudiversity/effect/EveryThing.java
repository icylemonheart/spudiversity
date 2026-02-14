package com.dinzeer.spudiversity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class EveryThing extends MobEffect {
    private static final UUID REACH_DISTANCE_UUID = UUID.nameUUIDFromBytes("EVERYTHING_REACH_DISTANCE".getBytes());
    private static final UUID ATTACK_RANGE_UUID = UUID.nameUUIDFromBytes("EVERYTHING_ATTACK_RANGE".getBytes());

    public EveryThing() {
        super(MobEffectCategory.BENEFICIAL, Color.PINK.getRGB());
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {

        if (entity instanceof Player player) {
            applyFlightSpeedBoost(player);
        }

        applyReachAndAttackRangeBoost(entity);


        if (entity.level().isClientSide && entity.tickCount % 10 == 0) {
            spawnEverythingParticles(entity);
        }

        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, net.minecraft.world.entity.ai.attributes.AttributeMap attributes, int amplifier) {
        super.removeAttributeModifiers(entity, attributes, amplifier);


        if (entity instanceof Player player) {
            player.getAbilities().setFlyingSpeed(0.05F);
        }

        AttributeInstance reachAttr = entity.getAttribute(ForgeMod.BLOCK_REACH.get());
        if (reachAttr != null) {
            reachAttr.removeModifier(REACH_DISTANCE_UUID);
        }

        AttributeInstance attackRangeAttr = entity.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (attackRangeAttr != null) {
            attackRangeAttr.removeModifier(ATTACK_RANGE_UUID);
        }
    }

    private void applyFlightSpeedBoost(Player player) {
        player.getAbilities().setFlyingSpeed(0.1F);

        if (player.getAbilities().flying && player.level().isClientSide && player.tickCount % 5 == 0) {
            spawnFlightParticles(player);
        }
    }

    private void applyReachAndAttackRangeBoost(LivingEntity entity) {
        AttributeInstance reachAttr = entity.getAttribute(ForgeMod.BLOCK_REACH.get());
        if (reachAttr != null) {
            reachAttr.removeModifier(REACH_DISTANCE_UUID);
            reachAttr.addTransientModifier(new AttributeModifier(
                    REACH_DISTANCE_UUID,
                    "Everything Reach Boost",
                    3.0D,
                    AttributeModifier.Operation.ADDITION
            ));
        }

        AttributeInstance attackRangeAttr = entity.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (attackRangeAttr != null) {
            attackRangeAttr.removeModifier(ATTACK_RANGE_UUID);
            attackRangeAttr.addTransientModifier(new AttributeModifier(
                    ATTACK_RANGE_UUID,
                    "Everything Attack Range Boost",
                    3.0D,
                    AttributeModifier.Operation.ADDITION
            ));
        }
    }

    private void spawnEverythingParticles(LivingEntity entity) {
        for (int i = 0; i < 5; i++) {
            double x = entity.getX() + (Math.random() - 0.5) * entity.getBbWidth() * 1.5;
            double y = entity.getY() + Math.random() * entity.getBbHeight();
            double z = entity.getZ() + (Math.random() - 0.5) * entity.getBbWidth() * 1.5;

            int colorChoice = (int) (Math.random() * 6);
            switch (colorChoice) {
                case 0 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.ELECTRIC_SPARK,
                        x, y, z, 0, 0.05, 0);
                case 1 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.GLOW,
                        x, y, z, 0, 0.05, 0);
                case 2 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.ENCHANT,
                        x, y, z, 0, 0.05, 0);
                case 3 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.END_ROD,
                        x, y, z, 0, 0.05, 0);
                case 4 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.SCRAPE,
                        x, y, z, 0, 0.05, 0);
                case 5 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.WAX_ON,
                        x, y, z, 0, 0.05, 0);
            }
        }
    }

    private void spawnFlightParticles(Player player) {
        for (int i = 0; i < 3; i++) {
            double x = player.getX() + (Math.random() - 0.5) * player.getBbWidth();
            double y = player.getY() - 0.2;
            double z = player.getZ() + (Math.random() - 0.5) * player.getBbWidth();

            player.level().addParticle(net.minecraft.core.particles.ParticleTypes.CLOUD,
                    x, y, z,
                    (Math.random() - 0.5) * 0.1,
                    Math.random() * 0.1,
                    (Math.random() - 0.5) * 0.1);
        }
    }

    @SubscribeEvent
    public static void EveryThing(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        List<MobEffectInstance> mobEffects = entity.getActiveEffects().stream().toList();
        for (MobEffectInstance mobEffectInstance : mobEffects) {
            if (mobEffectInstance.getEffect() instanceof EveryThing) {
                event.setAmount(event.getAmount() * 0.23f);
                break;
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}