package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GlowStonePotato extends Item {
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        FastMakeEntityUtil.FastEffectByMinute(entity, SpEffect.RadiantLight, 3, 5);
        return super.finishUsingItem(itemStack, level, entity);
    }

    public GlowStonePotato(Properties p_41383_) {
        super(p_41383_.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationModifier(0.2f)
                .alwaysEdible()
                .build()));
    }

}
