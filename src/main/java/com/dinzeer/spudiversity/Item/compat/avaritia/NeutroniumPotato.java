package com.dinzeer.spudiversity.item.compat.avaritia;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.registry.SpAvaPotato;
import com.dinzeer.spudiversity.util.lang.CnLangUtil;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class NeutroniumPotato extends Item implements ICurioItem {
    private static final UUID NeutroniumPotato_UUID = UUID.nameUUIDFromBytes("NeutroniumPotato".getBytes());
    public NeutroniumPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(20)
                .saturationMod(2)
                .alwaysEat()
                .build()));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.MAX_HEALTH,
                new AttributeModifier(
                        NeutroniumPotato_UUID,
                        "NeutroniumPotato Boost",
                        3.25,
                        AttributeModifier.Operation.MULTIPLY_BASE
                )
        );
        modifiers.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        NeutroniumPotato_UUID,
                        "NeutroniumPotato Boost",
                        11.45,
                        AttributeModifier.Operation.MULTIPLY_BASE
                )
        );

        return modifiers;
    }
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (CurioCompat.hasItemInCurio(living, SpAvaPotato.NeutroniumPotato.get())){
                FastMakeEntityUtil.FastEffect(event.getEntity(), MobEffects.MOVEMENT_SLOWDOWN,5);
                FastMakeEntityUtil.FastEffect(event.getEntity(), MobEffects.WEAKNESS,5);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> toolTip, TooltipFlag tooltipFlag) {
        toolTip.add(AdvancedGradientTextHelper.createGradientText(
                    Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,0)).getString(),
                ColorPreset.DARK_NIGHT,
                AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
        ));
        toolTip.add(com.dinzeer.spudiversity.util.CharmPotatoes.effectsHeader());
        toolTip.add(AdvancedGradientTextHelper.createGradientText(
                Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,2)).getString(),
                ColorPreset.DARK_NIGHT,
                AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
        ));
        toolTip.add(AdvancedGradientTextHelper.createGradientText(
                Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,3)).getString(),
                ColorPreset.DARK_NIGHT,
                AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
        ));
    }

    @Override
    public Component getName(ItemStack itemStack) {
        return  AdvancedGradientTextHelper.createGradientText(
                Component.translatable(this.getDescriptionId(itemStack)).getString(),
                ColorPreset.DARK_NIGHT,
                AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
        );
    }
}
