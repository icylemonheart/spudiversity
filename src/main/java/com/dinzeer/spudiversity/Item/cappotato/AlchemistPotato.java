// AlchemistPotatoItem.java
package com.dinzeer.spudiversity.item.cappotato;


import com.dinzeer.spudiversity.capability.impl.AlchemistPotatoCapability;
import com.dinzeer.spudiversity.capability.impl.IAlchemistPotatoCap;
import com.dinzeer.spudiversity.util.CharmPotatoes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlchemistPotato extends Item {
    public AlchemistPotato(Properties properties) {
        super(properties);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new AlchemistPotatoCapability.Provider();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(CharmPotatoes.flavor("item.spudiversity.alchemist_potato.tooltip"));
        if (Screen.hasAltDown()) {
            tooltip.add(CharmPotatoes.effectsHeader());
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_1"));
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_2"));
            // 已注入的药水列表
            LazyOptional<IAlchemistPotatoCap> cap = stack.getCapability(AlchemistPotatoCapability.INSTANCE);
            cap.ifPresent(c -> {
                tooltip.add(Component.translatable("tooltip.alchemist_potato.stored"));
                List<ItemStack> potions = c.getStoredPotions();
                for (int i = 0; i < potions.size(); i++) {
                    ItemStack p = potions.get(i);
                    if (!p.isEmpty()) {
                        tooltip.add(Component.literal(" " + (i + 1) + ". ").append(p.getHoverName()));
                    }
                }
                if (potions.stream().allMatch(ItemStack::isEmpty)) {
                    tooltip.add(Component.translatable("tooltip.alchemist_potato.empty"));
                }
            });
        } else {
            tooltip.add(CharmPotatoes.effectsHeader());
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_1"));
            tooltip.add(CharmPotatoes.body("item.spudiversity.alchemist_potato.tooltip_2"));
            tooltip.add(Component.translatable("item.spudiversity.alchemist_potato.tooltip_alt",
                    Component.literal("「Alt」").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD)).withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}