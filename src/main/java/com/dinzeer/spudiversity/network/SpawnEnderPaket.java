package com.dinzeer.spudiversity.network;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import twilightforest.entity.projectile.TwilightWandBolt;

import java.util.function.Supplier;

public class SpawnEnderPaket {

    public SpawnEnderPaket() {
        // 空构造函数
    }

    public SpawnEnderPaket(FriendlyByteBuf buf) {
        // 从缓冲区读取（如果需要参数的话）
    }
    
    public void encode(FriendlyByteBuf buf) {
        // 写入缓冲区（如果需要参数的话）
    }
    
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                if (ModList.get().isLoaded("twilightforest")){
                    int ea=player.getRandom().nextInt(101);
                    if (ea>90){
                        SmallFireball fireball = new SmallFireball(player.level(), player,
                                player.getLookAngle().x * 2.0,
                                player.getLookAngle().y * 2.0,
                                player.getLookAngle().z * 2.0);

                        double eyeHeight = player.getEyeY() - 0.1;
                        fireball.setPos(player.getX(), eyeHeight, player.getZ());
                    }else {
                        player.level().addFreshEntity(new TwilightWandBolt(player.level(), player));
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}