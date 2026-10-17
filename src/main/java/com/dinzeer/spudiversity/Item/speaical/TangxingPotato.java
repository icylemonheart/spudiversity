package com.dinzeer.spudiversity.item.speaical;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.util.ArmorPiercingUtil;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.event.PlayerEventHandler;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.FlightUtil;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
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
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber
public class TangxingPotato extends Item {
    public static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "tangxing_attack_damage");
    public static final String CRYSTAL_TYPE = "tangxing_potato";

    public TangxingPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(10)
                .saturationModifier(1f)
                .build())
                .rarity(Rarity.EPIC));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            PlayerPermanentAbilities abilities = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (abilities != null) {
                abilities.setActivePotatoOff(CRYSTAL_TYPE);
            }
            PlayerEventHandler.syncCapabilities(player);
        }

        return super.finishUsingItem(stack, level, entity);
    }

    private static void applyPermanentEffects(Player player) {
        AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null && attackAttr.getModifier(ATTACK_DAMAGE_ID) == null) {
            attackAttr.addTransientModifier(new AttributeModifier(
                ATTACK_DAMAGE_ID,
                8.0,
                AttributeModifier.Operation.ADD_VALUE
            ));
        }
        FastMakeEntityUtil.FastEffect(player, MobEffects.FIRE_RESISTANCE, 1);
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            if (player.level().isClientSide) return;
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null) {
                if (store.getActivePotatoOff().equals(CRYSTAL_TYPE)) {
                    FoodData foodData = player.getFoodData();
                    foodData.setSaturation(0);

                    applyPermanentEffects(player);
                    if ("for_love".equals(store.getActivePotato())) {
                        FlightUtil.grantFlight(player, "tangxing");
                    } else {
                        FlightUtil.revokeFlight(player, "tangxing");
                    }
                } else {
                    FlightUtil.revokeFlight(player, "tangxing");
                    AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
                    if (attackAttr != null && attackAttr.hasModifier(ATTACK_DAMAGE_ID)) {
                        attackAttr.removeModifier(ATTACK_DAMAGE_ID);
                    }
                }
            }
        }
    }



    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            LivingEntity target = event.getEntity();

            if (store != null && store.getActivePotatoOff().equals(CRYSTAL_TYPE)){
                target.igniteForSeconds(15);
                ArmorPiercingUtil.addArmorPiercingEffect(target, player,1,1.0f);
            }
        }
        if (event.getEntity() instanceof Player player) {
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null && store.getActivePotatoOff().equals(CRYSTAL_TYPE)) {
                if (event.getSource().is(DamageTypes.ON_FIRE)){
                    event.setAmount(0);
                }
                if (event.getSource().is(DamageTypes.IN_FIRE)){
                    event.setAmount(0);
                }
                if (event.getSource().is(DamageTypes.LAVA)){
                    event.setAmount(0);
                }
                if (event.getSource().is(DamageTypes.UNATTRIBUTED_FIREBALL)){
                    event.setAmount(0);
                }
                if (event.getSource().is(DamageTypes.FIREBALL)){
                    event.setAmount(0);
                }
            }
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
    public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(PerpetualPotatoes.tag());
        tooltip.add(PerpetualPotatoes.flavor(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, 0)));

        if (Screen.hasShiftDown()) {
            // 具体效果与获取方式
            tooltip.add(PerpetualPotatoes.effectsHeader());
            tooltip.add(PerpetualPotatoes.lead(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, 1)));
            for (int i = 2; i <= 7; i++) {
                tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, i)));
            }
            tooltip.add(PerpetualPotatoes.obtainHeader());
            // 获取前置要求与获取方式
            tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, 8)));
            tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, 9)));
            tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.TangxingPotato, 10)));
        } else {
            tooltip.add(PerpetualPotatoes.shiftHint());
        }
        PerpetualPotatoes.appendActiveStatus(tooltip, true);
    }
}
