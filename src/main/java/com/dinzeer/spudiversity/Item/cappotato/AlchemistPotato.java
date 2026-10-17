package com.dinzeer.spudiversity.item.cappotato;


import com.dinzeer.spudiversity.registry.SpDataComponents;
import com.dinzeer.spudiversity.util.CharmPotatoes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class AlchemistPotato extends Item {
    public AlchemistPotato(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(CharmPotatoes.flavor("item.spudiversity.alchemist_potato.tooltip"));
        if (Screen.hasAltDown()) {
            tooltip.add(CharmPotatoes.effectsHeader());
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_1"));
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_2"));
            List<ItemStack> potions = stack.getOrDefault(SpDataComponents.ALCHEMIST_POTIONS.get(), List.of());
            tooltip.add(Component.translatable("tooltip.alchemist_potato.stored"));
            if (potions.isEmpty()) {
                tooltip.add(Component.translatable("tooltip.alchemist_potato.empty"));
            } else {
                for (int i = 0; i < potions.size(); i++) {
                    tooltip.add(Component.literal(" " + (i + 1) + ". ").append(potions.get(i).getHoverName()));
                }
            }
        } else {
            tooltip.add(CharmPotatoes.effectsHeader());
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_1"));
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_2"));
            tooltip.add(Component.translatable("item.spudiversity.alchemist_potato.tooltip_alt",
                    Component.literal("「Alt」").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD)).withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
