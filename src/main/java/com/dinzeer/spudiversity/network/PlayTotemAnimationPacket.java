package com.dinzeer.spudiversity.network;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.client.ClientHooks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayTotemAnimationPacket(ItemStack stack) implements CustomPacketPayload {
    public static final Type<PlayTotemAnimationPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "play_totem_animation"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PlayTotemAnimationPacket> STREAM_CODEC =
            ItemStack.STREAM_CODEC.map(PlayTotemAnimationPacket::new, PlayTotemAnimationPacket::stack);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(PlayTotemAnimationPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (net.neoforged.fml.loading.FMLEnvironment.dist == Dist.CLIENT) {
                ClientHooks.displayItemActivation(payload.stack);
            }
        });
    }
}
