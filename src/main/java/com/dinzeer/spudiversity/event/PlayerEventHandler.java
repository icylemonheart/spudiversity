package com.dinzeer.spudiversity.event;

import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.network.SyncKillBossAbilitiesPacket;
import com.dinzeer.spudiversity.network.SyncPermanentAbilitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class PlayerEventHandler {
    private static final UUID DEFENSE_BOOST_UUID = UUID.nameUUIDFromBytes("DRAGON_POTATO".getBytes());

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncCapabilities(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();
        event.getOriginal().getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(oldStore -> {
            event.getEntity().getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(newStore -> {
                newStore.deserializeNBT(oldStore.serializeNBT());
            });
        });
        event.getOriginal().getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(oldStore -> {
            event.getEntity().getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(newStore -> {
                newStore.deserializeNBT(oldStore.serializeNBT());
            });
        });
        event.getOriginal().invalidateCaps();
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncCapabilities(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncCapabilities(serverPlayer);
        }
    }

    public static void syncCapabilities(ServerPlayer player) {
        player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(cap ->
                SpuNetwork.sendToPlayer(new SyncPermanentAbilitiesPacket(player, cap.serializeNBT()), player)
        );
        player.getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(cap ->
                SpuNetwork.sendToPlayer(new SyncKillBossAbilitiesPacket(player, cap.serializeNBT()), player)
        );
    }

@SubscribeEvent
public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player){
            event.getEntity().getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(Store -> {
                switch (Store.getActivePotato()) {
                    case "fire_dragon_potato", "lightning_dragon_potato", "ice_dragon_potato","for_love" -> applyIceAndFire(player);
                }
            });
        }
}


    private static void applyIceAndFire(Player player) {
        AttributeInstance armorAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (armorAttr != null) {
            armorAttr.removeModifier(DEFENSE_BOOST_UUID);
            armorAttr.addTransientModifier(new AttributeModifier(
                    DEFENSE_BOOST_UUID,
                    "Dragon Potato Boost",
                    0.5D,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            ));
        }
    }
}