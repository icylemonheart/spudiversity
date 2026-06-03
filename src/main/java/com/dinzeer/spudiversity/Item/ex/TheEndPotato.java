package com.dinzeer.spudiversity.item.ex;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.registry.SpEffect;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheEndPotato extends Item {
    public TheEndPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(1)
                .saturationMod(0.1f)
                .build()));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            FastMakeEntityUtil.FastEffectByMinute(player, SpEffect.WeAreNull.get(), 1,300);
        }
        return super.finishUsingItem(stack, world, entity);
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        for (int i = 0; i < 8; i++){
            tooltipList.add(AdvancedGradientTextHelper.createGradientText(
                    Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.TheEndPotato,i)).getString(),
                    ColorPreset.RED_EMBER,
                    AdvancedGradientTextHelper.GradientMode.FLOW
            ));
        }




    }

    @Override
    public Component getName(ItemStack item) {
        return AdvancedGradientTextHelper.createGradientText(
                Component.translatable(this.getDescriptionId(item)).getString(),
                ColorPreset.RED_EMBER,
                AdvancedGradientTextHelper.GradientMode.FLOW
        );
    }
}
