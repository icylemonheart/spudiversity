package com.dinzeer.spudiversity.item.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EyesPotato extends Item {
    public EyesPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (entity instanceof Player player) {
            // 清空身上所有药水效果
            player.removeAllEffects();
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 12000, 0));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}