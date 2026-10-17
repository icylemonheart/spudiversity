package com.dinzeer.spudiversity.mixin;

import com.dinzeer.spudiversity.item.cappotato.AlchemistPotato;
import com.dinzeer.spudiversity.registry.SpDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(SplashPotionItem.class)
public class SplashPotionItemMixin {
    private static final int MAX_SLOTS = 10;

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack offhand = player.getOffhandItem();
        if (!(offhand.getItem() instanceof AlchemistPotato)) return;
        cir.setReturnValue(InteractionResultHolder.fail(player.getItemInHand(hand)));
        if (level.isClientSide()) return;

        List<ItemStack> potions = new ArrayList<>(offhand.getOrDefault(SpDataComponents.ALCHEMIST_POTIONS.get(), List.of()));
        if (potions.size() >= MAX_SLOTS) {
            player.displayClientMessage(Component.translatable("message.alchemist_potato.full"), true);
            return;
        }
        potions.add(player.getMainHandItem().copyWithCount(1));
        offhand.set(SpDataComponents.ALCHEMIST_POTIONS.get(), List.copyOf(potions));
        player.getMainHandItem().shrink(1);
        player.displayClientMessage(Component.translatable("message.alchemist_potato.inject_success"), true);
    }
}
