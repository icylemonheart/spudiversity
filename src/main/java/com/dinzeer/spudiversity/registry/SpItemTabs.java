package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.Spudiversity;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpItemTabs {

    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> spudiversity = REGISTRY.register("spudiversity",
            () -> CreativeModeTab.builder()
                    .backgroundTexture(Spudiversity.prefix("textures/gui/tab_item_search.png"))
                    .title(Component.translatable("item_group.spudiversity.spudiversity")).icon(Items.POTATO::getDefaultInstance)
                    .displayItems((parameters, tabData) -> {
                        tabData.accept(SpAvaPotato.NeutroniumPotato.get());
                        tabData.accept(SpAvaPotato.UniversePotato.get());
                        if (ModList.get().isLoaded("twilightforest")) {
                            tabData.accept(SpTwPotato.BloodPotato.get());
                            tabData.accept(SpTwPotato.ZombiePotato.get());
                            tabData.accept(SpTwPotato.FortificationWandPotato.get());
                            tabData.accept(SpTwPotato.DuskPotato.get());
                        }
                        if (ModList.get().isLoaded("iceandfire")) {
                            tabData.accept(SpICFPotato.LightningDragonPotato.get());
                            tabData.accept(SpICFPotato.FireDragonPotato.get());
                            tabData.accept(SpICFPotato.IceDragonPotato.get());
                        }
                    })
                    .build());

    public static void register(IEventBus a) {
        REGISTRY.register(a);
    }
}
