package com.dinzeer.spudiversity.Item.ex;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GreenPotato extends Item {
    public GreenPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.5f)
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            player.addEffect(new MobEffectInstance(SpEffect.RainBow.get(),  30*20, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,  30*20, 4));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,  30*20, 2));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,  30*20, 2));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,  30*20, 2));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
