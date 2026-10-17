package com.dinzeer.spudiversity.item.ex;

import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.List;

public class EnderDragonPotato extends Item {

    private static final int FOOD_COST = 2;

    public EnderDragonPotato(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);


        if (world.isClientSide) {
            return InteractionResultHolder.success(itemStack);
        }


        if (player.getFoodData().getFoodLevel() >= FOOD_COST / 2) {
            // 消耗饱食度（传入的数值是饱食度点数，1点=0.5鸡腿）
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - (FOOD_COST / 2));

            // 播放使用音效
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENDER_DRAGON_SHOOT, SoundSource.PLAYERS,
                    1.0F, 1.0F);


            DragonFireball fireball = new DragonFireball(world, player,
                    player.getLookAngle());

            double eyeHeight = player.getEyeY() - 0.1;
            fireball.setPos(player.getX(), eyeHeight, player.getZ());

            // 添加随机散射
            float inaccuracy = 1.0F; // 散射程度
            fireball.shoot(player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z,
                    1.5F, inaccuracy);

            // 添加到世界
            world.addFreshEntity(fireball);
            float tickCD= (float) Math.min(20, player.getAttributeValue(Attributes.ATTACK_SPEED));
            // 设置冷却时间（20 ticks = 1秒）
            player.getCooldowns().addCooldown(this, (int) (20-tickCD));


            return InteractionResultHolder.success(itemStack);
        } else {

            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.VILLAGER_NO, SoundSource.PLAYERS,
                    1.0F, 1.0F);
            return InteractionResultHolder.fail(itemStack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.item.Item.TooltipContext world, List<Component> tooltip, TooltipFlag flag) {

        for (int i = 0; i < 8; i++) {
            tooltip.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.EnderDragonPotato, i)).withStyle(ChatFormatting.LIGHT_PURPLE));
        }
        tooltip.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.EnderDragonPotato, 8)).withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.EnderDragonPotato, 9)).withStyle(ChatFormatting.GOLD));
    }
}