package com.dinzeer.spudiversity.event;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.util.FlightUtil;
import com.dinzeer.spudiversity.network.SyncKillBossAbilitiesPacket;
import com.dinzeer.spudiversity.network.SyncPermanentAbilitiesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber
public class PlayerEventHandler {
    private static final ResourceLocation DEFENSE_BOOST_ID = ResourceLocation.fromNamespaceAndPath("spudiversity", "dragon_potato");
    private static final ResourceLocation STAR_BOOST_ID = ResourceLocation.fromNamespaceAndPath("spudiversity", "star_potato");
    private static final Map<Player, Boolean> DRAGON_BOOST_APPLIED = new HashMap<>();
    private static final Map<Player, Boolean> STAR_BOOST_APPLIED = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncCapabilities(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        DRAGON_BOOST_APPLIED.remove(event.getEntity());
        STAR_BOOST_APPLIED.remove(event.getEntity());
        FlightUtil.clear(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        PlayerPermanentAbilities oldPermanent = event.getOriginal().getCapability(PlayerPermanentAbilities.CAPABILITY);
        PlayerPermanentAbilities newPermanent = event.getEntity().getCapability(PlayerPermanentAbilities.CAPABILITY);
        if (oldPermanent != null && newPermanent != null) {
            newPermanent.deserializeNBT(event.getOriginal().registryAccess(), oldPermanent.serializeNBT(event.getOriginal().registryAccess()));
        }

        PlayerKillBossAbilities oldKillBoss = event.getOriginal().getCapability(PlayerKillBossAbilities.CAPABILITY);
        PlayerKillBossAbilities newKillBoss = event.getEntity().getCapability(PlayerKillBossAbilities.CAPABILITY);
        if (oldKillBoss != null && newKillBoss != null) {
            newKillBoss.deserializeNBT(event.getOriginal().registryAccess(), oldKillBoss.serializeNBT(event.getOriginal().registryAccess()));
        }
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
        PlayerPermanentAbilities permanent = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
        if (permanent != null) {
            SpuNetwork.sendToPlayer(player, new SyncPermanentAbilitiesPacket(player.getUUID(), permanent.serializeNBT(player.registryAccess())));
        }
        PlayerKillBossAbilities killBoss = player.getCapability(PlayerKillBossAbilities.CAPABILITY);
        if (killBoss != null) {
            SpuNetwork.sendToPlayer(player, new SyncKillBossAbilitiesPacket(player.getUUID(), killBoss.serializeNBT(player.registryAccess())));
        }
    }

@SubscribeEvent
public static void onPlayerTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player){
            if (player.level().isClientSide) return;
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null) {
                boolean dragonActive = switch (store.getActivePotato()) {
                    case "fire_dragon_potato", "lightning_dragon_potato", "ice_dragon_potato" -> true;
                    default -> false;
                };
                boolean starActive = "for_love".equals(store.getActivePotato());

                Boolean wasDragon = DRAGON_BOOST_APPLIED.get(player);
                if (wasDragon == null || wasDragon != dragonActive) {
                    if (dragonActive) {
                        applyHealthBoost(player, DEFENSE_BOOST_ID);
                    } else {
                        removeHealthBoost(player, DEFENSE_BOOST_ID);
                    }
                    DRAGON_BOOST_APPLIED.put(player, dragonActive);
                }

                Boolean wasStar = STAR_BOOST_APPLIED.get(player);
                if (wasStar == null || wasStar != starActive) {
                    if (starActive) {
                        applyHealthBoost(player, STAR_BOOST_ID);
                    } else {
                        removeHealthBoost(player, STAR_BOOST_ID);
                    }
                    STAR_BOOST_APPLIED.put(player, starActive);
                }
            }
        }
}


    private static void applyHealthBoost(Player player, ResourceLocation id) {
        AttributeInstance armorAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (armorAttr != null) {
            float oldMax = player.getMaxHealth();
            float oldHealth = player.getHealth();
            armorAttr.removeModifier(id);
            armorAttr.addTransientModifier(new AttributeModifier(
                    id,
                    0.5D,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ));
            float newMax = player.getMaxHealth();
            if (oldMax > 0 && oldHealth >= oldMax - 0.01f) {
                player.setHealth(newMax);
            } else if (oldMax > 0) {
                player.setHealth(Math.min(oldHealth * newMax / oldMax, newMax));
            }
        }
    }

    private static void removeHealthBoost(Player player, ResourceLocation id) {
        AttributeInstance armorAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (armorAttr != null) {
            armorAttr.removeModifier(id);
            float reducedMax = player.getMaxHealth();
            if (player.getHealth() > reducedMax) {
                player.setHealth(reducedMax);
            }
        }
    }
}
