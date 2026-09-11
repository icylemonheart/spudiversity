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
import java.util.UUID;

public class LightningDragonPotato extends PotatoItemBase {
    private static final UUID DEFENSE_BOOST_UUID = UUID.nameUUIDFromBytes("DRAGON_POTATO".getBytes());
    public LightningDragonPotato(Properties properties) {
        super(properties,"lightning_dragon_potato");
    }

    @Override
    protected void applyCrystalEffect(Player player) {

    }

    // 龙霆土豆
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(PerpetualPotatoes.tag());
        tooltipList.add(PerpetualPotatoes.flavor(LangUtil.FastMakeItemTooltip(SpICFPotato.LightningDragonPotato, 0)));
        tooltipList.add(PerpetualPotatoes.effectsHeader());
        tooltipList.add(PerpetualPotatoes.lead(LangUtil.FastMakeItemTooltip(SpICFPotato.LightningDragonPotato, 1)));
        for (int i = 2; i <= 7; i++) {
            tooltipList.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpICFPotato.LightningDragonPotato, i)));
        }
        PerpetualPotatoes.appendActiveStatus(tooltipList, false);
    }
}
