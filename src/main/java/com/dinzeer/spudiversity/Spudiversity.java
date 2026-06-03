package com.dinzeer.spudiversity;

import com.dinzeer.godfox.Godfox;
import com.dinzeer.spudiversity.item.compat.avaritia.NeutroniumPotato;
import com.dinzeer.spudiversity.item.compat.avaritia.UniversePotato;
import com.dinzeer.spudiversity.item.compat.godfox.AuspiciousPotato;
import com.dinzeer.spudiversity.item.compat.slashblade.SlashBladeCompat;
import com.dinzeer.spudiversity.item.compat.tw.BloodPotato;
import com.dinzeer.spudiversity.item.compat.tw.DuskPotato;
import com.dinzeer.spudiversity.data.SpCuriosTestProvider;
import com.dinzeer.spudiversity.data.SpGlobalLootModifierProvider;
import com.dinzeer.spudiversity.data.SpRecipeGen;
import com.dinzeer.spudiversity.event.compat.godfox.BloodFoxEvent;
import com.dinzeer.spudiversity.event.compat.godfox.SkyFoxPotatoEvent;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.registry.*;
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
import org.slf4j.Logger;

@Mod(Spudiversity.MODID)
@Mod.EventBusSubscriber
public class Spudiversity {

    public static final String MODID = "spudiversity";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrate REGISTRATE = Registrate.create(MODID);

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    public Spudiversity() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SpItemTabs.register(modEventBus);
        SpPotato.register();
        SpEffect.REGISTRY.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::gatherData);
        LangUtil.init();
        SpEntities.ENTITIES.register(modEventBus);
        SpItem.register();
        SpLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        if (ModList.get().isLoaded("slashblade")) {
            MinecraftForge.EVENT_BUS.addListener(SlashBladeCompat::DoslashBlade);
        }
        if (ModList.get().isLoaded("twilightforest")) {
            SpTwPotato.register();
            MinecraftForge.EVENT_BUS.register(DuskPotato.class);
            MinecraftForge.EVENT_BUS.register(BloodPotato.class);
        }
        if (ModList.get().isLoaded(Godfox.MODID)) {
            MinecraftForge.EVENT_BUS.register(BloodFoxEvent.class);
            MinecraftForge.EVENT_BUS.register(SkyFoxPotatoEvent.class);
            MinecraftForge.EVENT_BUS.register(AuspiciousPotato.class);
            MinecraftForge.EVENT_BUS.addListener(SlashBladeCompat::BladeStandHit);
        }
        if (ModList.get().isLoaded("iceandfire")){
            SpICFPotato.regsitry();
        }
        if (ModList.get().isLoaded("godfox")){
        SpGodFoxPotato.register();
        }
        SpuNetwork.register();
        SpSounds.REGISTRY.register(modEventBus);
        SpAvaPotato.register();

        if (ModList.get().isLoaded("avaritia")) {
            MinecraftForge.EVENT_BUS.register(NeutroniumPotato.class);
            MinecraftForge.EVENT_BUS.register(UniversePotato.class);
        }
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
        event.getGenerator().addProvider(event.includeServer(), new SpGlobalLootModifierProvider(event.getGenerator().getPackOutput()));
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, SpRecipeGen::buildRecipes);
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, SpRecipeGen::onRecipeGen);
    }
}
