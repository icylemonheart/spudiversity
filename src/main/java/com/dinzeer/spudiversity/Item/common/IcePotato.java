package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IcePotato extends Item {
    public IcePotato(Properties p_41383_) {
        super(p_41383_.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            FastMakeEntityUtil.FastEffect(player, SpEffect.IcePotato.get(), 1,32);
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
