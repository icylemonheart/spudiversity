package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.config.PotatoConfig;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CoalPotatoes extends Item {
    public CoalPotatoes(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationModifier(0.5f)
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        FastMakeEntityUtil.FastEffectByMinute(entity, SpEffect.FurnaceBlessing, 1, PotatoConfig.CONFIG.orePotatoTime.get());
        return super.finishUsingItem(stack, world, entity);
    }
}
