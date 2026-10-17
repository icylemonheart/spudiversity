package com.dinzeer.spudiversity.item.compat.tw;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import twilightforest.init.TFDataAttachments;

public class FortificationWandPotato extends Item {
    public FortificationWandPotato(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide() && ModList.get().isLoaded("twilightforest")) {
            livingEntity.getData(TFDataAttachments.FORTIFICATION_SHIELDS).setShields(livingEntity, 5, false);
        }
        if (livingEntity instanceof ServerPlayer player && !player.isCreative()) {
            player.getCooldowns().addCooldown(this, 1200);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
