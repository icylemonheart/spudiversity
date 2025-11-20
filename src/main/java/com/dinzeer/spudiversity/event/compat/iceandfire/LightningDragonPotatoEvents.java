package com.dinzeer.spudiversity.event.compat.iceandfire;

import com.dinzeer.spudiversity.capability.PlayerPermanentAbilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class LightningDragonPotatoEvents {

    private static final Map<Player, Integer> STATIC_CHARGE = new HashMap<>();
    private static final int MAX_CHARGE = 100;
    private static final UUID SPEED_BOOST_UUID = UUID.nameUUIDFromBytes("LIGHTNING_SPEED".getBytes());
    private static final UUID ATTACK_SPEED_UUID = UUID.nameUUIDFromBytes("LIGHTNING_ATTACK_SPEED".getBytes());

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                if (store.getActivePotato().equals("lightning_dragon_potato")) {
                    // 雷霆：移动时积累静电
                    accumulateStaticCharge(player);

                    // 雷霆：非晴天时获得速度II提升
                    applyWeatherSpeedBoost(player);

                    // 野性难驯：根据生命值提升攻击速度和移动速度
                    applyRageBonus(player);

                    // 显示静电粒子效果
                    if (player.level().isClientSide && player.tickCount % 10 == 0) {
                        spawnStaticParticles(player);
                    }
                }
            });
        }
    }

    private static void accumulateStaticCharge(Player player) {
        // 检查玩家是否在移动
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
            // 非晴天时获得速度II
            if (!player.hasEffect(MobEffects.MOVEMENT_SPEED) ||
                    player.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() < 1) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1, true, false));
            }
        }
    }

    private static void applyRageBonus(Player player) {
        // 计算生命值比例
        float healthRatio = player.getHealth() / player.getMaxHealth();
        // 生命值越低，加成越高（最高25%）
        float bonusMultiplier = 0.25f * (1 - healthRatio);

        // 应用移动速度加成
        AttributeInstance speedAttr = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {
            speedAttr.removeModifier(SPEED_BOOST_UUID);
            if (bonusMultiplier > 0) {
                speedAttr.addTransientModifier(new net.minecraft.world.entity.ai.attributes.AttributeModifier(
                        SPEED_BOOST_UUID,
                        "Lightning Rage Speed",
                        bonusMultiplier,
                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_BASE
                ));
            }
        }

        // 应用攻击速度加成
        AttributeInstance attackSpeedAttr = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED);
        if (attackSpeedAttr != null) {
            attackSpeedAttr.removeModifier(ATTACK_SPEED_UUID);
            if (bonusMultiplier > 0) {
                attackSpeedAttr.addTransientModifier(new net.minecraft.world.entity.ai.attributes.AttributeModifier(
                        ATTACK_SPEED_UUID,
                        "Lightning Rage Attack Speed",
                        bonusMultiplier,
                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_BASE
                ));
            }
        }
    }

    private static void spawnStaticParticles(Player player) {
        int charge = STATIC_CHARGE.getOrDefault(player, 0);
        if (charge > 0) {
            int particleCount = charge / 20; // 根据电荷量决定粒子数量

            for (int i = 0; i < particleCount; i++) {
                double x = player.getX() + (Math.random() - 0.5) * player.getBbWidth();
                double y = player.getY() + Math.random() * player.getBbHeight();
                double z = player.getZ() + (Math.random() - 0.5) * player.getBbWidth();

                player.level().addParticle(net.minecraft.core.particles.ParticleTypes.ELECTRIC_SPARK,
                        x, y, z,
                        (Math.random() - 0.5) * 0.1,
                        Math.random() * 0.1,
                        (Math.random() - 0.5) * 0.1);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                if (store.getActivePotato().equals("lightning_dragon_potato")) {
                    LivingEntity target = event.getEntity();

                    // 风暴：有几率召唤闪电
                    if (Math.random() < getLightningChance(player)) {
                        summonLightning(target);
                    }

                    // 雷霆：静电满层时触发特殊效果
                    int charge = STATIC_CHARGE.getOrDefault(player, 0);
                    if (charge >= MAX_CHARGE) {
                        triggerStaticDischarge(player, target);
                        STATIC_CHARGE.put(player, 0); // 消耗所有静电
                    }
                }
            });
        }
    }

    private static double getLightningChance(Player player) {
        double baseChance = 0.2; // 20%基础几率

        // 在雷暴天气下几率提升
        if (player.level().isThundering()) {
            baseChance = 0.5; // 50%
        }

        // 检查附近是否有避雷针
        BlockPos playerPos = player.blockPosition();
        for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-10, -5, -10),
                playerPos.offset(10, 5, 10))) {
            if (player.level().getBlockState(pos).is(Blocks.LIGHTNING_ROD)) {
                baseChance = 0.5; // 50%
                break;
            }
        }

        return baseChance;
    }

    private static void summonLightning(LivingEntity target) {
        Level level = target.level();
        BlockPos strikePos = target.blockPosition();

        // 召唤闪电
        net.minecraft.world.entity.LightningBolt lightning = new net.minecraft.world.entity.LightningBolt(
                net.minecraft.world.entity.EntityType.LIGHTNING_BOLT, level);
        lightning.setPos(strikePos.getX(), strikePos.getY(), strikePos.getZ());
        lightning.setVisualOnly(false);
        level.addFreshEntity(lightning);

        // 闪电音效
        level.playSound(null, strikePos,
                net.minecraft.sounds.SoundEvents.LIGHTNING_BOLT_THUNDER,
                net.minecraft.sounds.SoundSource.WEATHER, 5.0F, 1.0F);
    }

    private static void triggerStaticDischarge(Player player, LivingEntity target) {
        Level level = player.level();

        // 击退效果
        target.knockback(2.0F,
                player.getX() - target.getX(),
                player.getZ() - target.getZ());

        // 麻痹效果（失明+缓慢）
        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0)); // 3秒失明
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2)); // 3秒缓慢III

        // 范围伤害和效果
        for (LivingEntity nearby : level.getEntitiesOfClass(LivingEntity.class,
                target.getBoundingBox().inflate(3.0))) {
            if (nearby != player && nearby != target) {
                nearby.hurt(player.damageSources().lightningBolt(), 5.0F);
                nearby.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
            }
        }

        // 放电粒子效果
        if (level.isClientSide) {
            for (int i = 0; i < 20; i++) {
                double x = target.getX() + (Math.random() - 0.5) * target.getBbWidth() * 2;
                double y = target.getY() + Math.random() * target.getBbHeight();
                double z = target.getZ() + (Math.random() - 0.5) * target.getBbWidth() * 2;

                level.addParticle(net.minecraft.core.particles.ParticleTypes.ELECTRIC_SPARK,
                        x, y, z,
                        (Math.random() - 0.5) * 0.5,
                        Math.random() * 0.5,
                        (Math.random() - 0.5) * 0.5);
            }
        }

        // 放电音效
        level.playSound(null, target.blockPosition(),
                net.minecraft.sounds.SoundEvents.LIGHTNING_BOLT_IMPACT,
                net.minecraft.sounds.SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}