package com.dinzeer.spudiversity.Item;

import committee.nova.mods.avaritia.common.item.resources.UpgradeSmithingTemplateItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class EdelweissUpgradeTemplate extends SmithingTemplateItem {
    public EdelweissUpgradeTemplate() {
        super(Component.translatable("item.spudiversity.upgrade_smithing_template_use").withStyle(style -> style.withColor(16757497)),
                Component.translatable("item.spudiversity.upgrade_smithing_template_need").withStyle(style -> style.withColor(16757497)),
                Component.translatable("item.spudiversity.upgrade_smithing_template").withStyle(style -> style.withColor(16757497)),
                Component.translatable("item.spudiversity.upgrade_smithing_template").withStyle(style -> style.withColor(16757497)),
                Component.translatable("item.spudiversity.upgrade_smithing_template").withStyle(style -> style.withColor(16757497)),
                List.of(),
                List.of()
        )
        ;
    }

}
