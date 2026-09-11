package com.dinzeer.spudiversity.util.lang;

import com.dinzeer.spudiversity.registry.*;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class LangUtil {
    public static void init(){
        REGISTRATE.addRawLang("item_group.spudiversity.spudiversity", "Potato Feast");

        // 永续土豆 tooltip 共享布局文案
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.tag", "[Perpetual Potato]");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.effects", "[Effects]");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.obtain", "[Acquisition]");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.shift_hint", "Press %s to view effects and acquisition");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.shift_hint_values", "Press %s to view exact values");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.active", "Active perpetual potato: %s");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.none", "No perpetual potato is currently active");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.off_active", "Independent slot active: %s");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.off_none", "Independent slot inactive");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.effects", "[Charm Effects]");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.obtain", "[Acquisition]");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.zzz_killed", "Bosses slain: %s");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.zzz_none", "No bosses slain yet");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.wither", "Wither");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.elder_guardian", "Elder Guardian");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.ender_dragon", "Ender Dragon");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.warden", "Warden");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.memory_now", "Current memory usage: %s%%, damage x%s");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.jijin_now", "Beneficial effects: %s -> +%s%% attack, +%s armor toughness, +%s%% max health");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.skyfox_now", "Current luck: %s -> -%s damage taken, +%s attack damage");

        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip", "\"Seems to be the staple food of Wither Skeletons\"");
        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip_1", "Grants \"Furnace Blessing\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip_2", "Automatically smelts ores while mining during the effect");

        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip", "\"The taste... acceptable?\"");
        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip_1", "Grants \"Lightning Rod Constitution\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip_2", "Attracts lightning but reduces lightning damage by 80% during the effect");

        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip", "\"Tastes like potato stew with carrots, but isn't this roasted?\"");
        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip_1", "Grants \"Night Vision\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip_2", "Grants \"Jump Boost\" effect after consumption");

        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip", "\"Rare treasure indeed\"");
        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip_1", "Grants \"Water Breathing\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip_2", "Cannot be used as an enchanting medium");

        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip", "\"Let the storm rage on!\"");
        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip_1", "Grants \"Resistance II\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip_2", "Lasts 20 seconds");

        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip", "\"Legend says the first one came from a witch who didn't want to drop redstone\"");
        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip_1", "Grants \"Speed II\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip_2", "Lasts 1 minute");

        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip", "\"Hmm, rare treasure indeed\"");
        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip_1", "Grants \"Regeneration\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip_2", "Grants \"Resistance\" effect after consumption");

        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip", "\"Even more expensive than the Nether Star: a potato made from it\"");
        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip_1", "Grants \"Resistance III\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip_2", "Lasts 30 seconds");

        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip", "\"Is this even edible?\"");
        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip_1", "Grants \"Resistance II\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip_2", "Grants \"Fire Resistance\" effect after consumption");

        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip", "\"Embrace rebirth, you invisible hand!\"");
        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip_1", "Grants \"Village Sun\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip_2", "Reduces villager trade prices by 80% during the effect");

        REGISTRATE.addRawLang("item.spudiversity.enchanted_potato.tooltip", "\"This is expensive\"");
        REGISTRATE.addRawLang("item.spudiversity.enchanted_potato.tooltip_1", "Grants the same effects as an Enchanted Golden Apple after consumption");

        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip", "\"Luxurious enjoyment\"");
        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip_1", "Grants \"Resistance IV\" effect after consumption");
        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip_2", "Lasts 1 minute");

        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip", "\"The pinnacle of potatoes!\"");
        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip_1", "Grants all effects of a level 2 beacon after consumption");
        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip_2", "Lasts 3 minutes");

        REGISTRATE.addRawLang("item.spudiversity.apple_potato.tooltip", "\"Potato or apple? Doesn't matter\"");
        REGISTRATE.addRawLang("item.spudiversity.apple_potato.tooltip_1", "Obtained by harvesting potato crops with a chance");

        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip", "\"You'd better be a potato\"");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_1", "Clears all potion effects upon consumption");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_2", "Then grants long-duration \"Night Vision\"");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_3", "Dropped by villagers/players upon death with a chance");

        REGISTRATE.addRawLang("item.spudiversity.ender_potato.tooltip", "\"Edible Eye of Ender\"");
        REGISTRATE.addRawLang("item.spudiversity.ender_potato.tooltip_1", "Same effect as Chorus Fruit upon consumption");

        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip", "\"See you tomorrow!\"");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_0", "Hold Shift + Right-click on a block to record the coordinate above it");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_1", "Consume to teleport above that block");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_2", "Recorded coordinate: X-%s Y-%s Z-%s");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_3", "No coordinate recorded");

        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip", "\"Feibi? Feibijubi\"");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_1", "\"Temporal Barrier\"");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_2", "Grants Resistance II (5s) + Regeneration II (2s) upon consumption");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_3", "And instantly inflicts \"Time Stop\" on all creatures within 5 blocks (except players):");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_4", "Grants Slowness V + Weakness V");

        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip", "Warning! Warning! Fission index of this item exceeds limit");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_1", "After consuming this legendary potato, you will gain a transcendent body:");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_2", "Grants Absorption V");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_3", "Repairs equipment durability every second");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_4", "Automatically counterattacks nearby enemies");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_5", "Enjoy!");

        REGISTRATE.addRawLang("item.spudiversity.cake_potato.tooltip", "\"Happy Birthday!\"");
        REGISTRATE.addRawLang("item.spudiversity.cake_potato.tooltip_1", "Just a very tasty potato");

        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip", "\"Accelerate, until all potatoes bloom and bear fruit!\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_1", "\"Potato\" \"Potato Flower\" \"Potato\" \"Sprout\" \"Potato\" \"Blood Dance\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_2", "\"Potato\" \"Ubisoft Gate\" \"Potato\" \"Server\" \"Potato\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_3", "\"Deep-fry\" \"Stew\" \"Potato\" \"Roast\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_4", "14 riddles have arrived");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_5", "\"Made in Potato!\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_6", "While worn, quickly restores durability of the held item");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_7", "Also, you accelerate");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_8", "Crafted at a crafting table: 4x Nether Star, 3x Clock");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_9", "1x Enchanted Potato, 1x Nether Star Potato");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_10", "Special evolution: wear Cake King and Dual Flavor potatoes, hold a Tidal Potato in your off-hand, then eat to obtain \"Made in Potato: Made in Heaven\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_alt", "Press %s to show item description");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,0), "\"I am the sweets! Mortals, offer your praises to the potato!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,1), "- \"Potato Sovereignty!\": Permanently gain all beacon effects (max level beacon)");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,2), "- \"Satiety is Bliss\": Automatically replenishes hunger when depleted");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,3), "- \"Happiness Takes Flight\": Grants flight ability");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,4), "A full beacon provides: Speed II, Haste II, Resistance II, Jump Boost II, Regeneration II, Strength II; refills hunger to 20 and restores 5 saturation when depleted");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,0), "\"Well, sea salt flavor, but inedible\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,1), "While holding this potato, your mining speed is doubled");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,2), "Right-click while holding (main or off-hand) to release a spherical sonic wave");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,3), "The sonic wave deals no damage but applies Slowness III (3s) + Glowing (10s) to mobs");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,4), "If you have a pickaxe in the off-hand and are underwater, the sonic wave can break blocks");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,0), "\"Steve? No, call me Shakyamuni Potato\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,1), "Upon consumption, enter a meditative sitting state");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,2), "During this state, slowly restore hunger to nearby allies centered on yourself");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,0), "W B X!");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,1), "While worn, gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,2), "\"Strength II\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,3), "\"Haste II\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,4), "\"Speed I\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,5), "\"Regeneration I\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,6), "Attacks unleash an air blade with 25% damage");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,1), "Now, Acceleration becomes even faster due to its birth");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,2), "At this moment, even time can be accelerated");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,3), "Press %s to start time acceleration");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.InfinitePotato,0), "\"Infinite eating, but raw\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.InfinitePotatoCooked,0), "\"Infinite eating, but cooked\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,0), "\"The potato is treasure itself!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,1), "Wearing this potato grants \"Super Strength\": attack damage x1.1");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.PotatoSalaed,0), "\"Very healthy\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,0), "\"Legend says this potato was grown by the wandering trader who volunteered labor for the evoker\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,1), "Same effect as Totem of Undying");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,2), "Obtained from wandering trader trades");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.HONEY_POTATO,0), "\"Sweet\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SonicPotato,0), "\"This potato feels... alive\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SonicPotato,1), "While worn, press %s to unleash sonic attacks");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,0), "\"Legend says this masterpiece comes from a hermit among the bamboo grove (or a particularly smart panda?), steaming the potato inside a fresh bamboo tube, allowing it to fully absorb the bamboo's freshness and resilience.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,1), "Upon consumption, gain the \"Bamboo Blessing\" status");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CookCookedPotato,0), "\"Surely inedible, so here, have it~\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CookCookedPotato,1), "Can be fed to entities to deal magic damage (ignores invincibility frames)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,0), "\"A potato of strange color. The skin is a silent purple, but a warm yellow peeks from the eyes.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,1), "\"It has no taste, no aroma, yet holding it brings an odd calm, as if holding a heart that no longer beats.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,2), "\"You are certain it comes from a very important person. Their figure is blurry in your memory, like a never-ending snowstorm. The only thing clear is the words you never said when they left.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,3), "\"... Eat it. Perhaps you'll find answers, or oblivion.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,4), "'Eat it, it's useless, but... it fills the belly.'");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,5), "A voice echoes in your mind, gender indistinct, carrying a smile and a trace... of barely perceptible weariness.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,6), "The voice causes a sharp sting, like an ice pick into your temple, but then comes a huge, suffocating emptiness.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,7), "Who are you? Who was I?");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,8), "What happened between us");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,9), "That such a silent potato is needed as the last anchor against the inevitable, complete oblivion?");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,0), "\"It really looks like ice. And tastes like it too.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,1), "Eating this potato grants the \"Extreme Cold Power\" effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,2), "Obtained by right-clicking a snow golem with a baked potato");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,0), "\"A potato no longer content to be buried in the soil. It craved heat, transformation, and finally threw itself into the most turbulent lava of the earth's veins.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,1), "\"The blazing fire reshaped its form, and boiling energy filled its core.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,2), "\"It was reborn from the fire.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,3), "Upon consumption, gain \"Ember End\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,0), "\"A potato born from pollination by the strange pollen of the Flower Forest. It has an unusual soft pinkish-white skin, and even a few never-wilting cherry blossom petals on its surface.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,1), "\"It emits a sweet floral scent, as if the essence of spring itself has been condensed into this small tuber.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,2), "Upon consumption, gain \"Sakura Rhapsody\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,0), "\"A tuber not planted, but condensed from the 'possibility' at the dawn of the world. Its skin is a flowing starry sky, its sprouts are shimmering laws.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,1), "Upon consumption, gain \"Transcend Narrative\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,2), "\"Appendix: This item cannot be obtained normally.\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,0), "\"A dark, cold-to-the-touch potato, its body covered with vein-like tentacles.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,1), "\"It exudes an ominous aura, and when held, you seem to hear countless whispers.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,2), "Upon consumption, gain \"Void of the End\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,3), "\"... Come, listen to me...\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,4), "\"... You're lonely too, aren't you?\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,5), "\"... There are people you can't forgive, right?\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,6), "\"... Do you also long to make the world feel your pain?\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,7), "\"... Eat me. We... become one...\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Cyclone,0), "\"Cyclone!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Cyclone,1), "30% chance to drop when a wither kills any creature at 200 meters height");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Joker,0), "\"Joker!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Joker,1), "Chance to drop when killing a creature with over 100 HP using an air kill");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.WaveCore,0), "Dropped by killing an Elder Guardian");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.WaveCrystal,0), "");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,0), "\"Dragon Blood Potato forged from the blood of the Fire Dragon\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,1), "Upon consumption, become a Fire Dragon scion and permanently gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,2), "1. Magma Walker");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,3), "2. Contact with lava that doesn't turn into magma blocks grants Haste I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,4), "3. Permanent Fire Resistance");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,5), "4. Auto-smelting");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,6), "5. Max health increased by 50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,7), "6. Water within 5 blocks centered on you evaporates");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,0), "\"Dragon Blood Potato forged from the blood of the Ice Dragon\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,1), "Upon consumption, become an Ice Dragon scion and permanently gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,2), "1. Frost Walker");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,3), "2. Immunity to Slowness");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,4), "3. Attacking a creature freezes it in place for 3 seconds (ineffective against bosses) and removes fire effects from it");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,5), "4. Final damage increased by 15% while in water");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,6), "5. Max health increased by 50%");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,0), "\"Dragon Blood Potato forged from the blood of the Lightning Dragon\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,1), "Upon consumption, become a Lightning Dragon scion and permanently gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,2), "1. Chance to summon a lightning bolt on a creature hit by your attack");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,3), "2. Chance greatly increased during thunderstorms or near lightning rods");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,4), "3. Moving builds up static charge; at full charge, your next attack deals knockback and paralysis (Blindness + Slowness) to nearby enemies in a small area");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,5), "4. Gain Speed II while moving during non-clear weather");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,6), "5. Max health increased by 50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,7), "6. The lower your health, the faster your attack speed and movement speed (up to 25% increase)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,0), "\"Blessed potato from the Celestial Fox, sweet in taste and nice to carry around\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,1), "While worn:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,2), "Provides 18% healing bonus when saturation is full");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,3), "Provides damage reduction equal to 18% of luck value");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,4), "Provides damage increase equal to 18% of luck value");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,5), "While wearing this accessory and having the Divine Sword \"Celestial Fox\" in your inventory, continuously gain the \"Village Sun\" effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,6), "Obtained by right-clicking the Divine Sword \"Celestial Fox\" with an Emerald Potato");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,0), "\"Blessed potato from the Blood Fox, spicy in taste and uncomfortable to carry\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,1), "While worn:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,2), "Your melee damage becomes magic damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,3), "On hit, target suffers \"Bleeding\" for 3 seconds, taking 2 damage per second. Effect does not stack but refreshes duration");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,4), "You deal 40% increased damage to creatures under \"Bleeding\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,5), "After killing 10 or more enemies consecutively, you enter the \"Bloodthirst\" state for 5 seconds");

        REGISTRATE.addRawLang( "effect.spudiversity.copper_potato", "Lightning Rod");
        REGISTRATE.addRawLang( "effect.spudiversity.furnace_blessing", "Furnace Blessing");
        REGISTRATE.addRawLang( "effect.spudiversity.village_sun", "Village Sun");
        REGISTRATE.addRawLang( "effect.spudiversity.no_mobs_booby", "Invincible Body");
        REGISTRATE.addRawLang( "effect.spudiversity.for_haven", "Made in Heaven · Heaven Ascension");
        REGISTRATE.addRawLang( "effect.spudiversity.rain_bow", "Zen Mind");
        REGISTRATE.addRawLang( "effect.spudiversity.bamboo_give", "Bamboo Blessing");
        REGISTRATE.addRawLang( "effect.spudiversity.ice_potato", "Extreme Cold Power");
        REGISTRATE.addRawLang( "effect.spudiversity.blaze_end", "Ember End");
        REGISTRATE.addRawLang( "effect.spudiversity.sakura_fantasy", "Sakura Rhapsody");
        REGISTRATE.addRawLang( "effect.spudiversity.every_thing", "Transcend Narrative");
        REGISTRATE.addRawLang( "effect.spudiversity.we_are_null", "Void of the End");
        REGISTRATE.addRawLang( "effect.spudiversity.wave_blood", "Bleeding");
        REGISTRATE.addRawLang( "effect.spudiversity.blood_king", "Bloodthirst");

        REGISTRATE.addRawLang( "stack.spudiversity.blood_king_cd", "\"Bloodthirst Battle Intent\"");

        REGISTRATE.addRawLang( "curios.identifier.potato", "Potato Charm");
        REGISTRATE.addRawLang( "curios.modifiers.potato", "When worn as a Potato Charm");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,2), "Also, creatures you kill drop double loot");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,0), "At the moment the Wither is defeated, the Nameless Potato in your off-hand loses all its luster.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,1), "It becomes gray, cold, like an ordinary stone.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,2), "It exhausted its power to exchange for fragments of memory — a figure leaving in the snow, an unsaid goodbye.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,3), "The thought of End coordinates 0-0-0 surfaces in your mind.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,0), "You journeyed to the world's end, to the center of the coordinates.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,1), "You consumed that dull potato, unlocking the final riddle of potato and memory.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,2), "Upon understanding all truths, tears flowed involuntarily.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,3), "The tear-soaked potato remnants transform into a tiny star radiating eternal light.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,4), "This star is named \"Tamina\".");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,5), "Holding it, you will never be lost to oblivion.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,6), "She is the companion who sacrificed everything for you; this is proof of her existence.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,7), "Consume it, %s, and you shall receive her blessing: no longer hungry, and stronger.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,8), "1. Tamina's blessing: never go hungry; your food level always stays full");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,9), "Obtained by consuming the Noname Potato Dark within 5 blocks of (0, 0, 0) in the End");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,0), "\"Hehehe, hahaha\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,1), "Seems to be a potato imported from the origin and then re-exported");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,2), "Quite high in sugar, eat less if trying to lose weight");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,3), "Grants Regeneration III upon consumption");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,0), "Right-click while holding to consume hunger and fire a dragon breath projectile");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,1), "[Ingredients]");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,2), "· 59% End Stone dust");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,3), "· 40% Chorus fruit chewed by the dragon");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,4), "· 1% Ender Dragon saliva (because it's too delicious)");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,5), "[Warm Tips]");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,6), "■ It's normal if ordinary potatoes cast jealous glances");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,7), "■ Final interpretation rights belong to the Ender Dragon (it says next time it'll add more dragon breath)");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,8), "Dragon-slaying commemorative item");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,9), "Obtained after earning the achievement \"Free the End\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.FortificationWandPotato,0), "\"This potato graduated from the magic academy's shield department!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.FortificationWandPotato,1), "A potato infused with a Lich's shield magic");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.FortificationWandPotato,2), "Upon consumption, gain 5 Lich shields that block 5 instances of damage");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.DuskPotato,0), "\"This potato is also the Potato King!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.DuskPotato,1), "A potato fused with a Lich's dusk magic");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.DuskPotato,2), "On attack, chance to fire a Dusk Pearl; small chance to fire a Fireball");

        REGISTRATE.addRawLang( "effect.spudiversity.undead_transformation_effect", "Outcast");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.ZombiePotato,0), "\"Brains! Brains!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.ZombiePotato,1), "Upon consumption, gain \"Outcast\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.ZombiePotato,2), "Undead mobs no longer target you, but neutral mobs will target you");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,0), "\"Rusty-flavored potato\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,1), "A potato fused with a Lich's blood magic");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,2), "Each attack drains life");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,3), "Grants buffs when equipped");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,4), "Upon death, prevents death once and restores half health, then enters a 60s cooldown");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,2), "Obtained by right-clicking a panda eating bamboo with an Apple Potato");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,6), "Right-click \"Blood Fox\" with a Diamond Potato");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,3), "In the Flower Forest biome, using bone meal on a potato sprout has a very small chance to yield 1-2");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.rabbit,0), "\"rabbit\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.tank,0), "\"tank\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.rabbit,1), "Obtained when a rabbit is killed by explosion damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.tank,1), "Obtained when a turtle is killed by explosion damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.bear_rabbit_tank,0), "\"Bubbly Fullness\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.bear_rabbit_tank,1), "You can feel it's carbonated");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,0), "\"A hero can't flee from battle\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,1), "Effects provided:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,2), "1. Extreme Speed I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,3), "2. Extreme Jump I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,4), "3. Regeneration I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,5), "4. Resistance II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,6), "5. Melee attacks have a 30% chance to trigger an explosion damage on nearby enemies (explosion damage cannot trigger itself)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,0), "\"Good morning! Potato!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,1), "Said to be extremely powerful");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,2), "While worn, grants Resistance I, Night Vision, and Strength I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,3), "After killing a Wither, additionally gain Regeneration I and Health Boost I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,4), "After killing an Elder Guardian, additionally gain Water Breathing and Jump Boost");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,5), "After killing the Ender Dragon, additionally gain Speed, Absorption, and Slow Falling");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,6), "After killing a Warden, increase the level of Resistance and Strength granted by the Zzz Potato by 1");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,7), "Additionally, after killing a Warden, attacks inflict Blindness and Slowness.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,8), "Obtained by right-clicking a bed while holding a Nether Star Potato");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,0), "\"Here we go!!!!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,1), "Effects provided:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,2), "1. Resistance II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,3), "2. Absorption I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,4), "3. Saturation I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,5), "4. Strength I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,6), "5. Speed I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,7), "6. Night Vision");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,8), "7. While worn, nearby dogs gain Strength effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,9), "8. Immunity to Darkness");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,10), "Currently unobtainable");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootPotato,0), "\"Gorgeous! This diamond luster is the potato's nobility!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootPotato,1), "While wearing this potato, kills count as two kills");

        REGISTRATE.addRawLang( "effect.spudiversity.glass_type", "Glass Walking");
        REGISTRATE.addRawLang( "effect.spudiversity.radiant_light", "Fluorescence");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,0), "\"Hard to chew, like glass\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,1), "Upon consumption, gain \"Glass Walking\" effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,2), "Allows movement inside glass during the effect; your projectiles can pass through glass");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,3), "Be careful, you become as fragile as glass");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlowStonePotato,0), "\"Feels like my teeth are glowing\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlowStonePotato,1), "Upon consumption, gain \"Fluorescence\" effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlowStonePotato,2), "Your entire body glows");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TarianPotato,0), "\"Listen to the End shatter, the sound of spawn point devastation\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TarianPotato,1), "Upon consumption, triggers:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TarianPotato,2), "\"Self-centered Instant Meteor Shower\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,0), "\"Potato Security Guard at your service\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,1), "While worn, gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,2), "1. Damage increased by 1% for every 1% memory usage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,3), "2. 25% chance to trigger \"Damage Boost Cleanup\" on attack");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,4), "3. Non-magic, non-fire attacks deal an additional 5% magic damage");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.FireworkPotato,0), "\"The trail of fireworks is also the potato's elegance\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.FireworkPotato,1), "While worn, gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.FireworkPotato,2), "Firing a bow after drawing adds two firework rockets");

        REGISTRATE.addRawLang("item.spudiversity.alchemist_potato.tooltip_alt", "Press %s to view stored potion effects");

        REGISTRATE.addRawLang("tooltip.alchemist_potato.stored", "§7Injected potions:");
        REGISTRATE.addRawLang("tooltip.alchemist_potato.empty", "None");
        REGISTRATE.addRawLang("message.alchemist_potato.inject_success", "§aPotion successfully injected into potato!");
        REGISTRATE.addRawLang("message.alchemist_potato.full","§cPotato is full! Cannot inject more potions.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AlchemistPotato,0), "\"Brewing, Forging, Potato Alchemy!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AlchemistPotato,1), "Hold in off-hand, hold a splash potion in main hand and right-click to inject up to 10 charges");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AlchemistPotato,2), "When worn as accessory, each attack selects one of the 10 injection slots and applies it to the target");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,0), "\"Noble crystal, the VIP among potatoes!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,1), "Upon consumption, gain:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,2), "1. Resistance IV");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,3), "2. Strength II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,4), "3. Regeneration III");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,5), "Obtained by right-clicking a budding amethyst while holding a Diamond Potato");


        REGISTRATE.addRawLang( "effect.spudiversity.invisible_waiting", "Invisible Vigil");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,0), "\"I hope I can make you proud, I hope I didn't let you down\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,1), "\"... Don't ... be sad ...\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,2), "Seems to be a special potato formed by fusing a message left by some girl before activating something with a potato");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,3), "While worn, gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,4), "1. You can fly creatively");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,5), "2. Continuously gain the \"Invisible Vigil\" effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,6), "3. Can equip an additional \"Potato Charm\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,7), "4. Prevents death once (full heal, 120s cooldown)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EmberPotato,0), "\"Burn until dawn\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EmberPotato,1), "Upon consumption, gain the effect \"Burn with Dawn\"");

        REGISTRATE.addRawLang( "effect.spudiversity.burn_with_dawn", "Burn with Dawn");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,0), "\"Help! This fish is drowning in air!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,1), "1. Can fly creatively while in water/rain");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,2), "2. Gain Night Vision II + Water Breathing II + Dolphin's Grace II while in water");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,3), "3. While in water/rain, regenerate 1 heart per second and gain +5 attack damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,4), "4. Continuously take damage while in desert/badlands/Nether biomes or dimensions");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,0), "\"Seeing this potato, you have felt a part of the power that governs the universe\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,1), "Permanently effective upon consumption:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,2), "1. Boundless power will %s your attack damage greatly increased");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,3), "2. Your standing is elevated; all creatures of lower standing than you will have their damage dealt to you %s");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,4), "3. You gain a trace of that sword's power; your melee damage will synchronize with the Apocrypha's %s");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,5), "4. Boundless vitality has completely %s your ascended body; your life force is greatly increased");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,6), "Your greed... is endless");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,7), "1. Attack damage increased to 6x base (+500%)");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,8), "2. All damage taken reduced by 50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,9), "3. Melee hits deal one extra hit of equal true damage (roughly double damage) and reset the target's invulnerability frames");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,10), "4. Max health increased to 6x base (+500%)");

        REGISTRATE.addRawLang("tooltip.spudiversity.entropy_increase","Entropy Increase");
        REGISTRATE.addRawLang("tooltip.spudiversity.suppress","Suppress");
        REGISTRATE.addRawLang("tooltip.spudiversity.endless_judgment","Endless Judgment");
        REGISTRATE.addRawLang("tooltip.spudiversity.dominate","Dominate");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,0), "\"Good morning! You're definitely sparkling today~\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,1), "1. Luck +10");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,2), "2. Max health +10");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,3), "3. When taking fatal damage, first consume saturation to negate the damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,4), "Obtained by right-clicking a bed while holding [Sakura Potato]");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,0), "\"So heavy...\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,1), "While worn:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,2), "1. Fortune and Looting level +5");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,3), "2. Attacks apply Slowness V and Weakness V");

        REGISTRATE.addRawLang( "effect.spudiversity.misty", "Mistify");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,0), "\"I am Alfarui Potato\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,1), "Upon consumption, gain \"Mistify\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,2), "During \"Mistify\", creatures other than bosses will not target you");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,3), "Also, hold Shift to phase through walls");

        REGISTRATE.addRawLang( "singularity.spudiversity.poisonous_potato", "Nascent Potato");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.AuspiciousPotato,0), "\"God and Demon, Spirit and Beast... The two blessed potatoes are now truly liberated\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.AuspiciousPotato,1), "While worn, gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.AuspiciousPotato,2), "1. Obtain all effects of the Light-chasing Potato \"Celestial Fox\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.AuspiciousPotato,3), "2. Attacks cause \"Bleeding\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.AuspiciousPotato,4), "3. Melee attacks deal an additional 20% fire damage and 10% magic damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.AuspiciousPotato,5), "4. On dealing damage, roll a die; based on the result, add extra special effects (luck value provides a 1:1 bonus)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,0), "\"She left you her gentleness, and kept the fire for herself. Now, the fire belongs to you too — along with all her pain and the unspoken love.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,1), "Occupies an independent perpetual slot; does not conflict with other perpetual potatoes:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,2), "1. Attack damage +8");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,3), "2. All attacks apply Fire Aspect III, and burning duration is extended to 15 seconds");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,4), "3. Damage dealt ignores target's armor");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,5), "When your permanent potato is \"Star Potato\":");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,6), "1. You can fly creatively");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,7), "2. The chain explosion chance of \"Bubbly Rabbit Tank Potato\" becomes 100%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,8), "When your death count > 100:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,9), "Wear \"Cake King Potato\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,10), "Obtained by consuming Sakura Potato while holding the_end_core in off-hand");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,0), "\"Thank you for your visit\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,1), "While worn, gain the following effects:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,2), "1. Damage taken reduced by 25%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,3), "2. For each positive effect you have:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,4), "   · Attack damage increased by 2%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,5), "   · Armor toughness increased by 1");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,6), "   · Max health increased by 10%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,7), "3. Convert 20% of damage dealt into self-healing");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,8), "I thought she left only two potatoes:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,9), "One gently lighting my path,");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,10), "The other burning alone where no one could see.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,11), "Now the rain has stopped, the sky is clear — that expanse of blue,");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,12), "Is the color of her finally being able to watch me in peace —");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,13), "That is her last wish.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,14), "\"Don't look for me, I'm always here, where the sky clears.\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,15), "After your playtime exceeds 99 hours");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,16), "Wear \"Cake King Potato\" and consume \"Tangxing Potato\" to obtain");

        REGISTRATE.addRawLang("item.spudiversity.jijin_potato.tooltip_alt", "Press '%s' to view story");
        REGISTRATE.addRawLang("item.spudiversity.jijin_potato.tooltip_shift", "Press '%s' to view acquisition");

        REGISTRATE.addRawLang("item.spudiversity.upgrade_smithing_template", "Edelweiss Template");
        REGISTRATE.addRawLang("item.spudiversity.upgrade_smithing_template_use", "Craft Edelweiss Potato");
        REGISTRATE.addRawLang("item.spudiversity.upgrade_smithing_template_need", "Paper Airplane");

        REGISTRATE.addRawLang("message.spudiversity.lose", "Critical failure! Your attack decreases and you suffer backlash");
        REGISTRATE.addRawLang("message.spudiversity.happy", "Success! Your damage increases by 20%");
        REGISTRATE.addRawLang("message.spudiversity.very_happy", "Great success! Your damage increases by 30%");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.the_end_core,0), "\"All her pain and fire are hidden within this core\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.the_end_core,1), "Obtained by consuming Star Potato in the Nether while holding a Nether Star in off-hand");
    }

    public static String FastMakeItemTooltip(RegistryEntry<? extends Item> item,int nummber){
        if (nummber ==0){
            return "item.spudiversity." + item.getId().getPath() + ".tooltip";
        }
        return "item.spudiversity." + item.getId().getPath() + ".tooltip_" + nummber;
    }
}