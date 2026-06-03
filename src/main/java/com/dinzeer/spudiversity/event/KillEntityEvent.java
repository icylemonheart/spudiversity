package com.dinzeer.spudiversity.event;

import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.network.SyncKillBossAbilitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class KillEntityEvent {
    @SubscribeEvent
    public static void onEntityKilled(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            player.getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(playerCap -> {
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
                    SpuNetwork.sendToPlayer(new SyncKillBossAbilitiesPacket(player, playerCap.serializeNBT()), player);
                }
            });
        }
    }
}
