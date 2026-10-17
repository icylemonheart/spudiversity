package com.dinzeer.spudiversity.event;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.network.SyncKillBossAbilitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class KillEntityEvent {
    @SubscribeEvent
    public static void onEntityKilled(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            PlayerKillBossAbilities playerCap = player.getCapability(PlayerKillBossAbilities.CAPABILITY);
            if (playerCap != null) {
                boolean changed = false;
                if (event.getEntity() instanceof WitherBoss) {
                    if (!playerCap.getKillWither()) {
                        playerCap.setKillWither(true);
                        changed = true;
                    }
                }
                if (event.getEntity() instanceof Warden) {
                    if (!playerCap.getKillWarden()) {
                        playerCap.setKillWarden(true);
                        changed = true;
                    }
                }
                if (event.getEntity() instanceof EnderDragon) {
                    if (!playerCap.getKillEnderDragon()) {
                        playerCap.setKillEnderDragon(true);
                        changed = true;
                    }
                }
                if (event.getEntity() instanceof ElderGuardian) {
                    if (!playerCap.getKillOldGrudian()) {
                        playerCap.setKillOldGrudian(true);
                        changed = true;
                    }
                }
                if (changed) {
                    SpuNetwork.sendToPlayer(player, new SyncKillBossAbilitiesPacket(player.getUUID(), playerCap.serializeNBT(player.registryAccess())));
                }
            }
        }
    }
}
