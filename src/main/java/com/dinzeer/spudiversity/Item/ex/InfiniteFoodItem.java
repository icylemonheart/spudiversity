package com.dinzeer.spudiversity.item.ex;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class InfiniteFoodItem extends Item {
    private final int nutrition;
    public InfiniteFoodItem(Properties properties,int nutrition) {
        super(properties
                .food(new FoodProperties.Builder()
                        .nutrition(nutrition)
                        .saturationMod(0.6f)
                        .alwaysEat()
                        .build())
        );
        this.nutrition=nutrition;
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (entity instanceof Player player) {
            // 添加冷却（3秒 = 60 ticks）
            player.getCooldowns().addCooldown(this, 60);
            player.getFoodData().eat(nutrition, 0.6f);
        }
        return stack; // 返回原物品堆，不消耗物品
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT; // 食用动画
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32; // 原版食物食用耗时
    }
}
