package com.dinzeer.spudiversity.event.compat.iceandfire;

import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class FireDragonPotatoEvents {

    @SubscribeEvent
    public static void onPlayerTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide) {
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null && "fire_dragon_potato".equals(store.getActivePotato())) {
                if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, true, false));
                }
                applyLavaWalker(player);
                evaporateWater(player);
            }
        }
    }

    private static void applyLavaWalker(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        FluidState fluidBelow = level.getFluidState(pos.below());

        if (fluidBelow.is(Fluids.LAVA)) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 0, true, false));
            for (BlockPos walkPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, -1, 1))) {
                if (walkPos.closerToCenterThan(player.position(), 1.5)
                        && level.getFluidState(walkPos).is(Fluids.LAVA)) {
                    level.setBlockAndUpdate(walkPos, Blocks.MAGMA_BLOCK.defaultBlockState());
                    level.scheduleTick(walkPos, Blocks.MAGMA_BLOCK, 100);
                }
            }
        }
    }

    private static void evaporateWater(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        int radius = 5;

        for (BlockPos waterPos : BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius),
                pos.offset(radius, radius, radius))) {
            if (waterPos.closerToCenterThan(player.position(), radius)) {
                if (level.getFluidState(waterPos).is(Fluids.WATER)) {
                    level.setBlockAndUpdate(waterPos, Blocks.AIR.defaultBlockState());
                }
            }
        }
    }
}
