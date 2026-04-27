package com.dinzeer.spudiversity.event.compat.iceandfire;

import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.github.alexthe666.iceandfire.entity.props.EntityDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IceDragonPotatoEvents {

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                if (store.getActivePotato().equals("ice_dragon_potato")) {
                    // 冰霜行者效果
                    applyFrostWalker(player);

                    // 免疫缓慢效果
                    if (player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                    }

                    // 细雪中自由行走且不受伤害
                    if (player.isInPowderSnow) {
                        player.setIsInPowderSnow(false);
                        player.clearFire();
                    }
                }
            });
        }
    }

    private static void applyFrostWalker(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        int radius = 2;

        // 在玩家周围创建冰面
        for (BlockPos targetPos : BlockPos.betweenClosed(pos.offset(-radius, -1, -radius),
                pos.offset(radius, -1, radius))) {
            if (targetPos.closerToCenterThan(player.position(), radius)) {
                freezeBlock(level, targetPos);
            }
        }

        // 在岩浆块上行走保护
        BlockPos posBelow = player.blockPosition().below();
        BlockState blockBelow = level.getBlockState(posBelow);
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
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                if (store.getActivePotato().equals("ice_dragon_potato")) {
                    LivingEntity target = event.getEntity();

                    if (!(target instanceof Enemy) || !target.getType().getCategory().isFriendly()) {
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 255));
                        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 255));
                    }



                        target.setSecondsOnFire(0);
                    if (ModList.get().isLoaded("iceandfire")){
                    EntityDataProvider.getCapability(target).ifPresent((data) -> {
                        data.frozenData.setFrozen(target, 300);
                    });
                    }

                    if (target.isInWaterRainOrBubble()) {
                        event.setAmount(event.getAmount() * 1.15f);
                    }
                }
            });
        }
    }
}