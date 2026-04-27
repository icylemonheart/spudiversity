package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class JijinPotato extends Item implements ICurioItem {
    private static final UUID DAMAGE_REDUCTION_UUID = UUID.nameUUIDFromBytes("JijinDamageReduction".getBytes());
    private static final UUID ATTACK_DAMAGE_UUID = UUID.nameUUIDFromBytes("JijinAttackDamage".getBytes());
    private static final UUID ARMOR_TOUGHNESS_UUID = UUID.nameUUIDFromBytes("JijinArmorToughness".getBytes());
    private static final UUID MAX_HEALTH_UUID = UUID.nameUUIDFromBytes("JijinMaxHealth".getBytes());

    public JijinPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(8)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof Player player) {
            int positiveEffects = getPositiveEffectCount(player);

            double attackBonus = positiveEffects * 0.02;
            double armorToughnessBonus = positiveEffects * 1.0;
            double maxHealthBonus = positiveEffects * 0.10;



            AttributeInstance AttackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
            AttributeInstance AttackTouAttr = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
            AttributeInstance HealAttr = player.getAttribute(Attributes.MAX_HEALTH);
            if (AttackAttr != null&& AttackTouAttr != null&& HealAttr != null){
                AttackAttr.removeModifier(ATTACK_DAMAGE_UUID);
                AttackTouAttr.removeModifier(ARMOR_TOUGHNESS_UUID);
                HealAttr.removeModifier(MAX_HEALTH_UUID);
                AttackAttr.addTransientModifier(new AttributeModifier(ATTACK_DAMAGE_UUID, "Jijin Attack Boost", attackBonus, AttributeModifier.Operation.MULTIPLY_TOTAL));
                AttackTouAttr.addTransientModifier(new AttributeModifier(ARMOR_TOUGHNESS_UUID, "Jijin Armor Toughness Boost", armorToughnessBonus, AttributeModifier.Operation.ADDITION));
                HealAttr.addTransientModifier(new AttributeModifier(MAX_HEALTH_UUID, "Jijin Max Health Boost", maxHealthBonus, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }





        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return AdvancedGradientTextHelper.createGradientText(
                super.getDescription().getString(),
                ColorPreset.BLUE_OCEAN,
                AdvancedGradientTextHelper.GradientMode.PULSE
        );
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (CurioCompat.hasItemInCurio(player, SpPotato.JijinPotato.get())) {
                float originalDamage = event.getAmount();
                float reducedDamage = originalDamage * 0.75f;
                event.setAmount(reducedDamage);
                
                if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                    float lifesteal = originalDamage * 0.20f;
                    player.heal(lifesteal);
                }
            }
        }
    }

    private int getPositiveEffectCount(Player player) {
        return player.getActiveEffects().stream()
                .filter(effect -> effect.getEffect().isBeneficial())
                .mapToInt(e -> 1)
                .sum();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasAltDown()) {
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip").withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_1").withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_2").withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_3").withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_4").withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_5").withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_6").withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_7").withStyle(ChatFormatting.AQUA));
        }
        else {
            tooltip.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_alt", 
                Component.literal("「Alt」").withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.AQUA));

        }
        if (Screen.hasControlDown()){
            for (int i=0; i<7; i++){
                tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_" + (i+8)).withStyle(ChatFormatting.AQUA));
            }

        }else {
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_alt",
                    Component.literal("Ctrl").withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.AQUA));
        }

        if (Screen.hasShiftDown()){
            for (int i=0; i<2; i++){
                tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_" + (i+15)).withStyle(ChatFormatting.AQUA));
            }

        }else {
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_shift",
                    Component.literal("shift").withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.AQUA));
        }





    }
}
