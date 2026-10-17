package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.item.compat.iceandfire.FireDragonPotato;
import com.dinzeer.spudiversity.item.compat.iceandfire.IceDragonPotato;
import com.dinzeer.spudiversity.item.compat.iceandfire.LightningDragonPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpICFPotato {

    public static final RegistryEntry<Item, IceDragonPotato> IceDragonPotato = REGISTRATE.item(
                    "ice_dragon_potato", IceDragonPotato::new
            ).properties(p -> p.stacksTo(64).food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationModifier(0)
                    .alwaysEdible().build()).rarity(Rarity.EPIC))
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<Item, FireDragonPotato> FireDragonPotato = REGISTRATE.item(
                    "fire_dragon_potato", FireDragonPotato::new
            ).properties(p -> p.stacksTo(64).food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationModifier(0)
                    .alwaysEdible().build()).rarity(Rarity.EPIC))
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<Item, LightningDragonPotato> LightningDragonPotato = REGISTRATE.item(
                    "lightning_dragon_potato", LightningDragonPotato::new
            ).properties(p -> p.stacksTo(64).food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationModifier(0)
                    .alwaysEdible().build()).rarity(Rarity.EPIC))
            .defaultModel().defaultLang().register();

    public static void regsitry() {
        SpPotato.ToolTipBlackList.add(IceDragonPotato);
        SpPotato.ToolTipBlackList.add(FireDragonPotato);
        SpPotato.ToolTipBlackList.add(LightningDragonPotato);
    }
}
