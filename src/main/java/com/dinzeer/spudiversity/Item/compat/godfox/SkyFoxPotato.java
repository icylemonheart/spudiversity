package com.dinzeer.spudiversity.Item.compat.godfox;

import com.dinzeer.spudiversity.regsitry.SpGodFoxPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class SkyFoxPotato extends Item implements ICurioItem {
    private static final UUID DEFENSE_BOOST_UUID = UUID.nameUUIDFromBytes("SkyFoxPotato".getBytes());
    public SkyFoxPotato(Properties properties) {
        super(properties);
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.MAX_HEALTH,
                new AttributeModifier(
                        DEFENSE_BOOST_UUID,
                        "SkyFoxPotato Damage Boost",
                        0.18,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                )
        );
        modifiers.put(
                Attributes.LUCK,
                new AttributeModifier(
                        DEFENSE_BOOST_UUID,
                        "SkyFoxPotato Damage Boost",
                        9,
                        AttributeModifier.Operation.ADDITION
                )
        );

        return modifiers;
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        for (int i = 0; i < 7; i++) {
                tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato, i)).withStyle(ChatFormatting.AQUA));
        }

    }
}
