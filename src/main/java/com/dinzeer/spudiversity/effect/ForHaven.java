package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import com.dinzeer.spudiversity.util.ILevel;
import com.dinzeer.spudiversity.util.Returnner;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForHaven extends MobEffect {
    private static int accelerationStage = 1; // 新增：加速阶段计数器
    private static int powerRange = 1; // 新增：加速阶段计数器
    private static int tickCounter = 0; // 新增：计数器，用于记录tick次数
    public ForHaven() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }


@Override
public void applyEffectTick(LivingEntity entity, int amplifier) {
    if (!entity.level().isClientSide && entity.level() instanceof ServerLevel serverLevel) {
        if (entity.hasEffect(SpEffect.ForHaven.get())) {
        long currentTime = serverLevel.getDayTime();
        long smoothFactor = accelerationStage;
        DimensionType dimensionType = serverLevel.dimensionType();
        ((ILevel)(Object)dimensionType).updateTimeOfDay(currentTime + smoothFactor);
        accelerationStage=accelerationStage+powerRange*10;
        }
    }
}

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player.level() instanceof ServerLevel serverLevel) {
            serverLevel.players().forEach(player -> {
                if (player.hasEffect(SpEffect.ForHaven.get())) {
                    Returnner.setDois(true);
                    tickCounter++;
                    if (tickCounter >= 20) {
                        powerRange = Math.min(powerRange + 1, Integer.MAX_VALUE);
                        tickCounter = 0;
                        if (powerRange % 10 == 0) {
                            event.player.sendSystemMessage(net.minecraft.network.chat.Component.literal(
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