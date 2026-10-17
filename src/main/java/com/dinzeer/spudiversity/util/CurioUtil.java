package com.dinzeer.spudiversity.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CurioUtil {
    public static List<ItemStack> getCurioItems(LivingEntity player) {
        List<ItemStack> items = new ArrayList<>();
        if (ModList.get().isLoaded("curios")) {
            Optional<ICuriosItemHandler> opt = CuriosApi.getCuriosInventory(player);
            if (opt.isPresent()) {
                ICuriosItemHandler curios = opt.get();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.getCurios().entrySet()) {
                    ICurioStacksHandler handler = entry.getValue();
                    for (int i = 0; i < handler.getSlots(); i++) {
                        ItemStack stack = handler.getStacks().getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            items.add(stack);
                        }
                    }
                }
            }
        }
        return items;
    }
}
