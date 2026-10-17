package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.NuclearExplosionScheduler;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TarianPotato extends Item {
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            ServerLevel serverLevel = (ServerLevel) level;

            NuclearExplosionScheduler.createEllipsoidExplosion(serverLevel,player.position(),160,160,player);
        }

        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, net.minecraft.world.item.Item.TooltipContext level, List<Component> tooltipList, TooltipFlag tooltipFlag) {

        tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.TarianPotato,0)).withStyle(style -> style.withColor(16757502)));
        tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.TarianPotato,1)).withStyle(style -> style.withColor(11729407)));
        tooltipList.add(AdvancedGradientTextHelper.createGradientText(
                Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.TarianPotato,2)).getString(),
                ColorPreset.LIGHT_RAINBOW,
                AdvancedGradientTextHelper.GradientMode.WAVE_REVERSE));
    }
    public TarianPotato(Properties p_41383_) {
        super(p_41383_.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
    }
}
