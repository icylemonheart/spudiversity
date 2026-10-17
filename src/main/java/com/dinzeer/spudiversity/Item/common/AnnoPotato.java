package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.registry.SpSounds;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AnnoPotato extends Item {
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {

        if (!level.isClientSide) {
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    SpSounds.anno_happy.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        FastMakeEntityUtil.FastEffect(entity, MobEffects.REGENERATION, 3, 30);
        return super.finishUsingItem(itemStack, level, entity);
    }

    public AnnoPotato(Properties p_41383_) {
        super(p_41383_.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
    }

    @Override
    public SoundEvent getEatingSound() {
        return  SpSounds.anno_happy.get();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, net.minecraft.world.item.Item.TooltipContext level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        for (int i = 0; i < 4; i++) {
            tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.AnnoPotato, i)).withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}