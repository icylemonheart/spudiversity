// 文件：UndeadTransformationEffect.java
package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;

import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;

import java.util.List;

@EventBusSubscriber(modid = "spudiversity")
public class UndeadTransformationEffect extends MobEffect {

    public UndeadTransformationEffect() {

        super(MobEffectCategory.NEUTRAL, 0x8B4513);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {

        updateHit(entity);
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // 每 tick 都执行 applyEffectTick
        return true;
    }



    private static void updateHit(LivingEntity player) {
        if (player.tickCount % 40 == 0) { // 每2秒检测
            List<LivingEntity> entities = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    new AABB(player.getX() - 12, player.getY() - 3, player.getZ() - 12,
                            player.getX() + 12, player.getY() + 3, player.getZ() + 12),
                    e -> e instanceof NeutralMob || e instanceof Enemy
            );

            for (LivingEntity entity : entities) {
                if (entity instanceof NeutralMob neutralMob) {
                    neutralMob.setTarget(player); // 强制中立生物敌对
                }
            }
        }
    }



    // 事件处理器 - 处理生物目标选择
    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity target = event.getNewAboutToBeSetTarget();

        if (target == null) return;


        if (target.hasEffect(SpEffect.UndeadTransformationEffect)) {
            LivingEntity attacker = event.getEntity();


            if (attacker.isInvertedHealAndHarm()) {

                event.setNewAboutToBeSetTarget(null);
            }


        }
    }
}
