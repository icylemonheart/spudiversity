package com.dinzeer.spudiversity.regsitry;

import com.dinzeer.spudiversity.Item.compat.iceandfire.FireDragonPotato;
import com.dinzeer.spudiversity.Item.compat.iceandfire.IceDragonPotato;
import com.dinzeer.spudiversity.Item.compat.iceandfire.LightningDragonPotato;
import com.dinzeer.spudiversity.Item.ex.TheEndPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Rarity;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpICFPotato {
    public static final RegistryEntry<IceDragonPotato> IceDragonPotato = REGISTRATE.item(
                    "ice_dragon_potato", IceDragonPotato::new
            ) .properties(p -> p.stacksTo(64).food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationMod(0)
                    .alwaysEat().build()).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<FireDragonPotato> FireDragonPotato = REGISTRATE.item(
                    "fire_dragon_potato", FireDragonPotato::new
            ) .properties(p -> p.stacksTo(64).food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationMod(0)
                    .alwaysEat().build()).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<LightningDragonPotato> LightningDragonPotato = REGISTRATE.item(
                    "lightning_dragon_potato", LightningDragonPotato::new
            ) .properties(p -> p.stacksTo(64).food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationMod(0)
                    .alwaysEat().build()).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static void regsitry(){
        SpPotato.ToolTipBlackList.add(IceDragonPotato);
        SpPotato.ToolTipBlackList.add(FireDragonPotato);
        SpPotato.ToolTipBlackList.add(LightningDragonPotato);
    }
}
