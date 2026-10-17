package com.dinzeer.spudiversity.item.speaical;

import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;

public class NoNamePotatoDark extends Item {
    public NoNamePotatoDark(Properties properties) {
        super(properties);
    }




    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        if (Screen.hasAltDown()){
            for (int i = 0; i < 4; i++){
                tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.NoNamePotatoDark,i)).withStyle(ChatFormatting.GRAY));
            }
        }else {
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_alt",
                    Component.literal("Alt").withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            if (level.dimension() == Level.END) {
                BlockPos playerPos = player.blockPosition();


                int range = 5;
                int targetX = 0, targetY = 0, targetZ = 0;

                if (Math.abs(playerPos.getX() - targetX) <= range &&
                        Math.abs(playerPos.getY() - targetY) <= range &&
                        Math.abs(playerPos.getZ() - targetZ) <= range) {

                    ItemStack star_potato = new ItemStack(SpPotato.StarPotato.get());

                    CompoundTag tag = star_potato.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                    tag.putString("ObtainedByPlayerName", player.getName().getString());
                    star_potato.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

                    itemStack.shrink(1);
                    if (!player.getInventory().add(star_potato)) {
                        player.drop(star_potato, false);
                    }
                    return itemStack.isEmpty() ? star_potato : itemStack;
                }
            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}
