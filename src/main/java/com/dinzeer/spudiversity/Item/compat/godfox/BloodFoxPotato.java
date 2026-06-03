package com.dinzeer.spudiversity.item.compat.godfox;

import com.dinzeer.spudiversity.registry.SpGodFoxPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloodFoxPotato extends Item {
    public BloodFoxPotato(Properties properties) {
        super(properties);
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        for (int i = 0; i < 7; i++) {
            tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato, i)).withStyle(ChatFormatting.DARK_RED));
        }

    }
}
