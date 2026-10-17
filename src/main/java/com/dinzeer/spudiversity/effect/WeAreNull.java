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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.awt.*;

@EventBusSubscriber
public class WeAreNull extends MobEffect {
    private static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "we_are_null_attack_damage");
    private static final ResourceLocation REACH_DISTANCE_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "we_are_null_reach_distance");
    private static final ResourceLocation ATTACK_RANGE_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "we_are_null_attack_range");

    public WeAreNull() {
        super(MobEffectCategory.BENEFICIAL, Color.BLACK.getBlue());
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
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

        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(net.minecraft.world.entity.ai.attributes.AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);

        AttributeInstance attackAttr = attributes.getInstance(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {
            attackAttr.removeModifier(ATTACK_DAMAGE_ID);
        }

        AttributeInstance reachAttr = attributes.getInstance(Attributes.BLOCK_INTERACTION_RANGE);
        if (reachAttr != null) {
            reachAttr.removeModifier(REACH_DISTANCE_ID);
        }

        AttributeInstance attackRangeAttr = attributes.getInstance(Attributes.ENTITY_INTERACTION_RANGE);
        if (attackRangeAttr != null) {
            attackRangeAttr.removeModifier(ATTACK_RANGE_ID);
        }
    }

    private void applyAttackDamageBoost(LivingEntity entity) {
        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {

            attackAttr.removeModifier(ATTACK_DAMAGE_ID);

            attackAttr.addTransientModifier(new AttributeModifier(
                    ATTACK_DAMAGE_ID,
                    0.66D,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
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
        AttributeInstance reachAttr = entity.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
        if (reachAttr != null) {
            reachAttr.removeModifier(REACH_DISTANCE_ID);
            reachAttr.addTransientModifier(new AttributeModifier(
                    REACH_DISTANCE_ID,
                    10.0D,
                    AttributeModifier.Operation.ADD_VALUE
            ));
        }

        AttributeInstance attackRangeAttr = entity.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
        if (attackRangeAttr != null) {
            attackRangeAttr.removeModifier(ATTACK_RANGE_ID);
            attackRangeAttr.addTransientModifier(new AttributeModifier(
                    ATTACK_RANGE_ID,
                    10.0D,
                    AttributeModifier.Operation.ADD_VALUE
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
    public static void onLivingDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();

        if (source.is(DamageTypes.STARVE) && entity.hasEffect(SpEffect.WeAreNull)) {
            event.setCanceled(true);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true; // 持续生效
    }
}
