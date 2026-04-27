package com.dinzeer.spudiversity.Item.speaical;

import com.dinzeer.legendreliclib.lib.util.ArmorPiercingUtil;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.dinzeer.spudiversity.util.FlightUtil;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class TangxingPotato extends Item {
    public static final UUID ATTACK_DAMAGE_UUID = UUID.nameUUIDFromBytes("TangxingAttackDamage".getBytes());
    public static final String CRYSTAL_TYPE = "tangxing_potato";

    public TangxingPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(10)
                .saturationMod(1f)
                .build())
                .rarity(Rarity.EPIC));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(abilities -> {
                abilities.setActivePotatoOff(CRYSTAL_TYPE);
            });
            

            
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        
        return super.finishUsingItem(stack, level, entity);
    }

    private static void applyPermanentEffects(Player player) {
        AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {
            attackAttr.removeModifier(ATTACK_DAMAGE_UUID);
            attackAttr.addTransientModifier(new AttributeModifier(
                ATTACK_DAMAGE_UUID,
                "Tangxing Permanent Attack Boost",
                8.0,
                AttributeModifier.Operation.ADDITION
            ));
        }
        FastMakeEntityUtil.FastEffect(player, MobEffects.FIRE_RESISTANCE, 1);
    }

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                if (store.getActivePotatoOff().equals(CRYSTAL_TYPE)) {
                    FoodData foodData = player.getFoodData();
                    foodData.setSaturation(0);
                    

                    applyPermanentEffects(player);
                    FlightUtil.grantFlight(player);
                }else {
                    AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
                    if (attackAttr != null) {
                        if (attackAttr.getModifier(ATTACK_DAMAGE_UUID)!=null){
                        if (attackAttr.hasModifier(attackAttr.getModifier(ATTACK_DAMAGE_UUID))){
                        attackAttr.removeModifier(ATTACK_DAMAGE_UUID);
                        FlightUtil.revokeFlight(player);
                        }
                        }
                    }
                }
            });
        }
    }



    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                LivingEntity target = event.getEntity();

                if (store.getActivePotatoOff().equals(CRYSTAL_TYPE)){
                    target.setSecondsOnFire(15);
                    ArmorPiercingUtil.addArmorPiercingEffect(target, player,1,1.0f);
                }
            });
        }
        if (event.getEntity() instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
                if (store.getActivePotatoOff().equals(CRYSTAL_TYPE)) {
                    if (event.getSource().is(DamageTypes.ON_FIRE)){
                        event.setAmount(0);
                    }
                }
            });
        }
    }

    private static void createExplosion(Level level, double x, double y, double z) {
        if (level instanceof ServerLevel serverLevel) {
            level.explode(null, x, y, z, 2.0F, Level.ExplosionInteraction.NONE);
            
            for (int i = 0; i < 20; i++) {
                double vx = (Math.random() - 0.5) * 2.0;
                double vy = Math.random() * 2.0;
                double vz = (Math.random() - 0.5) * 2.0;
                serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 1, vx, vy, vz, 0.1);
            }
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return AdvancedGradientTextHelper.createGradientText(
            super.getDescription().getString(),
            ColorPreset.ORANGE_FIRE,
            AdvancedGradientTextHelper.GradientMode.WAVE
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasAltDown()) {
            for (int i = 0; i < 8; i++) {
                tooltip.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, i))
                    .withStyle(ChatFormatting.RED));
            }
        } else {
            tooltip.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_alt",
                Component.literal("「Alt」").withStyle(ChatFormatting.RED)).withStyle(ChatFormatting.DARK_RED));
        }
        if (Screen.hasShiftDown()) {
            for (int i = 0; i < 10; i++) {
                tooltip.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, i+8))
                        .withStyle(ChatFormatting.RED));
            }
        } else {
            tooltip.add(Component.translatable("item.spudiversity.jijin_potato.tooltip_shift",
                    Component.literal("Shift").withStyle(ChatFormatting.RED)).withStyle(ChatFormatting.DARK_RED));
        }
    }
}
