package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlazePotato extends Item {
    public BlazePotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationModifier(0.2f)
                .alwaysEdible()
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            FastMakeEntityUtil.FastEffectByMinute(player, SpEffect.BlazeEnd, 1,3);
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
