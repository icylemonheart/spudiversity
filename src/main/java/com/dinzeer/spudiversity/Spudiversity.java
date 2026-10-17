package com.dinzeer.spudiversity;

import com.dinzeer.spudiversity.capability.SpAttachments;
import com.dinzeer.spudiversity.config.PotatoConfig;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.registry.*;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.Registrate;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

import java.lang.reflect.Field;

@Mod(Spudiversity.MODID)
public class Spudiversity {

    public static final String MODID = "spudiversity";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrate REGISTRATE = Registrate.create(MODID);

    static {
        try {
            Field f = AbstractRegistrate.class.getDeclaredField("defaultCreativeModeTab");
            f.setAccessible(true);
            f.set(REGISTRATE, null);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("无法关闭 Registrate 默认创造标签", e);
        }
    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public Spudiversity(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON,
                PotatoConfig.CONFIG_SPEC,
                "legendrelics/spudiversity/spudiversity-potato_config.toml");
        SpDataComponents.REGISTRY.register(modEventBus);
        SpAttachments.ATTACHMENT_TYPES.register(modEventBus);
        SpItemTabs.register(modEventBus);
        SpPotato.register();
        SpTwPotato.register();
        SpICFPotato.regsitry();
        SpEffect.REGISTRY.register(modEventBus);
        LangUtil.init();
        SpEntities.ENTITIES.register(modEventBus);
        SpItem.register();
        SpLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        SpuNetwork.register(modEventBus);
        SpSounds.REGISTRY.register(modEventBus);
        SpAvaPotato.register();
    }
}
