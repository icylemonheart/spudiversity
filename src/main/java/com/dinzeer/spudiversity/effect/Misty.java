package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;

@EventBusSubscriber
public class Misty extends MobEffect {
    public Misty() {
        super(MobEffectCategory.BENEFICIAL, 16757497);
    }




    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity newTarget = event.getNewAboutToBeSetTarget();
        if (newTarget == null) return;

        if (newTarget.hasEffect(SpEffect.Misty)) {
            LivingEntity attacker = event.getEntity();
            if (!attacker.getType().is(Tags.EntityTypes.BOSSES)) {
                // 取消本次目标变更，而不是设为 null
                event.setCanceled(true);
            }
        }
    }
}
