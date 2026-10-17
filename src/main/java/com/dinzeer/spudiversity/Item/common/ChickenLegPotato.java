package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChickenLegPotato extends Item {
    public ChickenLegPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        FastMakeEntityUtil.FastEffect(entity, MobEffects.SATURATION, 1, 5);
        return super.finishUsingItem(stack, world, entity);
    }
}
