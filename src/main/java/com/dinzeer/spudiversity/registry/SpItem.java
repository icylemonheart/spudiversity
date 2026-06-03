package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.item.base.EdelweissUpgradeTemplate;
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
    public static final RegistryEntry<Item> rabbit = REGISTRATE.item(
                    "rabbit", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> tank = REGISTRATE.item(
                    "tank", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> bear_rabbit_tank = REGISTRATE.item(
                    "bear_rabbit_tank", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> paper_plane = REGISTRATE.item(
                    "paper_plane", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> the_end_core = REGISTRATE.item(
                    "the_end_core", Item::new
            ) .properties(p -> p.stacksTo(64)
                    .rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<EdelweissUpgradeTemplate> EdelweissUpgradeTemplate = REGISTRATE.item(
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
