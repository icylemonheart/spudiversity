package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class RadiantLight extends MobEffect {
    public RadiantLight() {
        super(MobEffectCategory.BENEFICIAL, 0xFFDD88); // 暖金色
    }


    // 记录每个玩家当前光源所在位置
    private static final Map<UUID, BlockPos> LIGHT_POSITIONS = new HashMap<>();

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) return;

        MobEffectInstance effect = entity.getEffect(SpEffect.RadiantLight.get());
        if (effect != null) {

                updateLight(entity, effect.getAmplifier());

        } else {


                removeLight(entity);

        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() != null &&
                event.getEffectInstance().getEffect() == SpEffect.RadiantLight.get()) {
            removeLight(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEffectInstance() != null &&
                event.getEffectInstance().getEffect() == SpEffect.RadiantLight.get()) {
            removeLight(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        removeLight(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        removeLight(event.getEntity());
    }
    private static void updateLight(LivingEntity player, int amplifier) {
        ServerLevel level = (ServerLevel) player.level();
        BlockPos playerPos = player.blockPosition();

        int lightLevel = Math.min(15, 3 + amplifier * 2);

        BlockPos oldPos = LIGHT_POSITIONS.get(player.getUUID());
        if (oldPos != null && !oldPos.equals(playerPos)) {
            removeLightAt(level, oldPos);
        }

        BlockState state = level.getBlockState(playerPos);
        if (state.canBeReplaced() || state.is(Blocks.LIGHT)) {
            BlockState lightState = Blocks.LIGHT.defaultBlockState()
                    .setValue(LightBlock.LEVEL, lightLevel);
            level.setBlock(playerPos, lightState, 3);
            LIGHT_POSITIONS.put(player.getUUID(), playerPos);
        } else {
            BlockPos headPos = playerPos.above();
            BlockState headState = level.getBlockState(headPos);
            if (headState.canBeReplaced() || headState.is(Blocks.LIGHT)) {
                BlockState lightState = Blocks.LIGHT.defaultBlockState()
                        .setValue(LightBlock.LEVEL, lightLevel);
                level.setBlock(headPos, lightState, 3);
                LIGHT_POSITIONS.put(player.getUUID(), headPos);
            }
        }
    }

    private static void removeLight(LivingEntity player) {
        ServerLevel level = (ServerLevel) player.level();
        BlockPos pos = LIGHT_POSITIONS.remove(player.getUUID());
        if (pos != null) {
            removeLightAt(level, pos);
        }
    }

    private static void removeLightAt(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.LIGHT)) {
            level.removeBlock(pos, false);
        }
    }
}