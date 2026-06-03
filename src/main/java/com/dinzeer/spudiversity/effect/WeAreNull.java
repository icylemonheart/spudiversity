package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.UUID;

@Mod.EventBusSubscriber
public class WeAreNull extends MobEffect {
    private static final UUID ATTACK_DAMAGE_UUID = UUID.nameUUIDFromBytes("WE_ARE_NULL_ATTACK_DAMAGE".getBytes());
    private static final UUID REACH_DISTANCE_UUID = UUID.nameUUIDFromBytes("WE_ARE_NULL_REACH_DISTANCE".getBytes());
    private static final UUID ATTACK_RANGE_UUID = UUID.nameUUIDFromBytes("WE_ARE_NULL_ATTACK_RANGE".getBytes());

    public WeAreNull() {
        super(MobEffectCategory.BENEFICIAL, Color.BLACK.getBlue());
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        applyAttackDamageBoost(entity);

        if (entity instanceof Player player) {
            maintainMinimalFoodLevel(player);
        }


        if (entity.tickCount % 20 == 0) {
            entity.heal(3.0F);
            spawnHealingParticles(entity);
        }

        applyReachAndAttackRangeBoost(entity);

        if (entity.level().isClientSide && entity.tickCount % 5 == 0) {
            spawnNullParticles(entity);
        }

        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, net.minecraft.world.entity.ai.attributes.AttributeMap attributes, int amplifier) {
        super.removeAttributeModifiers(entity, attributes, amplifier);

        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {
            attackAttr.removeModifier(ATTACK_DAMAGE_UUID);
        }

        AttributeInstance reachAttr = entity.getAttribute(ForgeMod.BLOCK_REACH.get());
        if (reachAttr != null) {
            reachAttr.removeModifier(REACH_DISTANCE_UUID);
        }

        AttributeInstance attackRangeAttr = entity.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (attackRangeAttr != null) {
            attackRangeAttr.removeModifier(ATTACK_RANGE_UUID);
        }


        if (entity instanceof Player player) {
            player.getFoodData().setFoodLevel(20); // 恢复满饱腹度
        }
    }

    private void applyAttackDamageBoost(LivingEntity entity) {
        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {

            attackAttr.removeModifier(ATTACK_DAMAGE_UUID);

            attackAttr.addTransientModifier(new AttributeModifier(
                    ATTACK_DAMAGE_UUID,
                    "We Are Null Attack Boost",
                    0.66D,
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }
    }

    private void maintainMinimalFoodLevel(Player player) {
        FoodData foodData = player.getFoodData();

        if (foodData.getFoodLevel() > 6) {
            foodData.setFoodLevel(6);
        }

        if (foodData.getSaturationLevel() > 0) {
            foodData.setSaturation(0);
        }

        if (foodData.getFoodLevel() <= 0) {
            foodData.setFoodLevel(1);
        }
    }

    private void applyReachAndAttackRangeBoost(LivingEntity entity) {
        AttributeInstance reachAttr = entity.getAttribute(ForgeMod.BLOCK_REACH.get());
        if (reachAttr != null) {
            reachAttr.removeModifier(REACH_DISTANCE_UUID);
            reachAttr.addTransientModifier(new AttributeModifier(
                    REACH_DISTANCE_UUID,
                    "We Are Null Reach Boost",
                    10.0D,
                    AttributeModifier.Operation.ADDITION
            ));
        }

        AttributeInstance attackRangeAttr = entity.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (attackRangeAttr != null) {
            attackRangeAttr.removeModifier(ATTACK_RANGE_UUID);
            attackRangeAttr.addTransientModifier(new AttributeModifier(
                    ATTACK_RANGE_UUID,
                    "We Are Null Attack Range Boost",
                    10.0D,
                    AttributeModifier.Operation.ADDITION
            ));
        }
    }

    private void spawnHealingParticles(LivingEntity entity) {

        if (entity.level().isClientSide) {
            for (int i = 0; i < 5; i++) {
                double x = entity.getX() + (Math.random() - 0.5) * entity.getBbWidth();
                double y = entity.getY() + Math.random() * entity.getBbHeight();
                double z = entity.getZ() + (Math.random() - 0.5) * entity.getBbWidth();

                entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.HEART,
                        x, y, z,
                        0, 0.1, 0);
            }
        }
    }

    private void spawnNullParticles(LivingEntity entity) {
        for (int i = 0; i < 3; i++) {
            double x = entity.getX() + (Math.random() - 0.5) * entity.getBbWidth() * 2;
            double y = entity.getY() + Math.random() * entity.getBbHeight();
            double z = entity.getZ() + (Math.random() - 0.5) * entity.getBbWidth() * 2;

            int particleType = (int) (Math.random() * 3);
            switch (particleType) {
                case 0 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE,
                        x, y, z, 0, 0.05, 0);
                case 1 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.LARGE_SMOKE,
                        x, y, z, 0, 0.05, 0);
                case 2 -> entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.REVERSE_PORTAL,
                        x, y, z,
                        (Math.random() - 0.5) * 0.02,
                        Math.random() * 0.02,
                        (Math.random() - 0.5) * 0.02);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();

        if (source.is(DamageTypes.STARVE) && entity.hasEffect(SpEffect.WeAreNull.get())) {
            event.setCanceled(true);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 持续生效
    }
}