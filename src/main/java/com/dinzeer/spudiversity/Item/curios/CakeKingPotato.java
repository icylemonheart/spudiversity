package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class CakeKingPotato extends Item {
    public CakeKingPotato(Item.Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(20)
                .saturationMod(1f)
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




   public static int fly = 1;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (CurioCompat.hasItemInCurio(event.player,  SpPotato.CakeKingPotato.get())){

        Level level = player.level();

        if (player != null && !level.isClientSide) {
            addBeaconEffects(player);
            if (player.getFoodData().getFoodLevel() <= 0) {
                player.getFoodData().setFoodLevel(20);
                player.getFoodData().setSaturation(5.0f);
            }
            fly = 1;
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
        }else if (fly == 1){
            fly = 0;
            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();
        }
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
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {

    }












}