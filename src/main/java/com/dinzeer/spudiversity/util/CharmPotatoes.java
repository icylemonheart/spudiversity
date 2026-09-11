package com.dinzeer.spudiversity.util;

import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmPotatoes {
    public static final String EFFECTS = "tooltip.spudiversity.charm.effects";
    public static final String OBTAIN = "tooltip.spudiversity.charm.obtain";
    public static final String ZZZ_KILLED = "tooltip.spudiversity.charm.zzz_killed";
    public static final String ZZZ_NONE = "tooltip.spudiversity.charm.zzz_none";
    public static final String MEMORY_NOW = "tooltip.spudiversity.charm.memory_now";
    public static final String JIJIN_NOW = "tooltip.spudiversity.charm.jijin_now";
    public static final String SKYFOX_NOW = "tooltip.spudiversity.charm.skyfox_now";

    public static final class Spec {
        public final int[] flavor;
        public final int[] effects;
        public final int[] obtain;

        public Spec(int[] flavor, int[] effects, int[] obtain) {
            this.flavor = flavor;
            this.effects = effects;
            this.obtain = obtain;
        }
    }

    private static final Map<String, Spec> SPECS = new HashMap<>();

    static {
        put("auspicious_potato", new int[]{0}, new int[]{2, 3, 4, 5}, new int[]{});
        put("blood_potato", new int[]{0, 1}, new int[]{2, 3, 4}, new int[]{});
        put("build_potato", new int[]{0}, new int[]{2, 3, 4, 5, 6}, new int[]{});
        put("cake_king_potato", new int[]{0}, new int[]{1, 2, 3, 4}, new int[]{});
        put("dusk_potato", new int[]{0, 1}, new int[]{2}, new int[]{});
        put("firework_potato", new int[]{0}, new int[]{2}, new int[]{});
        put("geed_potato", new int[]{0}, new int[]{2, 3, 4, 5, 6, 7, 8, 9}, new int[]{10});
        put("loot_potato", new int[]{0}, new int[]{1}, new int[]{});
        put("loot_king_potato", new int[]{0}, new int[]{1, 2}, new int[]{});
        put("mysterious_potato", new int[]{0}, new int[]{2, 3, 4, 5, 6}, new int[]{});
        put("potato_36", new int[]{0}, new int[]{2, 3, 4}, new int[]{});
        put("undying_potato", new int[]{0}, new int[]{1}, new int[]{2});
        put("zzz_potato", new int[]{0}, new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{8});
    }

    private static void put(String path, int[] flavor, int[] effects, int[] obtain) {
        SPECS.put(path, new Spec(flavor, effects, obtain));
    }

    public static Spec spec(String path) {
        return SPECS.get(path);
    }

    public static String key(String path, int n) {
        return n == 0
                ? "item.spudiversity." + path + ".tooltip"
                : "item.spudiversity." + path + ".tooltip_" + n;
    }

    public static MutableComponent effectsHeader() {
        return Component.translatable(EFFECTS).withStyle(ChatFormatting.AQUA);
    }

    public static MutableComponent obtainHeader() {
        return Component.translatable(OBTAIN).withStyle(ChatFormatting.GREEN);
    }

    public static MutableComponent flavor(String key, Object... args) {
        return Component.translatable(key, args).withStyle(ChatFormatting.GOLD);
    }

    public static MutableComponent body(String key, Object... args) {
        return Component.translatable(key, args).withStyle(ChatFormatting.GRAY);
    }

    public static MutableComponent live(String key, Object... args) {
        Object[] colored = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            colored[i] = args[i] instanceof Component c ? c
                    : Component.literal(String.valueOf(args[i])).withStyle(ChatFormatting.AQUA);
        }
        return Component.translatable(key, colored).withStyle(ChatFormatting.DARK_GRAY);
    }

    public static void appendStandard(List<Component> tooltip, String path) {
        Spec spec = SPECS.get(path);
        if (spec == null) {
            return;
        }
        for (int n : spec.flavor) {
            tooltip.add(flavor(key(path, n)));
        }
        if (spec.effects.length > 0) {
            tooltip.add(effectsHeader());
            for (int n : spec.effects) {
                tooltip.add(body(key(path, n)));
                if ("potato_36".equals(path) && n == 2) {
                    Runtime runtime = Runtime.getRuntime();
                    long total = runtime.totalMemory();
                    long used = total - runtime.freeMemory();
                    long percent = total <= 0 ? 0 : Math.round(used * 100.0 / total);
                    double multiplier = 1 + (total <= 0 ? 0 : (double) used / total);
                    tooltip.add(live(MEMORY_NOW, percent, String.format("%.2f", multiplier)));
                }
            }
            if ("zzz_potato".equals(path)) {
                appendKilledBosses(tooltip);
            }
        }
        if (spec.obtain.length > 0) {
            tooltip.add(obtainHeader());
            for (int n : spec.obtain) {
                tooltip.add(body(key(path, n)));
            }
        }
    }

    private static void appendKilledBosses(List<Component> tooltip) {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        player.getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(cap -> {
            MutableComponent names = Component.empty();
            boolean first = true;
            first = appendBossName(names, first, cap.getKillWither(), "tooltip.spudiversity.boss.wither");
            first = appendBossName(names, first, cap.getKillOldGrudian(), "tooltip.spudiversity.boss.elder_guardian");
            first = appendBossName(names, first, cap.getKillEnderDragon(), "tooltip.spudiversity.boss.ender_dragon");
            first = appendBossName(names, first, cap.getKillWarden(), "tooltip.spudiversity.boss.warden");
            if (first) {
                tooltip.add(Component.translatable(ZZZ_NONE).withStyle(ChatFormatting.DARK_GRAY));
            } else {
                tooltip.add(Component.translatable(ZZZ_KILLED, names).withStyle(ChatFormatting.DARK_GRAY));
            }
        });
    }

    private static boolean appendBossName(MutableComponent names, boolean first, boolean killed, String key) {
        if (!killed) {
            return first;
        }
        if (!first) {
            names.append(Component.literal("、").withStyle(ChatFormatting.DARK_GRAY));
        }
        names.append(Component.translatable(key).withStyle(ChatFormatting.GREEN));
        return false;
    }
}
