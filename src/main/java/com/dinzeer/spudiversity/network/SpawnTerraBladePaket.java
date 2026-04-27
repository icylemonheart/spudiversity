package com.dinzeer.spudiversity.network;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import twilightforest.entity.projectile.TwilightWandBolt;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;

import java.util.function.Supplier;

public class SpawnTerraBladePaket {

    public SpawnTerraBladePaket() {
        // 空构造函数
    }

    public SpawnTerraBladePaket(FriendlyByteBuf buf) {
        // 从缓冲区读取（如果需要参数的话）
    }
    
    public void encode(FriendlyByteBuf buf) {
        // 写入缓冲区（如果需要参数的话）
    }
    
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                if (ModList.get().isLoaded("botania")) {
                    trySpawnBurst( player);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
    public static void trySpawnBurst(Player player) {
        if (!player.isSpectator() ) {
            ManaBurstEntity burst = getBurst(player, player.getMainHandItem());
            player.level().addFreshEntity(burst);
            player.level().playSound((Player)null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

    }

    public static ManaBurstEntity getBurst(Player player, ItemStack stack) {
        double damage =player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        ManaBurstEntity burst = new ManaBurstEntity(player);
        float motionModifier = 7.0F;
        burst.setColor(2162464);
        burst.setMana(20);
        burst.setStartingMana(100);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(4.0F);
        burst.setGravity(0.0F);
        burst.setDeltaMovement(burst.getDeltaMovement().scale((double)motionModifier));
        burst.setSourceLens(stack.copy());
        return burst;
    }
}