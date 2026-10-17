package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
// 新增导入
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import com.dinzeer.spudiversity.registry.SpEffect; // 新增导入

import java.util.List;

@EventBusSubscriber
public class CopperPotato extends MobEffect {
    public CopperPotato() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @SubscribeEvent
    public static void onLightningSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();

        if (entity instanceof LightningBolt) {
            LightningBolt bolt = (LightningBolt) entity;
            AABB area = new AABB(bolt.getX() - 8, bolt.getY() - 8, bolt.getZ() - 8,
                                bolt.getX() + 8, bolt.getY() + 8, bolt.getZ() + 8);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);

            for (LivingEntity target : entities) {
                if (target.hasEffect(SpEffect.CopperPotato)) {
                    LightningBolt newBolt = EntityType.LIGHTNING_BOLT.create(level);
                    if (newBolt != null) {
                        newBolt.moveTo(target.getX(), target.getY(), target.getZ());
                        level.addFreshEntity(newBolt);
                    }
                    bolt.remove(Entity.RemovalReason.DISCARDED);
                    break;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLightningDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().is(DamageTypes.LIGHTNING_BOLT) ) {
            LivingEntity entity = event.getEntity();
            if (entity.hasEffect(SpEffect.CopperPotato)) {
                event.setAmount(event.getAmount() * 0.2f);
            }
        }
    }
}
