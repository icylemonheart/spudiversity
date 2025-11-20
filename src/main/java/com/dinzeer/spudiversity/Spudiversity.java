package com.dinzeer.spudiversity;

import com.dinzeer.godfox.Godfox;
import com.dinzeer.spudiversity.Item.compat.slashblade.SlashBladeCompat;
import com.dinzeer.spudiversity.data.SpCuriosTestProvider;
import com.dinzeer.spudiversity.data.SpRecipeGen;
import com.dinzeer.spudiversity.event.compat.godfox.BloodFoxEvent;
import com.dinzeer.spudiversity.event.compat.godfox.SkyFoxPotatoEvent;
import com.dinzeer.spudiversity.network.PlayTotemAnimationPacket;
import com.dinzeer.spudiversity.regsitry.*;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Spudiversity.MODID)
public class Spudiversity {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "spudiversity";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrate REGISTRATE = Registrate.create(MODID);
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "main"),
            () -> "1.0",
            s -> true,
            s -> true
    );
    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    public Spudiversity() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // 注册创造模式标签页
        SpItemTabs.register(modEventBus);
        SpPotato.register();
        SpEffect.REGISTRY.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::gatherData);
        LangUtil.init();
        SpEntities.ENTITIES.register(modEventBus);
        SpItem.register();
        if (ModList.get().isLoaded("slashblade")) {
            MinecraftForge.EVENT_BUS.addListener(SlashBladeCompat::DoslashBlade);
        }
        if (ModList.get().isLoaded(Godfox.MODID)) {
            MinecraftForge.EVENT_BUS.register(BloodFoxEvent.class);
            MinecraftForge.EVENT_BUS.register(SkyFoxPotatoEvent.class);
            MinecraftForge.EVENT_BUS.addListener(SlashBladeCompat::BladeStandHit);
        }
        if (ModList.get().isLoaded("iceandfire")){
            SpICFPotato.regsitry();
        }
        SpGodFoxPotato.register();
        CHANNEL.registerMessage(0, PlayTotemAnimationPacket.class,
                PlayTotemAnimationPacket::encode,
                PlayTotemAnimationPacket::decode,
                PlayTotemAnimationPacket::handle);


//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    public void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(
                // Tell generator to run only when server data are generating
                event.includeServer(),
                new SpCuriosTestProvider(
                        MODID,
                        event.getGenerator().getPackOutput(),
                        event.getExistingFileHelper(),
                        event.getLookupProvider()
                )
        );
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, SpRecipeGen::buildRecipes);
    }
}
