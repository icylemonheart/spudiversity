package com.dinzeer.spudiversity.item.speaical;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.registry.SpAvaPotato;
import com.dinzeer.spudiversity.util.lang.CnLangUtil;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

@EventBusSubscriber
public class NeutroniumPotato extends Item implements ICurioItem {
    private static final ResourceLocation NeutroniumPotato_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "neutronium_potato");
    public NeutroniumPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(20)
                .saturationModifier(2)
                .alwaysEdible()
                .build()));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.MAX_HEALTH,
                new AttributeModifier(
                        NeutroniumPotato_ID,
                        3.25,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                )
        );
        modifiers.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        NeutroniumPotato_ID,
                        11.45,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                )
        );

        return modifiers;
    }
    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (CurioCompat.hasItemInCurio(living, SpAvaPotato.NeutroniumPotato.get())){
                FastMakeEntityUtil.FastEffect(event.getEntity(), MobEffects.MOVEMENT_SLOWDOWN,5);
                FastMakeEntityUtil.FastEffect(event.getEntity(), MobEffects.WEAKNESS,5);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext level, List<Component> toolTip, TooltipFlag tooltipFlag) {
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
