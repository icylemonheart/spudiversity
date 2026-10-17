package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class GlassType  extends MobEffect {
    public GlassType() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(SpEffect.GlassType)) {
            event.setAmount(entity.getHealth() + 1.0F+event.getAmount());
            entity.level().playSound(null, entity.blockPosition(),
                    net.minecraft.sounds.SoundEvents.GLASS_BREAK,
                    entity.getSoundSource(), 1.0F, 1.0F);
        }
    }

}
