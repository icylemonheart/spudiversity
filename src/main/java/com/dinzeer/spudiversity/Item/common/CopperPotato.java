package com.dinzeer.spudiversity.Item.common;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
// 新增抗性效果所需的导入
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CopperPotato extends Item {
    public CopperPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(10)
                .saturationMod(0.1F)
                .alwaysEat()
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            player.addEffect(new MobEffectInstance(SpEffect.CopperPotato.get(),  600*3, 0));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}