package com.dinzeer.spudiversity.Item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import com.dinzeer.spudiversity.regsitry.SpSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GlowStonePotato extends Item {
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        FastMakeEntityUtil.FastEffectByMinute(entity, SpEffect.RadiantLight.get(), 3, 5);
        return super.finishUsingItem(itemStack, level, entity);
    }

    public GlowStonePotato(Properties p_41383_) {
        super(p_41383_.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationMod(0.2f)
                .alwaysEat()
                .build()));
    }

}
