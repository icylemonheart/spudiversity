package com.dinzeer.spudiversity.item.common;

import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalPotato extends PotatoItemBase {
    private static final UUID CrystalPotatoUUID = UUID.nameUUIDFromBytes("CrystalPotato".getBytes());
    private static final Map<String, Long> cooldownMap = new HashMap<>();
    private static final long COOLDOWN_TIME = 1200;

    public CrystalPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()), "crystal_potato");
    }


    @Override
    protected void applyCrystalEffect(Player player) {

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
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
    public static void LivingUpdate(TickEvent.PlayerTickEvent event){
        Player player=event.player;
        player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store ->{
            if (store.getActivePotato().equals("crystal_potato")){
                applyCrystalPotato(player);
            }else {
                AttributeInstance armorAttr = player.getAttribute(Attributes.LUCK);
                armorAttr.removeModifier(CrystalPotatoUUID);
                AttributeInstance healAttr = player.getAttribute(Attributes.MAX_HEALTH);
                healAttr.removeModifier(CrystalPotatoUUID);
            }
        });
    }
    @SubscribeEvent
    public static void LivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player){
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store ->{
                if (store.getActivePotato().equals("crystal_potato")){
                    String playerKey = player.getStringUUID();
                    long currentTime = System.currentTimeMillis();

                    if (!cooldownMap.containsKey(playerKey) || currentTime - cooldownMap.get(playerKey) >= COOLDOWN_TIME) {
                        if (player.getFoodData().getFoodLevel() > 15) {
                            player.getFoodData().setFoodLevel(0);
                            cooldownMap.put(playerKey, currentTime);
                            event.setCanceled(true);
                            player.setHealth(player.getMaxHealth());
                        }
                    }
                }
            });
        }
    }

    private static void applyCrystalPotato(Player player) {
        AttributeInstance armorAttr = player.getAttribute(Attributes.LUCK);
        if (armorAttr != null) {
            armorAttr.removeModifier(CrystalPotatoUUID);
            armorAttr.addTransientModifier(new AttributeModifier(
                    CrystalPotatoUUID,
                    "CrystalPotato Potato Boost",
                    10,
                    AttributeModifier.Operation.ADDITION
            ));
        }
        AttributeInstance healAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (healAttr != null) {
            healAttr.removeModifier(CrystalPotatoUUID);
            healAttr.addTransientModifier(new AttributeModifier(
                    CrystalPotatoUUID,
                    "UniversePotato Potato Boost",
                    10,
                    AttributeModifier.Operation.ADDITION
            ));
        }
    }





}
