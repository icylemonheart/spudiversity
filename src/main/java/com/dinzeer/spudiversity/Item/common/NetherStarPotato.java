package com.dinzeer.spudiversity.item.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NetherStarPotato extends Item {
    public NetherStarPotato(Properties p_41383_) {
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
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 180*20, 0));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 180*20, 0));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 180*20, 0));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 180*20, 0));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
