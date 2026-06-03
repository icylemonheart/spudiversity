package com.dinzeer.spudiversity.item.curios;

import com.dinzeer.legendreliclib.lib.util.WaitingTick;
import com.dinzeer.spudiversity.network.PlayTotemAnimationPacket;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.util.CurioUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

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
                SpuNetwork.INSTANCE.send(
                        PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                        new PlayTotemAnimationPacket(ItemStack)
                );
            });

        }
    }


}
