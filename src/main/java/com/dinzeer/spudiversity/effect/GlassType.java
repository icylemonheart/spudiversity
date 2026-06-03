package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GlassType  extends MobEffect {
    public GlassType() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(SpEffect.GlassType.get())) {
            event.setAmount(entity.getHealth() + 1.0F+event.getAmount());
            entity.level().playSound(null, entity.blockPosition(),
                    net.minecraft.sounds.SoundEvents.GLASS_BREAK,
                    entity.getSoundSource(), 1.0F, 1.0F);
        }
    }

}
