package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

@Mod.EventBusSubscriber
public class Potato36 extends Item implements ICurioItem {
    public Potato36(Item.Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.3f)
                .build()));
    }


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity entity) {
            if (CurioCompat.hasItemInCurio(entity, SpPotato.Potato36.get())){

                Runtime runtime= Runtime.getRuntime();
                long totalMemory= runtime.totalMemory();
                long freeMemory= runtime.freeMemory();
                long usedMemory= totalMemory-freeMemory;
                float addDamage=1+((float) usedMemory /totalMemory);
                event.setAmount(event.getAmount()*addDamage);

                if (Math.random()>0.75){
                    event.setAmount(event.getAmount()*0);
                }
                if (!event.getSource().is(DamageTypes.MAGIC)&&!event.getSource().is(DamageTypes.ON_FIRE)){
                    FastMakeEntityUtil.FastHurt(event.getEntity(),entity, event.getAmount()*0.05f, DamageTypes.MAGIC);
                }
            }
        }
    }




}
