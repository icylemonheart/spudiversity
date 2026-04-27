package com.dinzeer.spudiversity.event.compat.iceandfire;

import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FireDragonPotatoEvents {


    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                if (store.getActivePotato().equals("fire_dragon_potato")) {
                    // 常驻抗火效果
                    if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, true, false));
                    }

                    // 熔岩行者：在熔岩上行走
                    applyLavaWalker(player);

                    // 龙炎庇护：蒸发周围的水
                    evaporateWater(player);
                }
            });
        }
    }

    private static void applyLavaWalker(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();

        // 检查是否站在熔岩上
        BlockPos posBelow = pos.below();
        FluidState fluidBelow = level.getFluidState(posBelow);

        if (fluidBelow.is(Fluids.LAVA)) {
            // 在熔岩上站立时获得急迫效果
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 0, true, false));

            // 创建临时的黑曜石或岩浆块平台
            for (BlockPos walkPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, -1, 1))) {
                if (walkPos.closerToCenterThan(player.position(), 1.5) &&
                        level.getFluidState(walkPos).is(Fluids.LAVA)) {
                    level.setBlockAndUpdate(walkPos, Blocks.MAGMA_BLOCK.defaultBlockState());
                    level.scheduleTick(walkPos, Blocks.MAGMA_BLOCK, 100); // 10秒后恢复
                }
            }
        }
    }

    private static void evaporateWater(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        int radius = 5;

        // 蒸发周围的水
        for (BlockPos waterPos : BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius),
                pos.offset(radius, radius, radius))) {
            if (waterPos.closerToCenterThan(player.position(), radius)) {
                FluidState fluidState = level.getFluidState(waterPos);
                if (fluidState.is(Fluids.WATER)) {
                    level.setBlockAndUpdate(waterPos, Blocks.AIR.defaultBlockState());

                    // 添加蒸汽粒子效果
                    if (level.isClientSide && player.tickCount % 5 == 0) {
                        level.addParticle(net.minecraft.core.particles.ParticleTypes.CLOUD,
                                waterPos.getX() + 0.5, waterPos.getY() + 0.5, waterPos.getZ() + 0.5,
                                0, 0.1, 0);
                    }
                }
            }
        }
    }




}