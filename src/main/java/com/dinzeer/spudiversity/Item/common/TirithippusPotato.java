package com.dinzeer.spudiversity.Item.common;

import net.minecraft.ChatFormatting;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class TirithippusPotato extends Item {
    public TirithippusPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.5f)
                        .alwaysEat()
                .build()));
    }

    @SubscribeEvent
    public static void OnClickPotato(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        if (player.isShiftKeyDown()&&player.getMainHandItem().getItem() instanceof TirithippusPotato) {
            // 使用NBT记录坐标
            ItemStack stack = player.getMainHandItem();
            CompoundTag tag = stack.getOrCreateTag();
            BlockPos pos = player.blockPosition();
            tag.putInt("RecordedX", pos.getX());
            tag.putInt("RecordedY", pos.getY());
            tag.putInt("RecordedZ", pos.getZ());
            tag.putBoolean("Recorded", true);
            player.displayClientMessage(Component.literal("已记录坐标: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()),true);
        }
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // 食用时检查NBT记录状态并传送
        if (entity instanceof Player) {
            Player player = (Player) entity;
            // 改为检查NBT标签
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.getBoolean("Recorded")) {
                int x = tag.getInt("RecordedX");
                int y = tag.getInt("RecordedY");
                int z = tag.getInt("RecordedZ");
                player.teleportTo(x + 0.5, y, z + 0.5); // 传送到记录位置
                player.displayClientMessage(Component.literal("已传送至记录坐标!"),true);
            } else {
                player.displayClientMessage(Component.literal("未记录坐标!"),true);
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(Component.translatable("item.spudiversity.tirithippus_potato.tooltip").withStyle(ChatFormatting.RED));
        tooltipList.add(Component.translatable("item.spudiversity.tirithippus_potato.tooltip_0").withStyle(ChatFormatting.GOLD));
        tooltipList.add(Component.translatable("item.spudiversity.tirithippus_potato.tooltip_1").withStyle(ChatFormatting.GOLD));
        CompoundTag tag = itemStack.getTag();
        if (tag != null && tag.getBoolean("Recorded")) {
            String x = String.valueOf(tag.getInt("RecordedX"));
            String y = String.valueOf(tag.getInt("RecordedY"));
            String z = String.valueOf(tag.getInt("RecordedZ"));
            tooltipList.add(Component.translatable("item.spudiversity.tirithippus_potato.tooltip_2",new Object[]{
                    Component.literal(x).withStyle(ChatFormatting.RED),
                    Component.literal(y).withStyle(ChatFormatting.RED),
                    Component.literal(z).withStyle(ChatFormatting.RED)
            }).withStyle(ChatFormatting.GOLD));
        }else {
            tooltipList.add(Component.translatable("item.spudiversity.tirithippus_potato.tooltip_3").withStyle(ChatFormatting.GOLD));
        }


    }
}