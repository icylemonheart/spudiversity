package com.dinzeer.spudiversity.client;

import com.dinzeer.spudiversity.regsitry.SpEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(SpEntities.AIR_BLADE.get(), AirBladeRenderer::new);
        EntityRenderers.register(SpEntities.SWORD_ENTITY.get(), SwordRenderer::new);
    }
}