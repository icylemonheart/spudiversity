package com.dinzeer.spudiversity.event.compat.iceandfire;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber
public class LightningDragonPotatoEvents {

    private static final Map<Player, Integer> STATIC_CHARGE = new HashMap<>();
    private static final Map<Player, Float> LAST_RAGE_BONUS = new HashMap<>();
    private static final int MAX_CHARGE = 100;
    private static final ResourceLocation SPEED_BOOST_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "lightning_speed");
    private static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "lightning_attack_speed");

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        STATIC_CHARGE.remove(player);
        LAST_RAGE_BONUS.remove(player);
    }

    @SubscribeEvent
    public static void onPlayerTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null) {
                if ("lightning_dragon_potato".equals(store.getActivePotato())) {
                    if (!player.level().isClientSide) {
                        accumulateStaticCharge(player);
                        applyWeatherSpeedBoost(player);
                        applyRageBonus(player);
                    }

                    if (player.level().isClientSide && player.tickCount % 10 == 0) {
                        spawnStaticParticles(player);
                    }
                } else {
                    if (!player.level().isClientSide) {
                        removeRageBonus(player);
                    }
                }
            }
        }
    }

    private static void accumulateStaticCharge(Player player) {
        if (player.getDeltaMovement().lengthSqr() > 0.01) {
            int charge = STATIC_CHARGE.getOrDefault(player, 0);
            charge = Math.min(charge + 2, MAX_CHARGE);
            STATIC_CHARGE.put(player, charge);
        }
    }

    private static void applyWeatherSpeedBoost(Player player) {
        Level level = player.level();
        boolean isSunny = level.isDay() && !level.isRaining() && !level.isThundering();

        if (!isSunny) {
            if (!player.hasEffect(MobEffects.MOVEMENT_SPEED)
                    || player.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() < 1) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1, true, false));
            }
        }
    }

    private static void applyRageBonus(Player player) {
        float healthRatio = player.getHealth() / player.getMaxHealth();
        float bonusMultiplier = 0.25f * (1 - healthRatio);

        float lastBonus = LAST_RAGE_BONUS.getOrDefault(player, -1f);
        if (Math.abs(bonusMultiplier - lastBonus) < 0.01f) {
            return;
        }
        LAST_RAGE_BONUS.put(player, bonusMultiplier);

        AttributeInstance speedAttr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {
            speedAttr.removeModifier(SPEED_BOOST_ID);
            if (bonusMultiplier > 0) {
                speedAttr.addTransientModifier(new AttributeModifier(
                        SPEED_BOOST_ID,
                        bonusMultiplier,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ));
            }
        }

        AttributeInstance attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr != null) {
            attackSpeedAttr.removeModifier(ATTACK_SPEED_ID);
            if (bonusMultiplier > 0) {
                attackSpeedAttr.addTransientModifier(new AttributeModifier(
                        ATTACK_SPEED_ID,
                        bonusMultiplier,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ));
            }
        }
    }

    private static void removeRageBonus(Player player) {
        LAST_RAGE_BONUS.remove(player);
        AttributeInstance speedAttr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {
            speedAttr.removeModifier(SPEED_BOOST_ID);
        }
        AttributeInstance attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr != null) {
            attackSpeedAttr.removeModifier(ATTACK_SPEED_ID);
        }
    }

    private static void spawnStaticParticles(Player player) {
        int charge = STATIC_CHARGE.getOrDefault(player, 0);
        if (charge > 0) {
            int particleCount = charge / 20;
            for (int i = 0; i < particleCount; i++) {
                double x = player.getX() + (Math.random() - 0.5) * player.getBbWidth();
                double y = player.getY() + Math.random() * player.getBbHeight();
                double z = player.getZ() + (Math.random() - 0.5) * player.getBbWidth();
                player.level().addParticle(ParticleTypes.ELECTRIC_SPARK,
                        x, y, z,
                        (Math.random() - 0.5) * 0.1,
                        Math.random() * 0.1,
                        (Math.random() - 0.5) * 0.1);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null && "lightning_dragon_potato".equals(store.getActivePotato())) {
                LivingEntity target = event.getEntity();

                if (Math.random() < getLightningChance(player)) {
                    summonLightning(target);
                }

                int charge = STATIC_CHARGE.getOrDefault(player, 0);
                if (charge >= MAX_CHARGE) {
                    triggerStaticDischarge(player, target);
                    STATIC_CHARGE.put(player, 0);
                }
            }
        }
    }

    private static double getLightningChance(Player player) {
        double baseChance = 0.2;

        if (player.level().isThundering()) {
            baseChance = 0.5;
        }

        BlockPos playerPos = player.blockPosition();
        for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-10, -5, -10),
                playerPos.offset(10, 5, 10))) {
            if (player.level().getBlockState(pos).is(Blocks.LIGHTNING_ROD)) {
                baseChance = 0.5;
                break;
            }
        }

        return baseChance;
    }

    private static void summonLightning(LivingEntity target) {
        Level level = target.level();
        BlockPos strikePos = target.blockPosition();

        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightning.setPos(strikePos.getX(), strikePos.getY(), strikePos.getZ());
        lightning.setVisualOnly(false);
        level.addFreshEntity(lightning);

        level.playSound(null, strikePos,
                SoundEvents.LIGHTNING_BOLT_THUNDER,
                SoundSource.WEATHER, 5.0F, 1.0F);
    }

    private static void triggerStaticDischarge(Player player, LivingEntity target) {
        Level level = player.level();

        target.knockback(2.0F,
                player.getX() - target.getX(),
                player.getZ() - target.getZ());

        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));

        for (LivingEntity nearby : level.getEntitiesOfClass(LivingEntity.class,
                target.getBoundingBox().inflate(3.0))) {
            if (nearby != player && nearby != target) {
                nearby.hurt(player.damageSources().lightningBolt(), 5.0F);
                nearby.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
            }
        }

        if (level.isClientSide) {
            for (int i = 0; i < 20; i++) {
                double x = target.getX() + (Math.random() - 0.5) * target.getBbWidth() * 2;
                double y = target.getY() + Math.random() * target.getBbHeight();
                double z = target.getZ() + (Math.random() - 0.5) * target.getBbWidth() * 2;
                level.addParticle(ParticleTypes.ELECTRIC_SPARK,
                        x, y, z,
                        (Math.random() - 0.5) * 0.5,
                        Math.random() * 0.5,
                        (Math.random() - 0.5) * 0.5);
            }
        }

        level.playSound(null, target.blockPosition(),
                SoundEvents.LIGHTNING_BOLT_IMPACT,
                SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
