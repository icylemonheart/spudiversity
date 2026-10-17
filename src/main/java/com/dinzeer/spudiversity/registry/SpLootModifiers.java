package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.data.loot.CustomAddTemplateModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class SpLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
        DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Spudiversity.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> CUSTOM_ADD_TEMPLATE =
        LOOT_MODIFIER_SERIALIZERS.register("custom_add_template", () -> CustomAddTemplateModifier.CODEC);
}
