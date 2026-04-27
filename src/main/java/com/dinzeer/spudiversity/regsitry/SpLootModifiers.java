package com.dinzeer.spudiversity.regsitry;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.data.loot.CustomAddTemplateModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = 
        DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Spudiversity.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> CUSTOM_ADD_TEMPLATE = 
        LOOT_MODIFIER_SERIALIZERS.register("custom_add_template", () -> CustomAddTemplateModifier.CODEC.get());

    public static void register(IEventBus modEventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
    }
}
