package com.dinzeer.spudiversity.event.compat.godfox;


import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpEffect;
import com.dinzeer.spudiversity.registry.SpGodFoxPotato;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SkyFoxPotatoEvent {



    @SubscribeEvent
    public static void SkyPotato(LivingHealEvent s){
        if (CurioCompat.hasItemInCurio(s.getEntity(), SpGodFoxPotato.SkyFoxPotato.get())){
            if (s.getEntity() instanceof Player player){
                FoodData foodData = player.getFoodData();
                if (foodData.getFoodLevel() > 19) {
                   s.setAmount(s.getAmount() * 1.18f);
                }
            }
        }
    }
    @SubscribeEvent
    public static void SkyPotatoOnHurt(LivingHurtEvent s){
        if (CurioCompat.hasItemInCurio(s.getEntity(), SpGodFoxPotato.SkyFoxPotato.get())){
            if (s.getEntity() instanceof Player player){
                int count= (int) Math.ceil(player.getAttributeValue(Attributes.LUCK)*0.18f);
              s.setAmount(s.getAmount()-count);
            }
        }if (s.getSource().getEntity() instanceof Player player){
        if (CurioCompat.hasItemInCurio(player, SpGodFoxPotato.SkyFoxPotato.get())){
            int count= (int) Math.ceil(player.getAttributeValue(Attributes.LUCK)*0.18f);
           s.setAmount(s.getAmount()+count);
        }
        }
    }
    @SubscribeEvent
    public static void SkyPotatoUpdate(SlashBladeEvent.UpdateEvent s){
        if (s.getEntity() instanceof Player player){
            if (CurioCompat.hasItemInCurio(player, SpGodFoxPotato.SkyFoxPotato.get())){
                if (s.getSlashBladeState().getTranslationKey().equals("item.godfox.truegodfox")){
                    FastMakeEntityUtil.FastEffect(player, SpEffect.VillageSun.get(), 1);
                }
            }
        }
    }
}
