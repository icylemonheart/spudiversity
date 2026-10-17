package com.dinzeer.spudiversity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, MODID);
    public static final DeferredHolder<SoundEvent, SoundEvent> anno_happy
            = REGISTRY.register("anno_happy", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, "anno_happy")));
}
