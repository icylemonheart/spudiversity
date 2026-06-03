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
import java.util.UUID;

public class FireDragonPotato extends PotatoItemBase {
    private static final UUID DEFENSE_BOOST_UUID = UUID.nameUUIDFromBytes("DRAGON_POTATO".getBytes());
    public FireDragonPotato(Properties properties) {
        super(properties,"fire_dragon_potato");
    }

    @Override
    protected void applyCrystalEffect(Player player) {
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        for (int i = 0; i < 9; i++) {
            tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpICFPotato.FireDragonPotato, i)).withStyle(ChatFormatting.RED));
        }

    }
}
