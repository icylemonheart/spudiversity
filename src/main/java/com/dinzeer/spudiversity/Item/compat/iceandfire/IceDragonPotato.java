package com.dinzeer.spudiversity.item.compat.iceandfire;

import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.registry.SpICFPotato;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IceDragonPotato extends PotatoItemBase {

    public IceDragonPotato(Properties properties) {
        super(properties,"ice_dragon_potato");
    }

    @Override
    protected void applyCrystalEffect(Player player) {
    }

    // 龙霜土豆
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(PerpetualPotatoes.tag());
        tooltipList.add(PerpetualPotatoes.flavor(LangUtil.FastMakeItemTooltip(SpICFPotato.IceDragonPotato, 0)));
        tooltipList.add(PerpetualPotatoes.effectsHeader());
        tooltipList.add(PerpetualPotatoes.lead(LangUtil.FastMakeItemTooltip(SpICFPotato.IceDragonPotato, 1)));
        for (int i = 2; i <= 6; i++) {
            tooltipList.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpICFPotato.IceDragonPotato, i)));
        }
        PerpetualPotatoes.appendActiveStatus(tooltipList, false);
    }
}
