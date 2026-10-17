package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.entity.AirBladeProjectile;
import com.dinzeer.spudiversity.registry.SpPotato;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;

@EventBusSubscriber
public class MysteriousPotato extends Item {
    public MysteriousPotato(Item.Properties p_41383_) {
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
    // 添加状态效果
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;
        if (isCurios(player)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1)); // 力量II
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1));    // 急迫II
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0)); // 迅捷I
            if (!player.hasEffect(MobEffects.REGENERATION)){
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0)); // 生命恢复I
                 }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        summonIs(player);
    }

    public static void summonIs(Player player){
        if (isCurios(player)) {
            double damage = Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).getValue() * 0.25; // 玩家攻击力的25%


            summonAirBlade(player, (float) damage);

        }
    }


    public static void summonAirBlade(LivingEntity owner, float damageMultiplier) {
        Level level = owner.level();
        if (!level.isClientSide) {
            AirBladeProjectile airBlade = new AirBladeProjectile(level, owner, damageMultiplier);
            level.addFreshEntity(airBlade);
        }
    }
    public static Boolean isCurios(Player player){
        return CurioCompat.hasItemInCurio(player, SpPotato.MysteriousPotato.get());
    }
}
