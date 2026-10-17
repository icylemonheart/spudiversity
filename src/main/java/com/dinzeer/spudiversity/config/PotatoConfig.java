package com.dinzeer.spudiversity.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class PotatoConfig {
    public static final ModConfigSpec CONFIG_SPEC;
    public static final PotatoConfig CONFIG;
    public final ModConfigSpec.IntValue orePotatoTime, diamondCrystalTime;

    static {
        Pair<PotatoConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(PotatoConfig::new);
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }

    private PotatoConfig(ModConfigSpec.Builder builder) {
        builder.push("potato_time_config");
        orePotatoTime = builder
                .comment("矿石土豆的食用后效果的生效时长（单位：分钟） Ore potato effect duration in minutes",
                        "Default: 5")
                .defineInRange("orePotatoTime", 5, 0, Integer.MAX_VALUE);
        diamondCrystalTime = builder
                .comment("钻石水晶土豆的食用后效果的生效时长（单位：分钟） Diamond crystal potato effect duration in minutes",
                        "Default: 2")
                .defineInRange("diamondCrystalTime", 2, 0, Integer.MAX_VALUE);
        builder.pop();
    }
}
