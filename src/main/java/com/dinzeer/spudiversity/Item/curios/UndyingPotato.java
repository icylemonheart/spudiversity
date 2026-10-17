package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.util.WaitingTick;
import com.dinzeer.spudiversity.network.PlayTotemAnimationPacket;
import com.dinzeer.spudiversity.util.CurioUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

@EventBusSubscriber
public class UndyingPotato extends Item {
    public UndyingPotato(Properties properties) {
        super(properties
                .stacksTo(1)
                .food((new FoodProperties.Builder())
                .nutrition(4)
                .saturationModifier(1f)
                .alwaysEdible()
                .build()));
    }

    @SubscribeEvent
    public static void OnDeathTime(LivingDeathEvent event){
        LivingEntity entity = event.getEntity();
        if (entity.getOffhandItem().getItem() instanceof UndyingPotato){
            event.setCanceled(true);
            DoUndead(entity.getOffhandItem(), entity);
            return;
        }
        if (entity.getMainHandItem().getItem() instanceof UndyingPotato){
            event.setCanceled(true);
            DoUndead(entity.getMainHandItem(), entity);
            return;
        }
        boolean canUnDying=false;
        ItemStack itemStack = null;
        List<ItemStack> Items= CurioUtil.getCurioItems(entity);
        for (ItemStack item : Items) {
            if (item.getItem() instanceof UndyingPotato p){
                canUnDying=true;
                itemStack=item;
            }
        }
        if (canUnDying){
            event.setCanceled(true);
            DoUndead(itemStack, entity);
            return;
        }

    }
    public static void DoUndead(ItemStack itemStack, LivingEntity entity){
        entity.setHealth(1.0F);
        itemStack.shrink(1);
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0));
        if (entity instanceof Player player){
            WaitingTick.schedule(1, () -> {
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(
                        player,
                        new PlayTotemAnimationPacket(itemStack)
                );
            });

        }
    }


}
