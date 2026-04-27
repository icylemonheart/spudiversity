package com.dinzeer.spudiversity.util;

import net.minecraft.world.entity.player.Player;

public class FlightUtil {
    // 给予飞行能力
    public static void grantFlight(Player player) {
        if (!player.level().isClientSide && !player.isCreative()) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    // 移除飞行能力
    public static void revokeFlight(Player player) {
        if (!player.level().isClientSide && !player.isCreative()) {
            player.getAbilities().mayfly = false;

            // 如果玩家正在飞行，取消飞行状态
            if (player.getAbilities().flying) {
                player.getAbilities().flying = false;
            }

            player.onUpdateAbilities();
        }
    }

}
