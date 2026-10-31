package com.dinzeer.spudiversity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber
public class ServerDisconnect extends MobEffect {
    public ServerDisconnect() {
        super(MobEffectCategory.HARMFUL, 0xAA2222);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Mob mob) {
            mob.setNoAi(true);
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        releaseAi(event);
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        releaseAi(event);
    }

    private static void releaseAi(MobEffectEvent event) {
        if (event.getEffectInstance() != null
                && event.getEffectInstance().getEffect().value() instanceof ServerDisconnect
                && event.getEntity() instanceof Mob mob) {
            mob.setNoAi(false);
        }
    }
}
