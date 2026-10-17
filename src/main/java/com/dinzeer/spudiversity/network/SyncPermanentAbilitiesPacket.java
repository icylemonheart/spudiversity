package com.dinzeer.spudiversity.network;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.client.ClientHooks;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public record SyncPermanentAbilitiesPacket(UUID playerUUID, CompoundTag data) implements CustomPacketPayload {
    public static final Type<SyncPermanentAbilitiesPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "sync_permanent_abilities"));

    private static final StreamCodec<RegistryFriendlyByteBuf, UUID> UUID_CODEC =
            ByteBufCodecs.fromCodecWithRegistries(UUIDUtil.CODEC);
    private static final StreamCodec<RegistryFriendlyByteBuf, CompoundTag> DATA_CODEC =
            ByteBufCodecs.fromCodecWithRegistries(CompoundTag.CODEC);

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncPermanentAbilitiesPacket> STREAM_CODEC =
            StreamCodec.of(
                    (RegistryFriendlyByteBuf buf, SyncPermanentAbilitiesPacket packet) -> {
                        UUID_CODEC.encode(buf, packet.playerUUID);
                        DATA_CODEC.encode(buf, packet.data);
                    },
                    (RegistryFriendlyByteBuf buf) -> new SyncPermanentAbilitiesPacket(UUID_CODEC.decode(buf), DATA_CODEC.decode(buf))
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SyncPermanentAbilitiesPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (net.neoforged.fml.loading.FMLEnvironment.dist == Dist.CLIENT) {
                Player player = ClientHooks.getClientPlayer(payload.playerUUID);
                if (player != null) {
                    PlayerPermanentAbilities cap = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
                    if (cap != null) {
                        cap.deserializeNBT(ClientHooks.getClientRegistryAccess(), payload.data);
                    }
                }
            }
        });
    }
}
