
package com.dinzeer.spudiversity.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayTotemAnimationPacket {
    private final ItemStack itemStack;

    public PlayTotemAnimationPacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static void encode(PlayTotemAnimationPacket msg, FriendlyByteBuf buf) {
        buf.writeItem(msg.itemStack);
    }

    public static PlayTotemAnimationPacket decode(FriendlyByteBuf buf) {
        return new PlayTotemAnimationPacket(buf.readItem());
    }

    public static void handle(PlayTotemAnimationPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                Minecraft.getInstance().gameRenderer.displayItemActivation(msg.itemStack);
                System.out.println("已发完");
            });

        });
        ctx.get().setPacketHandled(true);
    }

}