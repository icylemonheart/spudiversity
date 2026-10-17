package com.dinzeer.spudiversity.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class SpuNetwork {

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(SpuNetwork::onRegisterPayloads);
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(SyncPermanentAbilitiesPacket.TYPE, SyncPermanentAbilitiesPacket.STREAM_CODEC, SyncPermanentAbilitiesPacket::handle);
        registrar.playToClient(SyncKillBossAbilitiesPacket.TYPE, SyncKillBossAbilitiesPacket.STREAM_CODEC, SyncKillBossAbilitiesPacket::handle);
        registrar.playToClient(PlayTotemAnimationPacket.TYPE, PlayTotemAnimationPacket.STREAM_CODEC, PlayTotemAnimationPacket::handle);
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }
}
