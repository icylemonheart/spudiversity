package com.dinzeer.spudiversity.Item.compat.tw;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import twilightforest.capabilities.CapabilityList;
import twilightforest.capabilities.shield.IShieldCapability;

public class FortificationWandPotato extends Item {
    public FortificationWandPotato(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide()) {
            livingEntity.getCapability(CapabilityList.SHIELDS).ifPresent(IShieldCapability::replenishShields);
        }
        if ( livingEntity instanceof ServerPlayer player){
        if (!player.isCreative()) {
            player.getCooldowns().addCooldown(this, 1200);
        }
        }
        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
