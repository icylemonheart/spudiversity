package com.dinzeer.spudiversity.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ClientConfig {
    public static final ModConfigSpec SPEC;
    public static final ClientConfig CONFIG;

    public final ModConfigSpec.BooleanValue renderPotatoCharms;
    public final ModConfigSpec.BooleanValue renderPerpetualPotatoes;

    static {
        Pair<ClientConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(ClientConfig::new);
        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    private ClientConfig(ModConfigSpec.Builder builder) {
        builder.push("rendering");
        renderPotatoCharms = builder
                .comment("是否渲染土豆护符", "Whether to render potato charms")
                .define("render_potato_charms", true);
        renderPerpetualPotatoes = builder
                .comment("是否渲染永续土豆", "Whether to render perpetual potatoes")
                .define("render_perpetual_potatoes", true);
        builder.pop();
    }
}
