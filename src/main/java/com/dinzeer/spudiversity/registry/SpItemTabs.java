package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.Spudiversity;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpItemTabs {




    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> spudiversity = REGISTRY.register("spudiversity",
            () -> CreativeModeTab.builder()
                    .withBackgroundLocation(Spudiversity.prefix("textures/gui/tab_item_search.png"))
                    .title(Component.translatable("item_group.spudiversity.spudiversity")).icon(Items.POTATO::getDefaultInstance)
                    .displayItems((parameters, tabData) -> {
                        if (ModList.get().isLoaded("twilightforest")){
                            tabData.accept(SpTwPotato.BloodPotato.get());
                            tabData.accept(SpTwPotato.ZombiePotato.get());
                            tabData.accept(SpTwPotato.FortificationWandPotato.get());
                            tabData.accept(SpTwPotato.DuskPotato.get());
                        }
                        if (ModList.get().isLoaded("iceandfire")){
                            tabData.accept(SpICFPotato.LightningDragonPotato.get());
                            tabData.accept(SpICFPotato.FireDragonPotato.get());
                            tabData.accept(SpICFPotato.IceDragonPotato.get());
                        }
                        if (ModList.get().isLoaded("godfox")){
                            tabData.accept(SpGodFoxPotato.BloodFoxPotato.get());
                            tabData.accept(SpGodFoxPotato.SkyFoxPotato.get());
                            tabData.accept(SpGodFoxPotato.AuspiciousPotato.get());
                        }
                        if (ModList.get().isLoaded("avaritia")){
                            tabData.accept(SpAvaPotato.NeutroniumPotato.get());
                            tabData.accept(SpAvaPotato.UniversePotato.get());
                        }
                    })
                    .build());




    public static void register(IEventBus a) {
        REGISTRY.register(a);
    }
}
