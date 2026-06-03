package com.dinzeer.spudiversity.network;

import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SyncKillBossAbilitiesPacket {
    private final UUID playerUUID;
    private final CompoundTag data;

    public SyncKillBossAbilitiesPacket(Player player, CompoundTag data) {
        this.playerUUID = player.getUUID();
        this.data = data;
    }

    public SyncKillBossAbilitiesPacket(UUID playerUUID, CompoundTag data) {
        this.playerUUID = playerUUID;
        this.data = data;
    }

    public static void encode(SyncKillBossAbilitiesPacket msg, FriendlyByteBuf buf) {
        buf.writeUUID(msg.playerUUID);
        buf.writeNbt(msg.data);
    }

    public static SyncKillBossAbilitiesPacket decode(FriendlyByteBuf buf) {
        return new SyncKillBossAbilitiesPacket(buf.readUUID(), buf.readNbt());
    }

    public static void handle(SyncKillBossAbilitiesPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                Player player = Minecraft.getInstance().level.getPlayerByUUID(msg.playerUUID);
                if (player != null) {
                    player.getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(cap -> {
                        cap.deserializeNBT(msg.data);
                    });
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
