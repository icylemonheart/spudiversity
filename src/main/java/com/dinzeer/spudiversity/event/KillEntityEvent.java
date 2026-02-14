package com.dinzeer.spudiversity.event;

import com.dinzeer.spudiversity.capability.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.PlayerPermanentAbilities;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber
public class KillEntityEvent {
    @SubscribeEvent
    public static void onEntityKilled(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            PlayerKillBossAbilities playerCap= player.getCapability(PlayerKillBossAbilities.CAPABILITY).resolve().get();
            if (event.getEntity() instanceof WitherBoss){
                if (!playerCap.getKillWither()){
                    playerCap.setKillWither(true);
                    System.out.println("凋零击杀");
                }

            }
            if (event.getEntity() instanceof Warden){
                if (!playerCap.getKillWarden()){
                    playerCap.setKillWarden(true);
                    System.out.println("凋零击杀");
                }
            }
            if (event.getEntity() instanceof EnderDragon){
                if (!playerCap.getKillEnderDragon()){
                    playerCap.setKillEnderDragon(true);
                    System.out.println("凋零击杀");
                }
            }
            if (event.getEntity() instanceof ElderGuardian){
                if (!playerCap.getKillOldGrudian()){
                    playerCap.setKillOldGrudian(true);
                    System.out.println("凋零击杀");
                }
            }
        }
    }
}
