package com.dinzeer.spudiversity.item.compat.iceandfire;

import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.registry.SpICFPotato;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class LightningDragonPotato extends PotatoItemBase {

    public LightningDragonPotato(Properties properties) {
        super(properties, "lightning_dragon_potato");
    }

    @Override
    protected void applyCrystalEffect(Player player) {
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(PerpetualPotatoes.tag());
        tooltip.add(PerpetualPotatoes.flavor(LangUtil.FastMakeItemTooltip(SpICFPotato.LightningDragonPotato, 0)));
        tooltip.add(PerpetualPotatoes.effectsHeader());
        tooltip.add(PerpetualPotatoes.lead(LangUtil.FastMakeItemTooltip(SpICFPotato.LightningDragonPotato, 1)));
        for (int i = 2; i <= 7; i++) {
            tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpICFPotato.LightningDragonPotato, i)));
        }
        PerpetualPotatoes.appendActiveStatus(tooltip, false);
    }
}
