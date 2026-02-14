// 文件：CraftingEventHandler.java
package com.dinzeer.spudiversity.event;

import com.dinzeer.spudiversity.regsitry.SpTwPotato;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import twilightforest.init.TFItems;

import java.util.List;
import java.util.Optional;

@Mod.EventBusSubscriber
public class CraftingEventHandler {
    

    
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();
        ItemStack crafted = event.getCrafting();
        CraftingContainer craftingInventory = (CraftingContainer) event.getInventory();
        if(!ModList.get().isLoaded("twilightforest")){return;}
        if (crafted.getItem() == SpTwPotato.FortificationWandPotato.get()) {
            returnDiamonds(player, craftingInventory, TFItems.FORTIFICATION_SCEPTER.get());
            returnDiamonds(player, craftingInventory, TFItems.LICH_TROPHY.get());
        }
        if (crafted.getItem() == SpTwPotato.ZombiePotato.get()) {
            returnDiamonds(player, craftingInventory, TFItems.ZOMBIE_SCEPTER.get());
            returnDiamonds(player, craftingInventory, Items.WITHER_SKELETON_SKULL);
        }
        if (crafted.getItem() == SpTwPotato.DuskPotato.get()) {
            returnDiamonds(player, craftingInventory, TFItems.TWILIGHT_SCEPTER.get());
        }
    }
    
    private static void returnDiamonds(Player player, CraftingContainer craftingInventory, Item item) {

        int ToReturn = 0;
        

        for (int i = 0; i < craftingInventory.getContainerSize(); i++) {
            ItemStack stackInSlot = craftingInventory.getItem(i);
            if (stackInSlot.getItem() == item) {
                ToReturn++;
            }
        }
        

        if (ToReturn > 0) {
            ItemStack diamonds = new ItemStack(item, ToReturn);

            if (!player.addItem(diamonds)) {
                player.drop(diamonds, false);
            }
        }
    }
}