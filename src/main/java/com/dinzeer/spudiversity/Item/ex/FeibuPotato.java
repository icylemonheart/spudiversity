package com.dinzeer.spudiversity.Item.ex;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class FeibuPotato extends Item {
    public FeibuPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.5f)
                .build()));
    }
    
    // 添加食用后效果实现
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player) {
            Player player = (Player) entity;
            
            // 给玩家添加效果：抗性提升II(5秒) + 生命恢复II(2秒)
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1)); // 100 ticks = 5秒
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 1));      // 40 ticks = 2秒
            
            // 获取半径5格内所有生物（除玩家）
            AABB area = new AABB(player.getX() - 5, player.getY() - 5, player.getZ() - 5,
                                player.getX() + 5, player.getY() + 5, player.getZ() + 5);
            level.getEntitiesOfClass(LivingEntity.class, area).stream()
                 .filter(e -> e != player) // 排除玩家自身
                 .forEach(e -> {
                     // 添加凝时效果：缓慢V + 虚弱V（5秒）
                     e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
                     e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 4));
                 });

        }
        return super.finishUsingItem(stack, level, entity);
    }


}
