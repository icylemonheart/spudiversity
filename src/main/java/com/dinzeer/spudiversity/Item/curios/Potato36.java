package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.CharmPotatoes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

@EventBusSubscriber
public class Potato36 extends Item implements ICurioItem {
    public Potato36(Item.Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationModifier(0.3f)
                .build()));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(CharmPotatoes.flavor(CharmPotatoes.key("potato_36", 0)));
        tooltip.add(CharmPotatoes.effectsHeader());
        for (int i = 2; i <= 4; i++) {
            tooltip.add(CharmPotatoes.body(CharmPotatoes.key("potato_36", i)));
        }
        Runtime runtime = Runtime.getRuntime();
        long total = runtime.totalMemory();
        long used = total - runtime.freeMemory();
        long percent = total <= 0 ? 0 : Math.round(used * 100.0 / total);
        double multiplier = 1 + (total <= 0 ? 0 : (double) used / total);
        tooltip.add(CharmPotatoes.live(CharmPotatoes.MEMORY_NOW, percent, String.format("%.2f", multiplier)));
    }


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerHurt(LivingIncomingDamageEvent event) {
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
