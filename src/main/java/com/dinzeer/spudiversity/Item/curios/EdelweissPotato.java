package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.registry.SpEffect;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.CharmPotatoes;
import com.dinzeer.spudiversity.util.DeathPreventionHandler;
import com.dinzeer.spudiversity.util.FlightUtil;
import com.dinzeer.spudiversity.util.lang.CnLangUtil;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
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
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.*;

@EventBusSubscriber
public class EdelweissPotato extends Item implements ICurioItem {
    private static final ResourceLocation EDELWEISS_POTATO_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "edelweiss_potato");
    private static final String SLOT_POTATO = "potato";
    private static final double ATTACK_DAMAGE_BONUS = 2.0;
    private static final double ATTACK_SPEED_BONUS = 0.1;

    private static final Map<UUID, Boolean> PREV_HAS = new HashMap<>();
    private static final Set<UUID> DIRTY = new HashSet<>();

    public EdelweissPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationModifier(1f)
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        FastMakeEntityUtil.FastEffect(slotContext.entity(), SpEffect.InvisibleWaiting, 1);
        if (slotContext.entity() instanceof ServerPlayer player) {
            FlightUtil.grantFlight(player, "edelweiss");
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof ServerPlayer player) {
            FlightUtil.grantFlight(player, "edelweiss");
            markDirty(player);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof ServerPlayer player) {
            markDirty(player);
            FlightUtil.revokeFlight(player, "edelweiss");
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        EDELWEISS_POTATO_ID,
                        ATTACK_DAMAGE_BONUS,
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
        modifiers.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        EDELWEISS_POTATO_ID,
                        ATTACK_SPEED_BONUS,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );
        return modifiers;
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            initBaseline(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        PREV_HAS.remove(event.getEntity().getUUID());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            initBaseline(player);
        }
    }

    private static void initBaseline(ServerPlayer player) {
        UUID uuid = player.getUUID();
        PREV_HAS.put(uuid, hasAnyEquipped(player));
    }

    private static void markDirty(ServerPlayer player) {
        UUID uuid = player.getUUID();
        if (DIRTY.add(uuid)) {
            player.getServer().execute(() -> {
                DIRTY.remove(uuid);
                refreshSlots(player);
            });
        }
    }

    private static void refreshSlots(Player player) {
        if (player.isRemoved()) return;
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            ICurioStacksHandler h = handler.getCurios().get(SLOT_POTATO);
            if (h == null) return;

            int currentTotal = h.getSlots();
            boolean hasAny = false;
            for (int i = 0; i < currentTotal; i++) {
                if (h.getStacks().getStackInSlot(i).is(SpPotato.EdelweissPotato.get())) {
                    hasAny = true;
                    break;
                }
            }

            UUID uuid = player.getUUID();
            Boolean prevHas = PREV_HAS.get(uuid);

            if (prevHas == null) {
                PREV_HAS.put(uuid, hasAny);
                return;
            }

            if (hasAny != prevHas) {
                PREV_HAS.put(uuid, hasAny);
                int delta = hasAny ? 1 : -1;
                CuriosApi.getSlotHelper().setSlotsForType(SLOT_POTATO, player, Math.max(1, currentTotal + delta));
            }
        });
    }

    private static boolean hasAnyEquipped(Player player) {
        boolean[] found = {false};
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            ICurioStacksHandler h = handler.getCurios().get(SLOT_POTATO);
            if (h == null) return;
            for (int i = 0; i < h.getSlots(); i++) {
                if (h.getStacks().getStackInSlot(i).is(SpPotato.EdelweissPotato.get())) {
                    found[0] = true;
                    break;
                }
            }
        });
        return found[0];
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (CurioCompat.hasItemInCurio(player, SpPotato.EdelweissPotato.get())) {
                DeathPreventionHandler.tryPreventDeath(player, event, SpPotato.EdelweissPotato.get(), 120);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext level, List<Component> tipList, TooltipFlag TooltipFlag) {
        for (int i = 0; i <= 2; i++) {
            tipList.add(CharmPotatoes.flavor(
                    CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato, i)));
        }
        tipList.add(CharmPotatoes.effectsHeader());
        for (int i = 4; i <= 7; i++) {
            tipList.add(CharmPotatoes.body(
                    CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato, i)));
        }
        tipList.add(CharmPotatoes.live(CharmPotatoes.EDELWEISS_ATTACK_DAMAGE,
                formatNumber(ATTACK_DAMAGE_BONUS)));
        tipList.add(CharmPotatoes.live(CharmPotatoes.EDELWEISS_ATTACK_SPEED,
                formatNumber(ATTACK_SPEED_BONUS * 100)));
    }

    private static String formatNumber(double value) {
        if (value == Math.floor(value)) {
            return String.valueOf((long) value);
        }
        return String.format("%.1f", value);
    }
}
