package com.dinzeer.spudiversity.event.compat.godfox;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import com.dinzeer.spudiversity.regsitry.SpGodFoxPotato;
import com.dinzeer.spudiversity.regsitry.SpStackReg;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BloodFoxEvent {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void BloodPotatoOnHurt(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity player){
        if (CurioCompat.hasItemInCurio(player, SpGodFoxPotato.BloodFoxPotato.get())){
          if (event.getSource().is(DamageTypes.MOB_ATTACK)||event.getSource().is(DamageTypes.PLAYER_ATTACK)){
              FastMakeEntityUtil.FastHurt(event.getEntity(),player, event.getAmount(),DamageTypes.MAGIC);
              event.setAmount(0);
          }
              FastMakeEntityUtil.FastEffect(event.getEntity(), SpEffect.WaveBlood.get(), 1);
          if (event.getEntity().hasEffect(SpEffect.WaveBlood.get())){
              event.setAmount(event.getAmount()*1.4f);
          }
        }
        }
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void BloodPotatoOnKill(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (CurioCompat.hasItemInCurio(player, SpGodFoxPotato.BloodFoxPotato.get())){
                SpStackReg.BloodKingCD.addStacks(player,1);
                if (SpStackReg.BloodKingCD.getCurrentStacks(player)>=10){
                    FastMakeEntityUtil.FastEffect(player, SpEffect.BloodKing.get(), 1);
                    SpStackReg.BloodKingCD.addStacks(player,-10);
                }
            }
        }
    }

}
