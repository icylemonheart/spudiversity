package com.dinzeer.spudiversity.capability;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = Spudiversity.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(PlayerPermanentAbilities.CAPABILITY, EntityType.PLAYER, (player, ctx) -> player.getData(SpAttachments.PERMANENT_ABILITIES));
        event.registerEntity(PlayerKillBossAbilities.CAPABILITY, EntityType.PLAYER, (player, ctx) -> player.getData(SpAttachments.KILL_BOSS_ABILITIES));
    }
}
