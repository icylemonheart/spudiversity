package com.dinzeer.spudiversity.regsitry;

import com.dinzeer.godfox.Godfox;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpItemTabs {




    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> spudiversity = REGISTRY.register("spudiversity",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.spudiversity.spudiversity")).icon(Items.POTATO::getDefaultInstance)
                    .displayItems((parameters, tabData) -> {
                          if (ModList.get().isLoaded(Godfox.MODID)){
                              tabData.accept(SpGodFoxPotato.SkyFoxPotato.get());
                              tabData.accept(SpGodFoxPotato.BloodFoxPotato.get());
                          }
                    })
                    .build());




    public static void register(IEventBus a) {
        REGISTRY.register(a);
    }
}
