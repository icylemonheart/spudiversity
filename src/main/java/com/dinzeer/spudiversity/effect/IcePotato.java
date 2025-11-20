package com.dinzeer.spudiversity.effect;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber
public class IcePotato extends MobEffect {
    public IcePotato() {
        super(MobEffectCategory.BENEFICIAL, Color.BLUE.getBlue());
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // 应用冰霜行者效果
        if (entity instanceof Player player) {
            applyFrostWalker(player);
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 持续生效
    }

    // 冰霜行者效果实现
    private void applyFrostWalker(Player player) {
        if (!player.onGround() || player.level().isClientSide) {
            return;
        }

        Level level = player.level();
        BlockPos pos = player.blockPosition();
        int radius = 2; // 冰霜行者半径

        // 在玩家周围创建冰面
        for (BlockPos targetPos : BlockPos.betweenClosed(pos.offset(-radius, -1, -radius),
                pos.offset(radius, -1, radius))) {
            if (targetPos.closerToCenterThan(player.position(), radius)) {
                freezeBlock(level, targetPos);
            }
        }
    }

    private void freezeBlock(Level level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        BlockState aboveState = level.getBlockState(pos.above());

        if (blockState.getFluidState().is(Fluids.WATER) && aboveState.isAir()) {
            level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
            level.scheduleTick(pos, Blocks.FROSTED_ICE, 60 + level.random.nextInt(40));
        }
    }



    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(SpEffect.IcePotato.get())) {

            float reducedDamage = event.getAmount() * 0.6f;
            event.setAmount(reducedDamage);
        }
        if (event.getSource().is(DamageTypes.FREEZE)){
            event.setAmount(0);
        }


        if (event.getSource().getEntity() instanceof Player player) {
            if (player.hasEffect(SpEffect.IcePotato.get())) {

                LivingEntity target = event.getEntity();
                FastMakeEntityUtil.FastEffect(target,MobEffects.MOVEMENT_SLOWDOWN,5,5);
                FastMakeEntityUtil.FastEffect(target,MobEffects.WEAKNESS,5,5);
            }
        }
    }

}