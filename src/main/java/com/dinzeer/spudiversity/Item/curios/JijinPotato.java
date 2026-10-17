package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.client.ClientHooks;
import com.dinzeer.spudiversity.registry.SpPotato;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber
public class JijinPotato extends Item implements ICurioItem {
    private static final ResourceLocation DAMAGE_REDUCTION_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "jijin_damage_reduction");
    private static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "jijin_attack_damage");
    private static final ResourceLocation ARMOR_TOUGHNESS_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "jijin_armor_toughness");
    private static final ResourceLocation MAX_HEALTH_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "jijin_max_health");
    private static final Map<Player, Integer> LAST_POSITIVE_EFFECTS = new HashMap<>();

    public JijinPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(8)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof Player player) {
            if (player.level().isClientSide) return;
            int positiveEffects = getPositiveEffectCount(player);

            Integer lastEffects = LAST_POSITIVE_EFFECTS.get(player);
            if (lastEffects != null && lastEffects == positiveEffects) {
                return;
            }
            LAST_POSITIVE_EFFECTS.put(player, positiveEffects);

            double attackBonus = positiveEffects * 0.02;
            double armorToughnessBonus = positiveEffects * 1.0;
            double maxHealthBonus = positiveEffects * 0.10;

            AttributeInstance AttackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
            AttributeInstance AttackTouAttr = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
            AttributeInstance HealAttr = player.getAttribute(Attributes.MAX_HEALTH);
            if (AttackAttr != null&& AttackTouAttr != null&& HealAttr != null){
                float oldMax = player.getMaxHealth();
                float oldHealth = player.getHealth();
                AttackAttr.removeModifier(ATTACK_DAMAGE_ID);
                AttackTouAttr.removeModifier(ARMOR_TOUGHNESS_ID);
                HealAttr.removeModifier(MAX_HEALTH_ID);
                if (positiveEffects > 0) {
                    AttackAttr.addTransientModifier(new AttributeModifier(ATTACK_DAMAGE_ID, attackBonus, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                    AttackTouAttr.addTransientModifier(new AttributeModifier(ARMOR_TOUGHNESS_ID, armorToughnessBonus, AttributeModifier.Operation.ADD_VALUE));
                    HealAttr.addTransientModifier(new AttributeModifier(MAX_HEALTH_ID, maxHealthBonus, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                }
                float newMax = player.getMaxHealth();
                if (oldMax > 0 && oldHealth >= oldMax - 0.01f) {
                    player.setHealth(newMax);
                } else if (oldMax > 0) {
                    player.setHealth(Math.min(oldHealth * newMax / oldMax, newMax));
                }
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        if (slotContext.entity() instanceof Player player) {
            LAST_POSITIVE_EFFECTS.remove(player);
            AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackAttr != null) attackAttr.removeModifier(ATTACK_DAMAGE_ID);
            AttributeInstance armorToughnessAttr = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
            if (armorToughnessAttr != null) armorToughnessAttr.removeModifier(ARMOR_TOUGHNESS_ID);
            AttributeInstance maxHealthAttr = player.getAttribute(Attributes.MAX_HEALTH);
            if (maxHealthAttr != null) {
                maxHealthAttr.removeModifier(MAX_HEALTH_ID);
                if (player.getHealth() > player.getMaxHealth()) {
                    player.setHealth(player.getMaxHealth());
                }
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
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
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

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        LAST_POSITIVE_EFFECTS.remove(event.getEntity());
    }

    private static int getPositiveEffectCount(Player player) {
        return player.getActiveEffects().stream()
                .filter(effect -> effect.getEffect().value().isBeneficial())
                .mapToInt(e -> 1)
                .sum();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasControlDown()) {
            for (int i = 0; i < 7; i++) {
                tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_" + (i + 8))
                        .withStyle(ChatFormatting.LIGHT_PURPLE));
            }
        } else {
            tooltip.add(com.dinzeer.spudiversity.util.CharmPotatoes.flavor(
                    "item.spudiversity.jijin_potato.tooltip"));
            tooltip.add(com.dinzeer.spudiversity.util.CharmPotatoes.effectsHeader());
            for (int i = 2; i <= 7; i++) {
                tooltip.add(com.dinzeer.spudiversity.util.CharmPotatoes.body(
                        "item.spudiversity.jijin_potato.tooltip_" + i));
            }
            // 实时显示当前正面效果数量与具体加成
            Player self = ClientHooks.getLocalPlayer();
            if (self != null) {
                int n = getPositiveEffectCount(self);
                tooltip.add(com.dinzeer.spudiversity.util.CharmPotatoes.live(
                        com.dinzeer.spudiversity.util.CharmPotatoes.JIJIN_NOW,
                        n, n * 2, n, n * 10));
            }
            if (Screen.hasShiftDown()) {
                tooltip.add(com.dinzeer.spudiversity.util.CharmPotatoes.obtainHeader());
                for (int i = 15; i <= 16; i++) {
                    tooltip.add(com.dinzeer.spudiversity.util.CharmPotatoes.body(
                            "item.spudiversity.jijin_potato.tooltip_" + i));
                }
            } else {
                tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_shift",
                        Component.literal("Shift").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD))
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_alt",
                    Component.literal("Ctrl").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD))
                    .withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
