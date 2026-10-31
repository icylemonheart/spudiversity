package com.dinzeer.spudiversity.item.compat.tw;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.util.CurioUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

@EventBusSubscriber
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
    public static void onHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            List<ItemStack> items = CurioUtil.getCurioItems(attacker);
            for (ItemStack item : items) {
                if (item.getItem() instanceof BloodPotato) {
                    attacker.heal(3);
                    break;
                }
            }
        }
    }
}
