package com.dinzeer.spudiversity.registry;

import com.dinzeer.legendreliclib.lib.tags.CuriosTag;
import com.dinzeer.spudiversity.item.compat.tw.BloodPotato;
import com.dinzeer.spudiversity.item.compat.tw.DuskPotato;
import com.dinzeer.spudiversity.item.compat.tw.FortificationWandPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpTwPotato {

    public static final RegistryEntry<Item, FortificationWandPotato> FortificationWandPotato = REGISTRATE.item(
                    "fortification_wand_potato", FortificationWandPotato::new
            ).properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(8)
                                    .saturationModifier(0.3f)
                                    .alwaysEdible()
                                    .build())
                            .stacksTo(64).rarity(Rarity.EPIC)
            )
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<Item, DuskPotato> DuskPotato = REGISTRATE.item(
                    "dusk_potato", DuskPotato::new
            ).properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(8)
                                    .saturationModifier(0.3f)
                                    .alwaysEdible()
                                    .build())
                            .stacksTo(1).rarity(Rarity.EPIC)
            )
            .tag(CuriosTag.POTATO, CuriosTag.EX_CURIO)
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<Item, Item> ZombiePotato = REGISTRATE.item(
                    "zombie_potato", Item::new
            ).properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(4)
                                    .saturationModifier(0.1f)
                                    .effect(new MobEffectInstance(SpEffect.UndeadTransformationEffect, 60 * 20), 1f)
                                    .alwaysEdible()
                                    .build())
                            .stacksTo(1).rarity(Rarity.EPIC)
            )
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<Item, BloodPotato> BloodPotato = REGISTRATE.item(
                    "blood_potato", BloodPotato::new
            ).properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(8)
                                    .saturationModifier(0.3f)
                                    .alwaysEdible()
                                    .build())
                            .stacksTo(1).rarity(Rarity.EPIC)
            )
            .tag(CuriosTag.POTATO, CuriosTag.EX_CURIO)
            .defaultModel().defaultLang().register();

    public static void register() {
    }
}
