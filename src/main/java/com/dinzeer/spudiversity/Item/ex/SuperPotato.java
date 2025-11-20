package com.dinzeer.spudiversity.Item.ex;

import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SuperPotato extends Item {
    public SuperPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.5f)
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            player.addEffect(new MobEffectInstance(SpEffect.NoMobsBooby.get(),  120*20, 0));
        }
        return super.finishUsingItem(stack, world, entity);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(AdvancedGradientTextHelper.createGradientText(Component.translatable("item.spudiversity.super_potato.tooltip").getString(), ColorPreset.RED_EMBER, AdvancedGradientTextHelper.GradientMode.WAVE));
        tooltipList.add(Component.translatable("item.spudiversity.super_potato.tooltip_1").withStyle(ChatFormatting.GOLD));
        tooltipList.add(Component.translatable("item.spudiversity.super_potato.tooltip_2").withStyle(ChatFormatting.GOLD));
        tooltipList.add(Component.translatable("item.spudiversity.super_potato.tooltip_3").withStyle(ChatFormatting.GOLD));
        tooltipList.add(Component.translatable("item.spudiversity.super_potato.tooltip_4").withStyle(ChatFormatting.GOLD));
        tooltipList.add(Component.translatable("item.spudiversity.super_potato.tooltip_5").withStyle(ChatFormatting.DARK_RED));
    }
}

