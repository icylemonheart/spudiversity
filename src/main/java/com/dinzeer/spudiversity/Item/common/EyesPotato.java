package com.dinzeer.spudiversity.item.common;

// ... existing imports ...
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

// 新增必要的导入
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;

public class EyesPotato extends Item {
    public EyesPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (entity instanceof Player player) {
            // 清除所有负面效果
            List<MobEffect> effectsToRemove = new ArrayList<>();
            for (MobEffectInstance effectInstance : player.getActiveEffects()) {
                if (effectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                    effectsToRemove.add(effectInstance.getEffect());
                }
            }
            for (MobEffect effect : effectsToRemove) {
                player.removeEffect(effect);
            }

            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 12000, 0));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}