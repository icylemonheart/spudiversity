package com.dinzeer.spudiversity.registry;

import com.dinzeer.legendreliclib.lib.tags.CuriosTag;
import com.dinzeer.spudiversity.item.compat.godfox.AuspiciousPotato;
import com.dinzeer.spudiversity.item.compat.godfox.BloodFoxPotato;
import com.dinzeer.spudiversity.item.compat.godfox.SkyFoxPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Rarity;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpGodFoxPotato {

    public static final RegistryEntry<SkyFoxPotato> SkyFoxPotato = REGISTRATE.item(
                    "sky_fox_potato", SkyFoxPotato::new
            ) .properties(p -> p.stacksTo(64).food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(1)
                    .alwaysEat().build()) .rarity(Rarity.EPIC))
            .tag(CuriosTag.POTATO)

            .defaultModel().defaultLang().register();
    public static final RegistryEntry<BloodFoxPotato> BloodFoxPotato = REGISTRATE.item(
                    "blood_fox_potato", BloodFoxPotato::new
            ) .properties(p -> p.stacksTo(1).food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(1)
                    .alwaysEat().build()) .rarity(Rarity.EPIC))
            .tag(CuriosTag.POTATO)

            .defaultModel().defaultLang().register();

    public static final RegistryEntry<AuspiciousPotato> AuspiciousPotato = REGISTRATE.item(
                    "auspicious_potato", AuspiciousPotato::new
            )
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static void register() {
        SpPotato.ToolTipBlackList.add(SkyFoxPotato);
        SpPotato.ToolTipBlackList.add(BloodFoxPotato);
    }
}
