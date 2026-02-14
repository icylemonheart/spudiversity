package com.dinzeer.spudiversity.util.lang;

import com.dinzeer.spudiversity.regsitry.*;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class LangUtil {
    public static void init(){
        REGISTRATE.addRawLang("item_group.spudiversity.spudiversity", "Potato Feast");

        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip", "\"Seems to be the staple food of Wither Skeletons\"");
        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip_1", "Grants 'Furnace Blessing' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip_2", "Automatically smelts blocks mined during the effect");

        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip", "\"Taste... acceptable?\"");
        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip_1", "Grants 'Living Lightning Rod' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip_2", "Attracts lightning but grants 80% lightning damage reduction during the effect");

        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip", "\"Tastes like potato stew with carrots, but isn't this baked?\"");
        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip_1", "Grants 'Night Vision' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip_2", "Grants 'Jump Boost' effect when eaten");

        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip", "\"99% pure, a rare treasure!\"");
        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip_1", "Grants 'Water Breathing' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip_2", "Cannot be used as an enchanting medium");

        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip", "\"Let the storm rage even fiercer!\"");
        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip_1", "Grants 'Resistance II' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip_2", "Lasts 20s");

        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip", "\"Rumor says the first one was created by a witch tired of dropping Redstone\"");
        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip_1", "Grants 'Speed II' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip_2", "Lasts 1 minute");

        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip", "\"Hmm, 99% pure, a rare treasure indeed!\"");
        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip_1", "Grants 'Regeneration' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip_2", "Grants 'Resistance' effect when eaten");

        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip", "\"They say even more precious than a Desert Star is a potato made from one\"");
        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip_1", "Grants 'Resistance III' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip_2", "Lasts 30s");

        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip", "\"C-Can this even be eaten?\"");
        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip_1", "Grants 'Resistance II' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip_2", "Grants 'Fire Resistance' effect when eaten");

        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip", "\"Embrace the new era, O Invisible Hand!\"");
        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip_1", "Grants 'Village Sun' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip_2", "Villager trade prices are 80% lower during the effect");

        REGISTRATE.addRawLang("item.spudiversity.enchanted_potato.tooltip", "\"This is expensive\"");
        REGISTRATE.addRawLang("item.spudiversity.enchanted_potato.tooltip_1", "Grants the same effects as an Enchanted Golden Apple when eaten");

        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip", "\"A luxurious indulgence\"");
        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip_1", "Grants 'Resistance IV' effect when eaten");
        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip_2", "Lasts 1 minute");

        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip", "\"This is the pinnacle of potatoes!\"");
        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip_1", "Grants all effects of a Level 2 Beacon when eaten");
        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip_2", "Lasts 3 minutes");

        REGISTRATE.addRawLang("item.spudiversity.apple_potato.tooltip", "\"Potato or Apple? Doesn't matter\"");
        REGISTRATE.addRawLang("item.spudiversity.apple_potato.tooltip_1", "Dropped occasionally when harvesting Potato plants");

        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip", "\"You better be a potato\"");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_1", "Clears all potion effects when eaten");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_2", "Then grants long-duration 'Night Vision'");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_3", "Dropped occasionally when a Villager/Player dies");

        REGISTRATE.addRawLang("item.spudiversity.ender_potato.tooltip", "\"An edible Ender Eye\"");
        REGISTRATE.addRawLang("item.spudiversity.ender_potato.tooltip_1", "Same teleportation effect as Chorus Fruit when eaten");


        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip", "\"See you tomorrow!\"");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_0", "Shift+Right-click on a block to record the coordinates above it");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_1", "Teleports you above the recorded block when eaten");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_2", "Recorded Coordinates: X-%s Y-%s Z-%s");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_3", "No coordinates recorded");

        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip", "\"Phoebe? Phoebe Chubi\"");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_1", "「Time-Lag Barrier」");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_2", "Grants Resistance II (5s) + Regeneration II (2s) when eaten");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_3", "And instantly applies 'Time Stasis' to all entities (except players) in a 5-block radius:");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_4", "Grants Slowness V + Weakness V");


        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip", "Warning! Warning! Fission index of this item exceeds safe limits");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_1", "Using this legendary potato grants you a Transcendent Body:");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_2", "Grants Absorption V");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_3", "Repairs equipped armor durability every second");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_4", "Automatically counterattacks nearby enemies");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_5", "Come and enjoy it!");

        REGISTRATE.addRawLang("item.spudiversity.cake_potato.tooltip", "\"Happy Birthday!\"");
        REGISTRATE.addRawLang("item.spudiversity.cake_potato.tooltip_1", "Just a very tasty potato");

        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip", "\"Accelerate, until all potatoes bloom and bear fruit!\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_1", "「Potato」「Potato Flower」「Potato」「Sprouting」「Potato」「Blood Dance」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_2", "「Potato」「Ubisoft Gate」「Potato」「Server」「Potato」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_3", "「Fried」「Stew」「Potato」「Roasted」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_4", "The 14 Riddles Have Arrived");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_5", "「Made In Potato!」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_6", "When worn, rapidly repairs durability of held items");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_7", "Simultaneously, you will be Hasted");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_8", "Furthermore, when you carry the Cake King and Dual Flavors");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_9", "And place the Ocean Potato in your off-hand");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_10", "Eating it will grant you the true 'Great Power'");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_alt", "Press %s to show item description");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,0), "\" I am Dessert! Mortals, offer your praise to the Potato!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,1), "- 「Potato Sovereignty!」: Permanently gain all Beacon effects (max level)");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,2), "- 「Fullness is Bliss」: Automatically refills hunger when depleted");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,3), "-  「Happiness Takes Flight」: Grants creative flight");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,0), "\"Uh, sea salt flavor, but inedible\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,1), "When held, your mining speed is doubled");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,2), "Right-click while holding (main or off-hand) to release a spherical sonic wave");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,3), "The sonic wave deals no damage, but applies Slowness III (3s) + Glowing (10s) to mobs");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,4), "If a pickaxe is in the off-hand and you are in water, the sonic wave can break blocks");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,0), "\"Steve? No, please call me Sakyamuni Spud\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,1), "Enter a meditative state after consumption");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,2), "Slowly restores nearby teammates' hunger while meditating");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,0), "W B X!");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,1), "Grants the following effects when worn:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,2), "「Strength II」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,3), "「Haste II」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,4), "「Speed I」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,5), "「Regeneration I」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,6), "Attacks release a 25% damage air slash");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,1), "Now, Haste accelerates even faster with its birth");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,2), "Now, even time itself can be accelerated");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,3), "Press %s to begin time acceleration");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.InfinitePotato,0), "\"Infinite eating, but raw\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.InfinitePotatoCooked,0), "\"Infinite eating, but cooked\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,0), "\"The Spud is Treasure Itself!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,1), "Wearing this potato grants 'Super Strength'");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.PotatoSalaed,0), "\"Very healthy\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,0), "\"Rumor says this potato was grown by an Evoker voluntarily working for a Wandering Trader for free\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,1), "Same effects as a Totem of Undying");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,2), "Obtained from Wandering Trader trades");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.HONEY_POTATO,0), "\"Sweet\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SonicPotato,0), "\"Why does this potato feel... alive?\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SonicPotato,1), "Press %s while worn to fire a sonic attack");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,0), "\"Legend tells this was the creation of a hermit in the bamboo forest (or perhaps a particularly clever panda?), slow-roasting potatoes in fresh bamboo tubes, allowing them to fully absorb the bamboo's fresh scent and resilience.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,1), "After consumption, gain the 'Bamboo Cutter' status effect");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CookCookedPotato,0), "\"This definitely can't be eaten, so... please, you have it~\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CookCookedPotato,1), "Can be fed to entities to deal magic damage (ignores invincibility frames)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,0), "「A potato of strange coloration. Its skin is a silent crimson-purple, yet its eyes shimmer with a warm, bright yellow.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,1), "「It has no taste, no scent, yet holding it brings a peculiar calm, as if clutching a heart that has ceased to beat.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,2), "「You are certain it came from the hands of someone immensely important. Their figure is blurred in your memory, as if seen through an endless snowfall. The only thing clear is the words you never spoke when they left.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,3), " 「...Consume it. Perhaps you will find answers within, or perhaps oblivion.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,4), "‘Eat it. It might not do much, but... it'll fill you up.’");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,5), "A voice sounds in your mind, its gender blurred, carrying laughter and a trace of... undetectable weariness.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,6), "The voice sends a sharp pain through you, like an ice pick to the temple, followed by an immense, suffocating emptiness.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,7), "Who are you? Who was I?");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,8), "What exactly happened between us");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,9), "That requires such a silent potato to serve as the final anchor, against the inevitable, complete oblivion?");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,0), "\"It really looks just like a piece of ice. Uh, and it tastes like ice too.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,1), "Eating this potato grants the 'Power of Extreme Cold' effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,2), "Obtained by using a Baked Potato on a Snow Golem");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,0), "「A potato no longer content to be buried in soil. It craved heat, craved transformation, and ultimately threw itself into the most turbulent lava flows of the earth's veins.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,1), "「Fierce flames remolded its form, boiling energy filled its core.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,2), "「It was reborn from the fire.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,3), "After consumption, gain 'Burnout'");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,0), "\"A potato born from pollination by strange pollen in a lush flower forest. Its body exhibits an unusual tender pink-white hue, even sporting a few eternal, un-wilting cherry blossom petals on its surface.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,1), "\"It emits a sweet floral aroma, as if condensing the entire breath of spring within this small tuber.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,2), "After consumption, gain 'Hanami Rhapsody'");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,0), "「\"A tuber not grown, but condensed from the 'possibility' at the world's dawn. Its skin is a flowing starscape, its sprouts shimmering laws of nature.\"」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,1), "After consumption, gain 'Transcendent Narrative'");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,2), "「Addendum: This item cannot be obtained normally.」");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,0), "「A potato with a dull color and cold touch, its body covered with blood-vessel-like tentacles.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,1), "「It exudes an ominous aura; when held, one seems to hear countless whispers.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,2), "After consumption, gain 'Emptiness of the End'");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,3), "「...Come, listen to me...」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,4), "「...You're lonely too, aren't you?」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,5), "「...You also have someone you can't forgive, right?」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,6), "「...You also desire... to make this world feel your pain, don't you?」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,7), "「...Eat me. We... shall become one...」");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Cyclone,0), "「Cyclone!」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Cyclone,1), "30% chance to drop when a Wither kills any entity at 200 meters height");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Joker,0), "「Joker!」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Joker,1), "Chance to drop from killing an entity with over 100 health while falling");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.WaveCore,0), "Dropped by Elder Guardians");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.WaveCrystal,0), "");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,0), "「A Dragon Blood Potato forged from the blood of a Fire Dragon.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,1), "After consumption, become a Potato-Scion of the 'Fire Dragon'");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,2), "You will gain:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,3), "Magma Block Walker");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,4), "Contact with lava (not magma blocks) grants Haste I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,5), "Permanent Fire Resistance");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,6), "Can automatically smelt items");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,7), "Max health increased by 50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,8), "Water within a 5-block radius of you evaporates");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,0), "「A Dragon Blood Potato forged from the blood of an Ice Dragon.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,1), "After consumption, become a Potato-Scion of the 'Ice Dragon'");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,2), "You will gain:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,3), "Frost Walker");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,4), "Immune to Slowness effects");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,5), "Attacking a mob freezes it in place for 3 seconds (ineffective on BOSSes) and extinguishes it");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,6), "Final damage increased by 15% while in water");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,7), "Max health increased by 50%");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,0), "「A Dragon Blood Potato forged from the blood of a Lightning Dragon.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,1), "After consumption, become a Potato-Scion of the 'Lightning Dragon'");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,2), "You will gain:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,3), "Chance to summon lightning above hit mobs");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,4), "Lightning chance greatly increased during thunderstorms or near lightning rods");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,5), "Moving builds static electricity; at full charge, your next attack knocks back and paralyzes (Blindness + Slowness) enemies in a small area");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,6), "Gain Speed II when moving during non-clear weather");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,7), "Max health increased by 50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,8), "Lower health increases your attack and movement speed (up to 25%)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,0), "「A blessed potato received from the Divine Fox. It tastes sweet and is also quite nice to carry.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,1), "When worn:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,2), "Provides 18% healing boost when saturation is full");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,3), "Provides damage reduction equal to 18% of your Luck");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,4), "Provides damage boost equal to 18% of your Luck");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,5), "Continuously gain the 'Village Sun' effect while also having the Sacred Sword 'Divine Fox' in inventory");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,6), "Obtained by striking the Sacred Sword 'Divine Fox' with an Emerald Potato");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,0), "「A blessed potato received from the Blood Fox. It tastes spicy and feels unpleasant to carry.」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,1), "When worn:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,2), "Your melee damage becomes Fire damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,3), "On hit, target suffers 'Bleeding' for 3 seconds, dealing 2 damage per second. Effect doesn't stack but refreshes duration");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,4), "You deal 40% increased damage to 'Bleeding' targets");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,5), "After killing 10 or more enemies consecutively, enter a 'Khorne' state for 5 seconds");

        REGISTRATE.addRawLang( "effect.spudiversity.copper_potato", "Lightning Rod");
        REGISTRATE.addRawLang( "effect.spudiversity.furnace_blessing", "Furnace Blessing");
        REGISTRATE.addRawLang( "effect.spudiversity.village_sun", "Village Sun");
        REGISTRATE.addRawLang( "effect.spudiversity.no_mobs_booby", "Invincible Body");
        REGISTRATE.addRawLang( "effect.spudiversity.for_haven", "Made In Haven · Heavenly Progression");
        REGISTRATE.addRawLang( "effect.spudiversity.rain_bow", "Zen Mind");
        REGISTRATE.addRawLang( "effect.spudiversity.bamboo_give", "Bamboo Cutter");
        REGISTRATE.addRawLang( "effect.spudiversity.ice_potato", "Power of Extreme Cold");
        REGISTRATE.addRawLang( "effect.spudiversity.blaze_end", "Burnout");
        REGISTRATE.addRawLang( "effect.spudiversity.sakura_fantasy", "Hanami Rhapsody");
        REGISTRATE.addRawLang( "effect.spudiversity.every_thing", "Transcendent Narrative");
        REGISTRATE.addRawLang( "effect.spudiversity.we_are_null", "Emptiness of the End");
        REGISTRATE.addRawLang( "effect.spudiversity.wave_blood", "Bleeding");
        REGISTRATE.addRawLang( "effect.spudiversity.blood_king", "Khorne");

        REGISTRATE.addRawLang( "stack.spudiversity.blood_king_cd", "「Khorne's Battle Will」");

        REGISTRATE.addRawLang( "curios.identifier.potato", "Potato Charm");
        REGISTRATE.addRawLang( "curios.modifiers.potato", "When worn as a Potato Charm");


        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,2), "Simultaneously, creatures you kill will drop loot twice");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,0), "The moment the Wither was defeated, the Nameless Potato in your off-hand lost all its luster.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,1), "It turned dull, cold, like an ordinary stone.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,2), "It exhausted its power, granting you scattered memory fragments—a figure leaving in the snow, words left unspoken.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,3), "The thought of the End coordinates 0-0-0 emerges in your mind.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,0), "You journeyed to the world's end, at the center of the coordinates.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,1), "You consumed the dimmed potato, unraveling the final mystery of potatoes and memories.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,2), "Upon comprehending all truths, tears streamed down your face involuntarily.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,3), "The potato fragment, soaked in tears, transformed into a miniature star emitting eternal light.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,4), "This star is named \"Tamina\".");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,5), "Carry it, and you shall never be lost to oblivion.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,6), "She is the companion who sacrificed everything for you—proof she existed.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,7), "Consume it, %s. You shall receive her blessing: No longer hungry, and stronger.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,0), "\"Hehehehe, yahahaha\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,1), "Seems to be a potato exported from its origin and re-imported.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,2), "Quite high in sugar content, consume less during dieting.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,3), "Gain \"Regeneration III\" upon use.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,0), "Right-click while holding to consume hunger and fire a Dragon's Breath projectile.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,1), "[Ingredients]");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,2), "· 59% End Stone dust");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,3), "· 40% Chorus Fruit chewed by the Ender Dragon");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,4), "· 1% Ender Dragon saliva (because it's too delicious)");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,5), "[Friendly Reminder]");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,6), "■ If ordinary potatoes cast envious glances, it's normal.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,7), "■ Final interpretation rights belong to the Ender Dragon (it says next time it'll add more Dragon's Breath).");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,8), "Dragon-slaying commemorative item.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,9), "Obtained after achieving the advancement \"Liberate the End\".");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.FortificationWandPotato,0), "\"This is a potato graduated from the Shield Magic department of the Arcane Academy!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.FortificationWandPotato,1), "A potato infused with the Lich's shield magic.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.FortificationWandPotato,2), "Consuming grants 5 Lich Shields, blocking five instances of damage.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.DuskPotato,0), "\"This spud is also the Potato King!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.DuskPotato,1), "A potato fused with the Lich's dusk magic.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.DuskPotato,2), "Attacks have a chance to launch Dusk Pearls, and a small chance to launch Fire Charges.");

        REGISTRATE.addRawLang( "effect.spudiversity.undead_transformation_effect", "Pariah");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.ZombiePotato,0), "\"Brains! Brains!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.ZombiePotato,1), "Gain \"Pariah\" upon consumption.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.ZombiePotato,2), "Undead creatures no longer target you, but neutral creatures will lock onto you.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,0), "\"Potato with a rusty taste.\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,1), "A potato fused with the Lich's blood magic.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,2), "Each attack causes life steal.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,3), "Grants beneficial effects when equipped.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpTwPotato.BloodPotato,4), "Upon death, prevents death once, then enters cooldown.");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,2), "Right-click a panda eating bamboo with an Apple Potato to obtain.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,6), "Strike the \"Blood Fox\" with a Diamond Potato.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,3), "In the Flower Forest biome, use Bone Meal on a potato sprout for a very low chance to obtain 1-2.");


        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.rabbit,0), "\"rabbit\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.tank,0), "\"tank\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.rabbit,1), "Obtained when a rabbit is killed by explosion damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.tank,1), "Obtained when a turtle is killed by explosion damage");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.bear_rabbit_tank,0), "\"Bubbly Fullness\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.bear_rabbit_tank,1), "You can feel this thing is carbonated");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,0), "\"A hero can't retreat from battle!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,1), "Effects provided:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,2), "1. Super Speed I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,3), "2. Super Jump I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,4), "3. Regeneration I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,5), "4. Resistance II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,6), "5. Attacks have a 30% chance to deal additional explosion damage to nearby enemies (explosion damage cannot trigger)");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,0), "\"Good morning! Potato!\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,1), "Reportedly very powerful");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,2), "When worn, grants Resistance I, Night Vision, and Strength I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,3), "After killing the Wither, additionally gains Regeneration I and Health Boost I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,4), "After killing an Elder Guardian, additionally gains Water Breathing and Jump Boost");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,5), "After killing the Ender Dragon, additionally gains Speed, Absorption, and Slow Falling");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,6), "After killing the Warden, the levels of Resistance and Strength gained from Zzz Potato increase by 1");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,7), "Additionally, after killing the Warden, attacks inflict Blindness and Slowness.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,8), "Right-click a bed while holding a Nether Star Potato to obtain");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,0), "\"Here we go！！！！\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,1), "Effects provided:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,2), "1. Resistance II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,3), "2. Absorption I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,4), "3. Saturation I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,5), "4. Strength I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,6), "5. Speed I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,7), "6. Night Vision");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,8), "7. When equipped, dogs around gain the Strength effect");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,9), "8. Immune to Darkness");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,10), "Temporarily unavailable");
    }



    public static String FastMakeItemTooltip(RegistryEntry<? extends Item> item,int num){
        if (num ==0){
        return "item.spudiversity." + item.getId().getPath() + ".tooltip";
        }
    return "item.spudiversity." + item.getId().getPath() + ".tooltip_" + num;
    }
}
