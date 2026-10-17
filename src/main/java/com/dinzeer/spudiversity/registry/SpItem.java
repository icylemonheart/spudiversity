package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.item.base.EdelweissUpgradeTemplate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpItem {
    public static final RegistryEntry<Item, Item> WaveCore = REGISTRATE.item(
                    "wave_core", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> WaveCrystal = REGISTRATE.item(
                    "wave_crystal", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> Cyclone = REGISTRATE.item(
                    "cyclone", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> Joker = REGISTRATE.item(
                    "joker", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> rabbit = REGISTRATE.item(
                    "rabbit", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> tank = REGISTRATE.item(
                    "tank", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> bear_rabbit_tank = REGISTRATE.item(
                    "bear_rabbit_tank", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> paper_plane = REGISTRATE.item(
                    "paper_plane", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, Item> the_end_core = REGISTRATE.item(
                    "the_end_core", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item, EdelweissUpgradeTemplate> EdelweissUpgradeTemplate = REGISTRATE.item(
                    "edelweiss_upgrade_template", p-> new EdelweissUpgradeTemplate()
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static void register() {
        SpPotato.ToolTipBlackList.add(EdelweissUpgradeTemplate);
        SpPotato.ToolTipBlackList.add(paper_plane);
    }
}
