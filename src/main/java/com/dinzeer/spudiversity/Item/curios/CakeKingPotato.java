package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.FlightUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber
public class CakeKingPotato extends Item {
    public CakeKingPotato(Item.Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(20)
                .saturationModifier(1f)
                .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 5));
        }
        return super.finishUsingItem(stack, world, entity);
    }

    private static final Set<UUID> FLY_GRANTED = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide) {
            return;
        }

        if (CurioCompat.hasItemInCurio(player, SpPotato.CakeKingPotato.get())) {
            addBeaconEffects(player);
            if (player.getFoodData().getFoodLevel() <= 0) {
                player.getFoodData().setFoodLevel(20);
                player.getFoodData().setSaturation(5.0f);
            }
            FLY_GRANTED.add(player.getUUID());
            FlightUtil.grantFlight(player, "cake_king");
        } else if (FLY_GRANTED.remove(player.getUUID())) {
            FlightUtil.revokeFlight(player, "cake_king");
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        FLY_GRANTED.remove(event.getEntity().getUUID());
    }

    private static void addBeaconEffects(Player player) {
        // 满级信标效果
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1)); // 速度II
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 1)); // 急迫II
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 1)); // 抗性提升II
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1)); // 跳跃提升II
        if (!player.hasEffect(MobEffects.REGENERATION)){
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 1));
        }
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 1));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext level, List<Component> tooltipList, TooltipFlag tooltipFlag) {

    }
}
