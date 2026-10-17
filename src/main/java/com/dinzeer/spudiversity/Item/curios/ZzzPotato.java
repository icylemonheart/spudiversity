package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.CharmPotatoes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
@EventBusSubscriber
public class ZzzPotato extends Item implements ICurioItem {
    public ZzzPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationModifier(1f)
                .build()));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        for (int i = 0; i <= 1; i++) {
            tooltip.add(CharmPotatoes.flavor(CharmPotatoes.key("zzz_potato", i)));
        }
        tooltip.add(CharmPotatoes.effectsHeader());
        for (int i = 2; i <= 7; i++) {
            tooltip.add(CharmPotatoes.body(CharmPotatoes.key("zzz_potato", i)));
        }
        CharmPotatoes.appendKilledBosses(tooltip);
        tooltip.add(CharmPotatoes.obtainHeader());
        tooltip.add(CharmPotatoes.body(CharmPotatoes.key("zzz_potato", 8)));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof ServerPlayer player){
            PlayerKillBossAbilities playerCap = player.getCapability(PlayerKillBossAbilities.CAPABILITY);
            if (playerCap == null) return;
            int damage_level=1;
            int def_level=1;
            if (playerCap.getKillWither()){
                if (!player.hasEffect(MobEffects.REGENERATION)) {
                    FastMakeEntityUtil.FastEffect(player, MobEffects.REGENERATION, 1);
                }
                if (!player.hasEffect(MobEffects.HEALTH_BOOST)){
                FastMakeEntityUtil.FastEffectByHour(player, MobEffects.HEALTH_BOOST,1,36000);
                }
            }
            if (playerCap.getKillOldGrudian()){
                FastMakeEntityUtil.FastEffect(player,MobEffects.WATER_BREATHING,1);
                FastMakeEntityUtil.FastEffect(player,MobEffects.JUMP,1);
            }
            if (playerCap.getKillEnderDragon()){
                FastMakeEntityUtil.FastEffect(player,MobEffects.MOVEMENT_SPEED,1);
                if (!player.hasEffect(MobEffects.ABSORPTION)){
                FastMakeEntityUtil.FastEffect(player,MobEffects.ABSORPTION,1);
                }
                FastMakeEntityUtil.FastEffect(player,MobEffects.SLOW_FALLING,1);
            }
            if (playerCap.getKillWarden()){
                damage_level+=1;
                def_level+=1;
            }
            FastMakeEntityUtil.FastEffect(player,MobEffects.DAMAGE_RESISTANCE,def_level);
            FastMakeEntityUtil.FastEffect(player,MobEffects.DAMAGE_BOOST,damage_level);
            FastMakeEntityUtil.FastEffect(player,MobEffects.NIGHT_VISION,1);
        }
    }
       @SubscribeEvent
       public static void onPlayerHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
             if (CurioCompat.hasItemInCurio(player, SpPotato.ZzzPotato.get())){
                 PlayerKillBossAbilities playerCap = player.getCapability(PlayerKillBossAbilities.CAPABILITY);
                 if (playerCap != null && playerCap.getKillWarden()){
                 FastMakeEntityUtil.FastEffect(event.getEntity(),MobEffects.BLINDNESS,1);
                 FastMakeEntityUtil.FastEffect(event.getEntity(),MobEffects.MOVEMENT_SLOWDOWN,1);
                 }
             }
        }
       }
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof ServerPlayer player){
         player.removeEffect( MobEffects.HEALTH_BOOST);
        }
        return ICurioItem.super.canUnequip(slotContext, stack);

    }


}
