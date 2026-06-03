package com.dinzeer.spudiversity.item.ex;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.registry.SpEffect;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EveryThingPotato extends Item {
    public EveryThingPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(20)
                .saturationMod(20f)
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            FastMakeEntityUtil.FastEffectByMinute(player, SpEffect.EveryThing.get(), 1,300);
            FastMakeEntityUtil.FastEffectByMinute(player, MobEffects.SATURATION, 5,300);
            FastMakeEntityUtil.FastEffectByMinute(player, MobEffects.REGENERATION, 5,300);
            FastMakeEntityUtil.FastEffectByMinute(player, MobEffects.FIRE_RESISTANCE, 5,300);
        }
        return super.finishUsingItem(stack, world, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        for (int i = 0; i < 3; i++){
            tooltipList.add(AdvancedGradientTextHelper.createGradientText(
                    Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.EveryThingPotato,i)).getString(),
                    ColorPreset.NEON_CYBER,
                    AdvancedGradientTextHelper.GradientMode.FLOW
            ));
        }




    }

    @Override
    public Component getName(ItemStack item) {
        return AdvancedGradientTextHelper.createGradientText(
                Component.translatable(this.getDescriptionId(item)).getString(),
                ColorPreset.NEON_CYBER,
                AdvancedGradientTextHelper.GradientMode.FLOW
        );
    }
}
