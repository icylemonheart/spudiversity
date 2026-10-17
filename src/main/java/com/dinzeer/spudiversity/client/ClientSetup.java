package com.dinzeer.spudiversity.client;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.registry.SpEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(SpEntities.AIR_BLADE.get(), AirBladeRenderer::new);

    }
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skinModel : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(skinModel);
            if (renderer != null) {
                renderer.addLayer(new ForeverRenderingLayer(renderer));
                if (ModList.get().isLoaded("curios")) {
                    renderer.addLayer(new CurioRingRenderLayer(renderer));
                }
            }
        }
    }
}
