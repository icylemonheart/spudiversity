package com.dinzeer.spudiversity.Item.compat.slashblade;

import com.dinzeer.spudiversity.Item.common.DiamondPotato;
import com.dinzeer.spudiversity.Item.common.EmeraldPotato;
import com.dinzeer.spudiversity.Item.curios.MysteriousPotato;
import com.dinzeer.spudiversity.regsitry.SpGodFoxPotato;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.world.entity.player.Player;

public class SlashBladeCompat {
    public static void DoslashBlade(SlashBladeEvent.DoSlashEvent event)  {
        if (event.getUser() instanceof Player player){
            MysteriousPotato.summonIs(player);
        }
    }
    public static void BladeStandHit(SlashBladeEvent.BladeStandAttackEvent event)  {
        if (event.getDamageSource().getEntity() instanceof Player player){
           if (player.getMainHandItem().getItem() instanceof DiamondPotato){
               if (event.getSlashBladeState().getTranslationKey().equals("item.godfox.truebloodfox")){
                   player.getMainHandItem().shrink(1);
                   player.addItem(SpGodFoxPotato.BloodFoxPotato.get().getDefaultInstance());
                   event.setCanceled(true);
               }
           }
            if (player.getMainHandItem().getItem() instanceof EmeraldPotato){
                if (event.getSlashBladeState().getTranslationKey().equals("item.godfox.truegodfox")){
                    player.getMainHandItem().shrink(1);
                    player.addItem(SpGodFoxPotato.SkyFoxPotato.get().getDefaultInstance());
                    event.setCanceled(true);
                }
            }
        }
    }

}
