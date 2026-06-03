package com.dinzeer.spudiversity.item.compat.tw;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.util.CurioUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class BloodPotato extends Item implements ICurioItem {
    public BloodPotato(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        FastMakeEntityUtil.FastEffect(slotContext.entity(), MobEffects.DAMAGE_BOOST, 2);
        FastMakeEntityUtil.FastEffect(slotContext.entity(), MobEffects.MOVEMENT_SPEED, 2);
        FastMakeEntityUtil.FastEffect(slotContext.entity(), MobEffects.DAMAGE_RESISTANCE, 2);
        FastMakeEntityUtil.FastEffect(slotContext.entity(), MobEffects.FIRE_RESISTANCE, 1);
        FastMakeEntityUtil.FastEffect(slotContext.entity(), MobEffects.NIGHT_VISION, 1);
    }
    @SubscribeEvent
    public static void OnDeath(LivingDeathEvent event){
        LivingEntity player=event.getEntity();

        List<ItemStack> Items= CurioUtil.getCurioItems(player);
        for (ItemStack item : Items) {
            if (item.getItem() instanceof BloodPotato p){
                event.setCanceled(true);
                DoUndead(item,player);
                break;
            }
        }
    }
    @SubscribeEvent
    public static void OnHurt(LivingHurtEvent event){

     if (event.getSource().getEntity() instanceof LivingEntity player){
             List<ItemStack> Items= CurioUtil.getCurioItems(player);
             for (ItemStack item : Items) {
                 if (item.getItem() instanceof BloodPotato p){
                    player.heal(3);
                     break;
                 }
             }
     }
    }

    public static void DoUndead(ItemStack ItemStack, LivingEntity entity){
        if (entity instanceof Player playerEntity){
            if (playerEntity.getCooldowns().isOnCooldown(ItemStack.getItem())) {
                return;
            }else {
                playerEntity.getCooldowns().addCooldown(ItemStack.getItem(), 60*20);
            }
        }

        entity.setHealth(entity.getMaxHealth()*0.5f);
        FastMakeEntityUtil.FastEffect(entity, MobEffects.ABSORPTION, 2);
        FastMakeEntityUtil.FastEffect(entity, MobEffects.REGENERATION, 2);

    }
}
