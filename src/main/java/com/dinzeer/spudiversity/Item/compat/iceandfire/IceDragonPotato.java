package com.dinzeer.spudiversity.item.compat.iceandfire;

import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.registry.SpICFPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
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
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        for (int i = 0; i < 8; i++) {
            tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpICFPotato.IceDragonPotato, i)).withStyle(ChatFormatting.AQUA));
        }

    }
}
