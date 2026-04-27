package com.dinzeer.spudiversity.Item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DiamondCrystalPotato extends Item {
    public DiamondCrystalPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()));
    }




    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        FastMakeEntityUtil.FastEffect(entity, MobEffects.REGENERATION, 3, 15);
        FastMakeEntityUtil.FastEffect(entity, MobEffects.DAMAGE_RESISTANCE, 4, 10);
        FastMakeEntityUtil.FastEffect(entity, MobEffects.DAMAGE_BOOST, 2, 10);
        return super.finishUsingItem(itemStack, level, entity);
    }

}
