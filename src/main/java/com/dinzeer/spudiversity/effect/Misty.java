package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Misty extends MobEffect {
    public Misty() {
        super(MobEffectCategory.BENEFICIAL, 16757497);
    }





    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity newTarget = event.getNewTarget();
        if (newTarget == null) return;

        if (newTarget.hasEffect(SpEffect.Misty.get())) {
            LivingEntity attacker = event.getEntity();
            if (!attacker.getType().is(Tags.EntityTypes.BOSSES)) {
                // 取消本次目标变更，而不是设为 null
                event.setCanceled(true);
            }
        }
    }

//
//    @SubscribeEvent
//    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
//        LivingEntity target = event.getNewTarget();
//
//        if (target == null) return;
//
//
//        if (target.hasEffect(SpEffect.Misty.get())) {
//            LivingEntity attacker = event.getEntity();
//
//
//            if (!attacker.getType().is(Tags.EntityTypes.BOSSES)) {
//
//                event.setNewTarget(null);
//            }
//
//
//        }
//    }
}
