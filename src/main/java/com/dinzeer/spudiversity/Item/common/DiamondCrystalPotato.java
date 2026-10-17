package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.config.PotatoConfig;
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
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        FastMakeEntityUtil.FastEffectByMinute(entity, MobEffects.REGENERATION, 3, PotatoConfig.CONFIG.diamondCrystalTime.get());
        FastMakeEntityUtil.FastEffectByMinute(entity, MobEffects.DAMAGE_RESISTANCE, 4, PotatoConfig.CONFIG.diamondCrystalTime.get());
        FastMakeEntityUtil.FastEffectByMinute(entity, MobEffects.DAMAGE_BOOST, 2, PotatoConfig.CONFIG.diamondCrystalTime.get());
        return super.finishUsingItem(itemStack, level, entity);
    }

}
