package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.Spudiversity;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class SpDataComponents {
    public static final DeferredRegister.DataComponents REGISTRY =
            DeferredRegister.createDataComponents(Spudiversity.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ItemStack>>> ALCHEMIST_POTIONS =
            REGISTRY.registerComponentType("alchemist_potions", builder -> builder
                    .persistent(ItemStack.OPTIONAL_CODEC.listOf())
                    .networkSynchronized(ItemStack.OPTIONAL_LIST_STREAM_CODEC));
}
