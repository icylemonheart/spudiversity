package com.dinzeer.spudiversity.event.compat.tw;

import com.dinzeer.spudiversity.registry.SpTwPotato;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import twilightforest.init.TFItems;

@EventBusSubscriber
public class TwCraftingRefundHandler {

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();
        ItemStack crafted = event.getCrafting();
        Container craftingInventory = event.getInventory();
        if (!ModList.get().isLoaded("twilightforest")) {
            return;
        }
        if (crafted.getItem() == SpTwPotato.FortificationWandPotato.get()) {
            returnItems(player, craftingInventory, TFItems.FORTIFICATION_SCEPTER.get());
            returnItems(player, craftingInventory, TFItems.LICH_TROPHY.get());
        }
        if (crafted.getItem() == SpTwPotato.ZombiePotato.get()) {
            returnItems(player, craftingInventory, TFItems.ZOMBIE_SCEPTER.get());
            returnItems(player, craftingInventory, net.minecraft.world.item.Items.WITHER_SKELETON_SKULL);
        }
        if (crafted.getItem() == SpTwPotato.DuskPotato.get()) {
            returnItems(player, craftingInventory, TFItems.TWILIGHT_SCEPTER.get());
        }
    }

    private static void returnItems(Player player, Container craftingInventory, Item item) {
        int toReturn = 0;
        for (int i = 0; i < craftingInventory.getContainerSize(); i++) {
            ItemStack stackInSlot = craftingInventory.getItem(i);
            if (stackInSlot.getItem() == item) {
                toReturn++;
            }
        }
        if (toReturn > 0) {
            ItemStack refund = new ItemStack(item, toReturn);
            if (!player.addItem(refund)) {
                player.drop(refund, false);
            }
        }
    }
}
