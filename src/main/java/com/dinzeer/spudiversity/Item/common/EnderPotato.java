package com.dinzeer.spudiversity.Item.common;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;


public class EnderPotato  extends ChorusFruitItem {
    public EnderPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.5f)
                .build()));
    }
    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);

        String[] a=itemstack.getItem().getDescriptionId().split("\\.",3);


//        list.add(returnner(a[2], ChatFormatting.WHITE));
//        list.add(returnner(a[2]+"_1", ChatFormatting.GOLD));
//        list.add(returnner(a[2]+"_2", ChatFormatting.GOLD));


    }
}
