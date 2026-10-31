package com.dinzeer.spudiversity.util;

import com.dinzeer.spudiversity.api.PerpetualPotatoRenderRegistry;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.client.ClientHooks;
import com.dinzeer.spudiversity.item.speaical.TangxingPotato;
import com.dinzeer.spudiversity.registry.SpAvaPotato;
import com.dinzeer.spudiversity.registry.SpICFPotato;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 永续土豆 tooltip 统一布局工具：
 * 顶部【永续土豆】标签 —— 中部台词 / 【效果】 / 【获取】分区 —— 底部当前生效状态
 */
public class PerpetualPotatoes {
    public static final String TAG = "tooltip.spudiversity.perpetual.tag";
    public static final String EFFECTS = "tooltip.spudiversity.perpetual.effects";
    public static final String OBTAIN = "tooltip.spudiversity.perpetual.obtain";
    public static final String SHIFT_HINT = "tooltip.spudiversity.perpetual.shift_hint";
    public static final String SHIFT_HINT_VALUES = "tooltip.spudiversity.perpetual.shift_hint_values";
    public static final String ACTIVE = "tooltip.spudiversity.perpetual.active";
    public static final String NONE = "tooltip.spudiversity.perpetual.none";
    public static final String OFF_ACTIVE = "tooltip.spudiversity.perpetual.off_active";
    public static final String OFF_NONE = "tooltip.spudiversity.perpetual.off_none";

    private static final Map<String, RegistryEntry<Item, ? extends Item>> MAIN_SLOT = new LinkedHashMap<>();
    private static final Map<String, RegistryEntry<Item, ? extends Item>> OFF_SLOT = new LinkedHashMap<>();

    static {
        MAIN_SLOT.put("tiny_water", SpPotato.TinyWaterPotato);
        MAIN_SLOT.put("crystal_potato", SpPotato.CrystalPotato);
        MAIN_SLOT.put("for_love", SpPotato.StarPotato);
        MAIN_SLOT.put("universe_potato", SpAvaPotato.UniversePotato);
        MAIN_SLOT.put("fire_dragon_potato", SpICFPotato.FireDragonPotato);
        MAIN_SLOT.put("ice_dragon_potato", SpICFPotato.IceDragonPotato);
        MAIN_SLOT.put("lightning_dragon_potato", SpICFPotato.LightningDragonPotato);
        OFF_SLOT.put(TangxingPotato.CRYSTAL_TYPE, SpPotato.TangxingPotato);
    }

    /** 【永续土豆】分类标签 */
    public static MutableComponent tag() {
        return Component.translatable(TAG).withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD);
    }

    /** 【效果】分区标题 */
    public static MutableComponent effectsHeader() {
        return Component.translatable(EFFECTS).withStyle(ChatFormatting.AQUA);
    }

    /** 【获取】分区标题 */
    public static MutableComponent obtainHeader() {
        return Component.translatable(OBTAIN).withStyle(ChatFormatting.GREEN);
    }

    /** 按下「Shift」查看具体效果与获取方式 */
    public static MutableComponent shiftHint() {
        return Component.translatable(SHIFT_HINT,
                Component.literal("Shift").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD)
        ).withStyle(ChatFormatting.DARK_GRAY);
    }

    /** 按下「Shift」查看具体数值 */
    public static MutableComponent shiftValuesHint() {
        return Component.translatable(SHIFT_HINT_VALUES,
                Component.literal("Shift").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD)
        ).withStyle(ChatFormatting.DARK_GRAY);
    }

    /** 神秘小故事 */
    public static MutableComponent flavor(String key, Object... args) {
        return Component.translatable(key, args).withStyle(ChatFormatting.GOLD);
    }

    /** 效果 */
    public static MutableComponent body(String key, Object... args) {
        return Component.translatable(key, args).withStyle(ChatFormatting.GRAY);
    }

    /** 效果引导 */
    public static MutableComponent lead(String key, Object... args) {
        return Component.translatable(key, args).withStyle(ChatFormatting.GOLD);
    }

    /** 空行分隔 */
    public static void spacer(List<Component> tooltip) {
        tooltip.add(Component.empty());
    }

    /** 取 MAIN 永续土豆类型对应的物品名 */
    public static Component nameOfMain(String crystalType) {
        RegistryEntry<Item, ? extends Item> entry = MAIN_SLOT.get(crystalType);
        if (entry != null) {
            return new ItemStack(entry.get()).getHoverName();
        }

        ItemStack external = PerpetualPotatoRenderRegistry.getMainStack(crystalType);
        if (!external.isEmpty()) {
            return external.getHoverName();
        }

        return Component.literal(crystalType).withStyle(ChatFormatting.DARK_GRAY);
    }

    /** 取 OFF 独立槽土豆类型对应的物品名 */
    public static Component nameOfOff(String crystalType) {
        RegistryEntry<Item, ? extends Item> entry = OFF_SLOT.get(crystalType);
        if (entry != null) {
            return new ItemStack(entry.get()).getHoverName();
        }

        ItemStack external = PerpetualPotatoRenderRegistry.getOffStack(crystalType);
        if (!external.isEmpty()) {
            return external.getHoverName();
        }

        return Component.literal(crystalType).withStyle(ChatFormatting.DARK_GRAY);
    }

    /**
     *  tooltip 底部显示当前永续土豆生效状态
     * @param showOffSlot 显示独立槽位（棠星土豆）状态
     */
    public static void appendActiveStatus(List<Component> tooltip, boolean showOffSlot) {
        Player player = ClientHooks.getLocalPlayer();
        spacer(tooltip);
        if (player == null) {
            return;
        }
        PlayerPermanentAbilities cap = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
        if (cap != null) {
            String active = cap.getActivePotato();
            if (active == null || active.isEmpty()) {
                tooltip.add(Component.translatable(NONE).withStyle(ChatFormatting.DARK_GRAY));
            } else {
                tooltip.add(Component.translatable(ACTIVE, nameOfMain(active)).withStyle(ChatFormatting.GRAY));
            }
            if (showOffSlot) {
                String off = cap.getActivePotatoOff();
                if (TangxingPotato.CRYSTAL_TYPE.equals(off)) {
                    tooltip.add(Component.translatable(OFF_ACTIVE, nameOfOff(off)).withStyle(ChatFormatting.GRAY));
                } else {
                    tooltip.add(Component.translatable(OFF_NONE).withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        }
    }
}
