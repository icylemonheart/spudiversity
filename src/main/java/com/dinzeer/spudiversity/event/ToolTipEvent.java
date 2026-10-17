package com.dinzeer.spudiversity.event;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.registry.SpPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

@EventBusSubscriber
public class ToolTipEvent {

    @SubscribeEvent
    public static void ToolTipEvent(ItemTooltipEvent event) {
        // 检查物品是否在黑名单中
        if (isItemInBlacklist(event.getItemStack().getItem())) {
            return; // 如果在黑名单中，直接返回，不添加tooltip
        }

        String[] a = event.getItemStack().getDescriptionId().split("\\.");
        if (a.length < 3){
            return;
        }
        if (a[1].equals(MODID)) {
            event.getToolTip().add(Component.translatable("item." + a[1] + "." + a[2] + ".tooltip").withStyle(ChatFormatting.GOLD));
            for (int i = 1; i < 300; i++) {
                String key = "item." + a[1] + "." + a[2] + ".tooltip" + "_" + i;
                if (I18n.exists(key)) {
                    event.getToolTip().add(Component.translatable(key).withStyle(ChatFormatting.GOLD));
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 检查物品是否在黑名单中
     * @param item 要检查的物品
     * @return 如果在黑名单中返回true，否则返回false
     */
    private static boolean isItemInBlacklist(Item item) {
        for (RegistryEntry<Item, ? extends Item> entry : SpPotato.ToolTipBlackList) {
            if (entry.get() == item) {
                return true;
            }
        }
        return false;
    }
}
