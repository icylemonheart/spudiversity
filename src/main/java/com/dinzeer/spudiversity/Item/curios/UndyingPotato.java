package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.WaitingTick;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.network.PlayTotemAnimationPacket;
import com.dinzeer.spudiversity.util.CurioUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class UndyingPotato extends Item {
    public UndyingPotato(Properties properties) {
        super(properties
                .stacksTo(1)
                .food((new FoodProperties.Builder())
                .nutrition(4)
                .saturationMod(1f)
                .alwaysEat()
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
            itemStack.shrink(1);
            return;
        }

    }
    public static void DoUndead(ItemStack ItemStack, LivingEntity entity){
        entity.setHealth(1.0F);
        ItemStack.shrink(1);
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0));
        if (entity instanceof Player player){
            WaitingTick.schedule(1, () -> {
                Spudiversity.CHANNEL.send(
                        PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                        new PlayTotemAnimationPacket(ItemStack)
                );
            });

        }
    }


}
