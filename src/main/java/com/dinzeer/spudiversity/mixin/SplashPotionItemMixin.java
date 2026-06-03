package com.dinzeer.spudiversity.mixin;

import com.dinzeer.spudiversity.item.cappotato.AlchemistPotato;
import com.dinzeer.spudiversity.capability.impl.AlchemistPotatoCapability;
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

@Mixin(SplashPotionItem.class)
public class SplashPotionItemMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        // 检查副手是否持有炼金术士土豆
        ItemStack offhand = player.getOffhandItem();
        if (offhand.getItem() instanceof AlchemistPotato) {
            offhand.getCapability(AlchemistPotatoCapability.INSTANCE).ifPresent(cap -> {
                if (cap.addPotion(player.getMainHandItem())) {
                    // 消耗一瓶药水
                    player.getMainHandItem().shrink(1);
                    player.displayClientMessage(Component.translatable("message.alchemist_potato.inject_success"), true);
                } else {
                    player.displayClientMessage(Component.translatable("message.alchemist_potato.full"), true);
                }
            });











            cir.setReturnValue(InteractionResultHolder.fail(player.getItemInHand(hand)));
        }
    }
}