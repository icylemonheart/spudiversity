package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.util.Mth;
import net.minecraftforge.event.entity.player.TradeWithVillagerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VillageSun extends MobEffect {
    public VillageSun() {
        super(MobEffectCategory.BENEFICIAL, 0xFFA500);
    }



}