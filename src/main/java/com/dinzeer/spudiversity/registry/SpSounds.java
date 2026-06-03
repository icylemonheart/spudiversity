package com.dinzeer.spudiversity.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
    public static final RegistryObject<SoundEvent> anno_happy
            = REGISTRY.register("anno_happy", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "anno_happy")));
}
