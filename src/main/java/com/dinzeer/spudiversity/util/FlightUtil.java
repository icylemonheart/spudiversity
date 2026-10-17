package com.dinzeer.spudiversity.util;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class FlightUtil {
    private static final Map<UUID, Set<String>> FLIGHT_SOURCES = new HashMap<>();

    public static void grantFlight(Player player, String source) {
        if (player.level().isClientSide || player.isCreative() || player.isSpectator()) {
            return;
        }
        UUID uuid = player.getUUID();
        Set<String> sources = FLIGHT_SOURCES.computeIfAbsent(uuid, k -> new HashSet<>());
        sources.add(source);
        if (!player.getAbilities().mayfly) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    public static void revokeFlight(Player player, String source) {
        if (player.level().isClientSide || player.isCreative() || player.isSpectator()) {
            return;
        }
        UUID uuid = player.getUUID();
        Set<String> sources = FLIGHT_SOURCES.get(uuid);
        if (sources != null) {
            sources.remove(source);
            if (sources.isEmpty()) {
                FLIGHT_SOURCES.remove(uuid);
                if (player.getAbilities().mayfly || player.getAbilities().flying) {
                    player.getAbilities().mayfly = false;
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();
                }
            }
        }
    }

    public static void clear(Player player) {
        FLIGHT_SOURCES.remove(player.getUUID());
    }
}
