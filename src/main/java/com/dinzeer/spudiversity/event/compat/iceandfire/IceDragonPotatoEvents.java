package com.dinzeer.spudiversity.event.compat.iceandfire;

import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.Optional;

@EventBusSubscriber
public class IceDragonPotatoEvents {

    @SubscribeEvent
    public static void onPlayerTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide) {
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null && "ice_dragon_potato".equals(store.getActivePotato())) {
                applyFrostWalker(player);

                if (player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                }

                if (player.isInPowderSnow) {
                    player.setIsInPowderSnow(false);
                    player.clearFire();
                }
            }
        }
    }

    private static void applyFrostWalker(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        int radius = 2;

        for (BlockPos targetPos : BlockPos.betweenClosed(pos.offset(-radius, -1, -radius),
                pos.offset(radius, -1, radius))) {
            if (targetPos.closerToCenterThan(player.position(), radius)) {
                freezeBlock(level, targetPos);
            }
        }

        BlockState blockBelow = level.getBlockState(player.blockPosition().below());
        if (blockBelow.is(Blocks.MAGMA_BLOCK)) {
            player.clearFire();
            if (player.hurtMarked) {
                player.hurtMarked = false;
            }
        }
    }

    private static void freezeBlock(Level level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        BlockState aboveState = level.getBlockState(pos.above());

        if (blockState.getFluidState().is(Fluids.WATER) && aboveState.isAir()) {
            level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
            level.scheduleTick(pos, Blocks.FROSTED_ICE, 60 + level.random.nextInt(40));
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null && "ice_dragon_potato".equals(store.getActivePotato())) {
                LivingEntity target = event.getEntity();

                if (!(target instanceof Enemy) || !target.getType().getCategory().isFriendly()) {
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 255));
                    target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 255));
                }

                target.clearFire();

                if (ModList.get().isLoaded("iceandfire")) {
                    Optional<Holder.Reference<MobEffect>> frozen = BuiltInRegistries.MOB_EFFECT
                            .getHolder(ResourceLocation.fromNamespaceAndPath("iceandfire", "frozen"));
                    frozen.ifPresent(holder -> target.addEffect(new MobEffectInstance(holder, 300)));
                }

                if (target.isInWaterRainOrBubble()) {
                    event.setAmount(event.getAmount() * 1.15f);
                }
            }
        }
    }
}
