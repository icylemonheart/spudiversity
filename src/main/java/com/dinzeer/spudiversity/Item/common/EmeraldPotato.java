package com.dinzeer.spudiversity.item.common;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EmeraldPotato extends Item {
    public EmeraldPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
}
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            player.addEffect(new MobEffectInstance(SpEffect.VillageSun, 120*20, 0));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
