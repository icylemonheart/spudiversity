package com.dinzeer.spudiversity.mixin;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public class VillagerMixin {
    @Inject(at = @At("HEAD"), method = "updateSpecialPrices")
    public void destroyBlock_markPlayerBeginBreak(Player p_35541_, CallbackInfo ci) {
        Villager village = (Villager)(Object)this;
        int i = village.getPlayerReputation(p_35541_);
        if (i != 0) {
            for(MerchantOffer merchantoffer : village.getOffers()) {
                merchantoffer.addToSpecialPriceDiff(-Mth.floor((float)i * merchantoffer.getPriceMultiplier()));
            }
        }

        if (p_35541_.hasEffect(SpEffect.VillageSun.get())) {

            for(MerchantOffer merchantoffer1 : village.getOffers()) {
                double d0 = 0.8;
                int j = (int)Math.floor(d0 * (double)merchantoffer1.getBaseCostA().getCount());
                merchantoffer1.addToSpecialPriceDiff(-Math.max(j, 1));
            }
        }
    }
}
