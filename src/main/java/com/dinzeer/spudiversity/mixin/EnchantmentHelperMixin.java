package com.dinzeer.spudiversity.mixin;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.registry.SpAvaPotato;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getEnchantmentLevel", at = @At("RETURN"), cancellable = true)
    private static void onGetEnchantmentLevel(Holder<Enchantment> enchantment, LivingEntity living, CallbackInfoReturnable<Integer> cir) {
        if (!(living instanceof Player player)) return;

        boolean hasPotato = CurioCompat.hasItemInCurio(player, SpAvaPotato.NeutroniumPotato.get());
        if (!hasPotato) return;

        if (enchantment.is(Enchantments.FORTUNE) || enchantment.is(Enchantments.LOOTING)) {
            int original = cir.getReturnValue();
            // 直接设置返回值，原值 +5（不突破附魔上限，但通常无上限限制）
            cir.setReturnValue(original + 5);
        }
    }
}
