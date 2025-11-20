package com.dinzeer.spudiversity.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CurioUtil {
    public static List<ItemStack> getCurioItems(LivingEntity player) {
        List<ItemStack> items = new ArrayList<>();
        if (ModList.get().isLoaded("curios")) {
            LazyOptional<ICuriosItemHandler> opt = CuriosApi.getCuriosInventory(player);
            if (opt.isPresent()) {
                ICuriosItemHandler curios = opt.resolve().get();
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
