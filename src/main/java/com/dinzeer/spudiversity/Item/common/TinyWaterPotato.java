package com.dinzeer.spudiversity.item.common;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.FlightUtil;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
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
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber
public class TinyWaterPotato extends PotatoItemBase {
    private static final ResourceLocation ATTACK_DAMAGE_MODIFIER = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "tiny_water_attack_bonus");

    public TinyWaterPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()), "tiny_water");
    }

    @Override
    protected void applyCrystalEffect(Player player) {

    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(PerpetualPotatoes.tag());
        tooltip.add(PerpetualPotatoes.flavor(LangUtil.FastMakeItemTooltip(SpPotato.TinyWaterPotato, 0)));
        tooltip.add(PerpetualPotatoes.effectsHeader());
        for (int i = 1; i <= 4; i++) {
            tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.TinyWaterPotato, i)));
        }
        PerpetualPotatoes.appendActiveStatus(tooltip, false);
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
        if (store == null) return;
        if (!"tiny_water".equals(store.getActivePotato())) {
            AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackAttr != null && attackAttr.hasModifier(ATTACK_DAMAGE_MODIFIER)) {
                attackAttr.removeModifier(ATTACK_DAMAGE_MODIFIER);
            }
            return;
        }

        boolean inWater = player.isInWater();
        boolean inRain = player.level().isRainingAt(player.blockPosition());
        boolean inWaterOrRain = inWater || inRain;

        if (inWaterOrRain) {
            FlightUtil.grantFlight(player, "tiny_water");

            if (inWater) {
                FastMakeEntityUtil.FastEffectByMinute(player,MobEffects.NIGHT_VISION,1,2);
                FastMakeEntityUtil.FastEffectByMinute(player,MobEffects.WATER_BREATHING,1,2);
                FastMakeEntityUtil.FastEffectByMinute(player,MobEffects.DOLPHINS_GRACE,1,2);
            }

            if (player.tickCount % 20 == 0) {
                if (player.getHealth() < player.getMaxHealth()) {
                    player.heal(2.0f);
                }
            }

            AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackAttr != null && attackAttr.getModifier(ATTACK_DAMAGE_MODIFIER) == null) {
                attackAttr.addTransientModifier(
                        new AttributeModifier(ATTACK_DAMAGE_MODIFIER, 5.0, AttributeModifier.Operation.ADD_VALUE)
                );
            }

        } else {
            FlightUtil.revokeFlight(player, "tiny_water");
            player.removeEffect(MobEffects.WATER_BREATHING);
            AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackAttr != null) {
                attackAttr.removeModifier(ATTACK_DAMAGE_MODIFIER);
            }
        }

        if (isInDesertBiome(player) || isInNether(player)) {
            if (player.tickCount % 40 == 0) {
                player.hurt(player.damageSources().magic(), 2.0f);
            }
        }
    }

    private static boolean isInNether(Player player){
        return player.level().dimension() == Level.NETHER;
    }

    private static boolean isInDesertBiome(Player player) {
        ResourceLocation biomeKey = player.level().getBiome(player.blockPosition()).unwrapKey()
                .map(key -> key.location())
                .orElse(null);

        if (biomeKey == null) return false;

        String biomePath = biomeKey.getPath().toLowerCase();
        return biomePath.contains("desert") ||
                biomePath.contains("badlands") ||
                biomePath.contains("mesa");
    }
}
