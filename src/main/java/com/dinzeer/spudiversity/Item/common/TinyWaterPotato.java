package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.util.FlightUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class TinyWaterPotato extends PotatoItemBase {
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("a1b2c3d4-e5f6-7890-abcd-ef1234567890");

    public TinyWaterPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()), "tiny_water");
    }

    @Override
    protected void applyCrystalEffect(Player player) {

    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;

        player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
            if (!"tiny_water".equals(store.getActivePotato())) return;

            boolean inWater = player.isInWater();
            boolean inRain = player.level().isRainingAt(player.blockPosition());
            boolean inWaterOrRain = inWater || inRain;

            if (inWaterOrRain) {
                FlightUtil.grantFlight(player);

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

                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ATTACK_DAMAGE_MODIFIER);
                    player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(
                            new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "tiny_water_attack_bonus", 5.0, AttributeModifier.Operation.ADDITION)
                    );

            } else {
                FlightUtil.revokeFlight(player);
                player.removeEffect(MobEffects.WATER_BREATHING);
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ATTACK_DAMAGE_MODIFIER);


            }

            if (isInDesertBiome(player) || isInNether(player)) {
                if (player.tickCount % 40 == 0) {
                    player.hurt(player.damageSources().magic(), 2.0f);
                }
            }
        });
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
