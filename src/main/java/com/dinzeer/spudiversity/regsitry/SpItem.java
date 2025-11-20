package com.dinzeer.spudiversity.regsitry;

import com.dinzeer.spudiversity.Item.common.CopperPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpItem {
    public static final RegistryEntry<Item> WaveCore = REGISTRATE.item(
                    "wave_core", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> WaveCrystal = REGISTRATE.item(
                    "wave_crystal", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> Cyclone = REGISTRATE.item(
                    "cyclone", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> Joker = REGISTRATE.item(
                    "joker", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static void register() {}
}
