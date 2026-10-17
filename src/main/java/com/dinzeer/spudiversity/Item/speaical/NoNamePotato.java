package com.dinzeer.spudiversity.item.speaical;

import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NoNamePotato extends Item {
    public NoNamePotato(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, net.minecraft.world.item.Item.TooltipContext level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        if (Screen.hasAltDown()){
        for (int i = 0; i < 10; i++){
            tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.NONAME_POTATO,i)).withStyle(ChatFormatting.GRAY));
        }
        }else {
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_alt",
                    Component.literal("「Alt」").withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.GRAY));
        }
    }
}
