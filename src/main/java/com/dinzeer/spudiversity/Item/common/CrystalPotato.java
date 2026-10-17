package com.dinzeer.spudiversity.item.common;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber
public class CrystalPotato extends PotatoItemBase {
    private static final ResourceLocation CRYSTAL_POTATO_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "crystal_potato");
    private static final Map<String, Long> cooldownMap = new HashMap<>();
    private static final long COOLDOWN_TIME = 1200;

    public CrystalPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()), "crystal_potato");
    }

    @Override
    protected void applyCrystalEffect(Player player) {

    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(PerpetualPotatoes.tag());
        tooltip.add(PerpetualPotatoes.flavor(LangUtil.FastMakeItemTooltip(SpPotato.CrystalPotato, 0)));
        tooltip.add(PerpetualPotatoes.effectsHeader());
        for (int i = 1; i <= 3; i++) {
            tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.CrystalPotato, i)));
        }
        tooltip.add(PerpetualPotatoes.obtainHeader());
        tooltip.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.CrystalPotato, 4)));
        PerpetualPotatoes.appendActiveStatus(tooltip, false);
    }

    @SubscribeEvent
    public static void LivingUpdate(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if (player.level().isClientSide) return;
        PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
        if (store == null) return;
        if (store.getActivePotato().equals("crystal_potato")){
            applyCrystalPotato(player);
        }else {
            AttributeInstance armorAttr = player.getAttribute(Attributes.LUCK);
            if (armorAttr != null) armorAttr.removeModifier(CRYSTAL_POTATO_ID);
            AttributeInstance healAttr = player.getAttribute(Attributes.MAX_HEALTH);
            if (healAttr != null) {
                healAttr.removeModifier(CRYSTAL_POTATO_ID);
                if (player.getHealth() > player.getMaxHealth()) {
                    player.setHealth(player.getMaxHealth());
                }
            }
        }
    }

    @SubscribeEvent
    public static void LivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player){
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store == null) return;
            if (store.getActivePotato().equals("crystal_potato")){
                String playerKey = player.getStringUUID();
                long currentTime = System.currentTimeMillis();

                if (!cooldownMap.containsKey(playerKey) || currentTime - cooldownMap.get(playerKey) >= COOLDOWN_TIME) {
                    if (player.getFoodData().getFoodLevel() > 15) {
                        player.getFoodData().setFoodLevel(0);
                        player.getFoodData().setSaturation(0);
                        player.getFoodData().setExhaustion(0);
                        cooldownMap.put(playerKey, currentTime);
                        event.setCanceled(true);
                        player.setHealth(1.0f);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        cooldownMap.remove(event.getEntity().getStringUUID());
    }

    private static void applyCrystalPotato(Player player) {
        AttributeInstance armorAttr = player.getAttribute(Attributes.LUCK);
        if (armorAttr != null && armorAttr.getModifier(CRYSTAL_POTATO_ID) == null) {
            armorAttr.addTransientModifier(new AttributeModifier(
                    CRYSTAL_POTATO_ID,
                    10,
                    AttributeModifier.Operation.ADD_VALUE
            ));
        }
        AttributeInstance healAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (healAttr != null && healAttr.getModifier(CRYSTAL_POTATO_ID) == null) {
            float oldMax = player.getMaxHealth();
            float oldHealth = player.getHealth();
            healAttr.addTransientModifier(new AttributeModifier(
                    CRYSTAL_POTATO_ID,
                    10,
                    AttributeModifier.Operation.ADD_VALUE
            ));
            float newMax = player.getMaxHealth();
            if (oldMax > 0 && oldHealth >= oldMax - 0.01f) {
                player.setHealth(newMax);
            } else if (oldMax > 0) {
                player.setHealth(Math.min(oldHealth * newMax / oldMax, newMax));
            }
        }
    }
}
