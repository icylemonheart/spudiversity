package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.registry.SpEffect;
import com.dinzeer.spudiversity.util.ILevel;
import com.dinzeer.spudiversity.util.Returnner;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class ForHaven extends MobEffect {
    private static int accelerationStage = 1; // 新增：加速阶段计数器
    private static int powerRange = 1; // 新增：加速阶段计数器
    private static int tickCounter = 0; // 新增：计数器，用于记录tick次数
    public ForHaven() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }


@Override
public boolean applyEffectTick(LivingEntity entity, int amplifier) {
    if (!entity.level().isClientSide && entity.level() instanceof ServerLevel serverLevel) {
        if (entity.hasEffect(SpEffect.ForHaven)) {
        long currentTime = serverLevel.getDayTime();
        long smoothFactor = accelerationStage;
        DimensionType dimensionType = serverLevel.dimensionType();
        ((ILevel)(Object)dimensionType).updateTimeOfDay(currentTime + smoothFactor);
        accelerationStage=accelerationStage+powerRange*10;
        }
    }
    return true;
}

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onWorldTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level() instanceof ServerLevel serverLevel) {
            serverLevel.players().forEach(player -> {
                if (player.hasEffect(SpEffect.ForHaven)) {
                    Returnner.setDois(true);
                    tickCounter++;
                    if (tickCounter >= 20) {
                        powerRange = Math.min(powerRange + 1, Integer.MAX_VALUE);
                        tickCounter = 0;
                        if (powerRange % 10 == 0) {
                            player.sendSystemMessage(net.minecraft.network.chat.Component.literal(
                                    String.format("当前加速功率为%s", powerRange)));
                        }
                    }
                    long currentDayTime = serverLevel.getDayTime();
                    serverLevel.setDayTime(currentDayTime + 24000);
                    long currentTime = serverLevel.getDayTime();
                    long smoothFactor = accelerationStage;
                    DimensionType dimensionType = serverLevel.dimensionType();
                    ((ILevel)(Object)dimensionType).updateTimeOfDay(currentTime + smoothFactor);
                    accelerationStage = accelerationStage + powerRange * 10;

                }else {
                    Returnner.setDois(false);
                    powerRange=1;
                }
            });
        }
    }

}
