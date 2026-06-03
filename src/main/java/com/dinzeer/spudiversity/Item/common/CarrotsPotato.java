package com.dinzeer.spudiversity.item.common;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class CarrotsPotato extends Item {
    public CarrotsPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()));
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {

            // 添加夜视效果（10分钟=12000ticks，等级1）
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,  15*20, 0));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP,  15*20, 0));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
