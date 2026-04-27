// AlchemistPotatoItem.java
package com.dinzeer.spudiversity.Item.cappotato;


import com.dinzeer.spudiversity.capability.impl.AlchemistPotatoCapability;
import com.dinzeer.spudiversity.capability.impl.IAlchemistPotatoCap;
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
        if (Screen.hasAltDown()) {
            LazyOptional<IAlchemistPotatoCap> cap = stack.getCapability(AlchemistPotatoCapability.INSTANCE);
            cap.ifPresent(c -> {
                List<ItemStack> potions = c.getStoredPotions();
                tooltip.add(Component.translatable("tooltip.alchemist_potato.stored"));
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
            tooltip.add(Component.translatable("item.spudiversity.alchemist_potato.tooltip_alt",
                    Component.literal("「Alt」").withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.GREEN));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }
}