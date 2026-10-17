package com.dinzeer.spudiversity.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public final class ClientHooks {
    private ClientHooks() {
    }

    public static Player getLocalPlayer() {
        return Minecraft.getInstance().player;
    }

    public static Player getClientPlayer(UUID uuid) {
        if (Minecraft.getInstance().level == null) {
            return null;
        }
        return Minecraft.getInstance().level.getPlayerByUUID(uuid);
    }

    public static HolderLookup.Provider getClientRegistryAccess() {
        if (Minecraft.getInstance().level == null) {
            return null;
        }
        return Minecraft.getInstance().level.registryAccess();
    }

    public static boolean isLocalPlayer(Player player) {
        return Minecraft.getInstance().player == player;
    }

    public static boolean isJumpKeyDown() {
        return Minecraft.getInstance().options.keyJump.isDown();
    }

    public static void displayItemActivation(ItemStack stack) {
        Minecraft.getInstance().gameRenderer.displayItemActivation(stack);
    }
}
