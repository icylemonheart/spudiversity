// CapabilityAttachHandler.java
package com.dinzeer.spudiversity.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

@Mod.EventBusSubscriber
public class CapabilityAttachHandler {
    public static final ResourceLocation PERMANENT_ABILITIES =
            new ResourceLocation(MODID, "permanent_abilities");

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(PERMANENT_ABILITIES, new PlayerPermanentAbilities.Provider());
        }
    }
}