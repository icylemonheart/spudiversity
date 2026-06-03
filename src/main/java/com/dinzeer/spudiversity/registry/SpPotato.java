package com.dinzeer.spudiversity.registry;

// 新增四个土豆物品的导入
import com.dinzeer.legendreliclib.lib.tags.CuriosTag;
import com.dinzeer.spudiversity.item.cappotato.AlchemistPotato;
import com.dinzeer.spudiversity.item.common.*;
import com.dinzeer.spudiversity.item.curios.*;
import com.dinzeer.spudiversity.item.ex.*;
import com.dinzeer.spudiversity.item.speaical.NoNamePotato;
import com.dinzeer.spudiversity.item.speaical.NoNamePotatoDark;
import com.dinzeer.spudiversity.item.speaical.StarPotato;
import com.dinzeer.spudiversity.item.speaical.TangxingPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.List;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpPotato {
    // 注册铜土豆物品
    public static final RegistryEntry<CopperPotato> COPPER_POTATO = REGISTRATE.item(
                    "copper_potato", CopperPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<CarrotsPotato> CarrotsPotato = REGISTRATE.item(
                    "carrots_potato", CarrotsPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<CoalPotatoes> CoalPotatoes = REGISTRATE.item(
                    "coal_potatoes", CoalPotatoes::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<LapsPotato> LapsPotato = REGISTRATE.item(
                    "laps_potato", LapsPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<IronPotato> IRON_POTATO = REGISTRATE.item(
                    "iron_potato", IronPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<RedstonePotato> REDSTONE_POTATO = REGISTRATE.item(
                    "redstone_potato", RedstonePotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<GoldPotato> GOLD_POTATO = REGISTRATE.item(
                    "gold_potato", GoldPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<DiamondPotato> DIAMOND_POTATO = REGISTRATE.item(
                    "diamond_potato", DiamondPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<ObsidianPotato> ObsidianPotato = REGISTRATE.item(
                    "obsidian_potato", ObsidianPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<EmeraldPotato> EmeraldPotato = REGISTRATE.item(
                    "emerald_potato", EmeraldPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<EnchantedPotato> EnchantedPotato = REGISTRATE.item(
                    "enchanted_potato", EnchantedPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<NetheritePotato> NetheritePotato = REGISTRATE.item(
                    "netherite_potato", NetheritePotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<NetherStarPotato> NetherStarPotato = REGISTRATE.item(
                    "nether_star_potato", NetherStarPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<ApplePotato> ApplePotato = REGISTRATE.item(
                    "apple_potato", ApplePotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<EyesPotato> EyesPotato = REGISTRATE.item(
                    "eyes_potato", EyesPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<EnderPotato> EnderPotato = REGISTRATE.item(
                    "ender_potato", EnderPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<TirithippusPotato> TirithippusPotato = REGISTRATE.item(
                    "tirithippus_potato", TirithippusPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<FeibuPotato> FeibuPotato = REGISTRATE.item(
                    "feibu_potato", FeibuPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<SuperPotato> SuperPotato = REGISTRATE.item(
                    "super_potato", SuperPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();//核土豆
    public static final RegistryEntry<CakePotato> CakePotato = REGISTRATE.item(
                    "cake_potato", CakePotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<MadeInPotato> MadeInPotato = REGISTRATE.item(
                    "made_in_potato", MadeInPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<CakeKingPotato> CakeKingPotato = REGISTRATE.item(
                    "cake_king_potato", CakeKingPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<OceanPotato> OceanPotato = REGISTRATE.item(
                    "ocean_potato", OceanPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<GreenPotato> GreenPotato = REGISTRATE.item(
                    "green_potato", GreenPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<MysteriousPotato> MysteriousPotato = REGISTRATE.item(
                    "mysterious_potato", MysteriousPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<MadeInPotatoMadeInHaven> MadeInPotatoMadeInHaven = REGISTRATE.item(
                    "made_in_potato_made_in_haven", MadeInPotatoMadeInHaven::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<InfiniteFoodItem> InfinitePotato = REGISTRATE.item(
                    "infinite_potato", p-> new InfiniteFoodItem(p,1)
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<InfiniteFoodItem> InfinitePotatoCooked = REGISTRATE.item(
                    "infinite_potato_cooked", p-> new InfiniteFoodItem(p,4)
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<LootPotato> LootPotato = REGISTRATE.item(
                    "loot_potato", LootPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> PotatoSalaed = REGISTRATE.item(
                     "potato_salaed", Item::new
            ) .properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(8)
                                    .saturationMod(0.3f)
                                    .alwaysEat()
                                    .build())
                            .stacksTo(64).rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<UndyingPotato> UndyingPotato = REGISTRATE.item(
                    "undying_potato", com.dinzeer.spudiversity.item.curios.UndyingPotato::new
            ) .properties(p -> p.stacksTo(1))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO,CuriosTag.CHARM)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Item> HONEY_POTATO = REGISTRATE.item(
                    "honey_potato", Item::new
            ) .properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(6)
                                    .saturationMod(0.2f)
                                    .alwaysEat()
                                    .build())
                            .stacksTo(64).rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<SonicPotato> SonicPotato = REGISTRATE.item(
                    "sonic_potato", SonicPotato::new
            ) .properties(p -> p.stacksTo(1))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO,CuriosTag.CHARM)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<BambooPotato> BambooPotato = REGISTRATE.item(
                    "bamboo_potato", BambooPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<CookCookedPotato> CookCookedPotato = REGISTRATE.item(
                    "cook_cooked_potato", CookCookedPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<NoNamePotato> NONAME_POTATO = REGISTRATE.item(
                    "noname_potato", NoNamePotato::new
            ) .properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(20)
                                    .saturationMod(2f)
                                    .alwaysEat()
                                    .build())
                            .stacksTo(64).rarity(Rarity.COMMON)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<IcePotato> IcePotato = REGISTRATE.item(
                    "ice_potato", IcePotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<BlazePotato> BlazePotato = REGISTRATE.item(
                    "blaze_potato", BlazePotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<SakuraPotato> SakuraPotato = REGISTRATE.item(
                    "sakura_potato", SakuraPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<EveryThingPotato> EveryThingPotato = REGISTRATE.item(
                    "every_thing_potato", EveryThingPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<TheEndPotato> TheEndPotato = REGISTRATE.item(
                    "the_end_potato", TheEndPotato::new
            ) .properties(p -> p.stacksTo( 64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<NoNamePotatoDark> NoNamePotatoDark = REGISTRATE.item(
                    "noname_potato_dark", NoNamePotatoDark::new
            ) .properties(p ->
                    p.food((new FoodProperties.Builder())
                                    .nutrition(20)
                                    .saturationMod(6f)
                                    .alwaysEat()
                                    .build())
                            .stacksTo(64).rarity(Rarity.EPIC)
            )
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<StarPotato> StarPotato = REGISTRATE.item(
                    "star_potato", StarPotato::new
            ) .properties(p -> p.stacksTo(64)
                    .food(new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationMod(0)
                    .alwaysEat().build()).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<AnnoPotato> AnnoPotato = REGISTRATE.item(
                    "anno_potato", AnnoPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<EnderDragonPotato> EnderDragonPotato = REGISTRATE.item(
                    "ender_dragon_potato", EnderDragonPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<BuildPotato> BuildPotato = REGISTRATE.item(
                    "build_potato", BuildPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<ZzzPotato> ZzzPotato = REGISTRATE.item(
                    "zzz_potato", ZzzPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<GeedPotato> GeedPotato = REGISTRATE.item(
                    "geed_potato", com.dinzeer.spudiversity.item.curios.GeedPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<LootKingPotato> LootKingPotato = REGISTRATE.item(
                    "loot_king_potato", LootKingPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();


    public static final RegistryEntry<GlowStonePotato> GlowStonePotato = REGISTRATE.item(
                    "glow_stone_potato", GlowStonePotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.RARE))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<GlassPotato> GlassPotato = REGISTRATE.item(
                    "glass_potato", GlassPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.RARE))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<TarianPotato> TarianPotato = REGISTRATE.item(
                    "tarian_potato", TarianPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<Potato36> Potato36 = REGISTRATE.item(
                    "potato_36", Potato36::new
            ) .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.EPIC))
            .tag(CuriosTag.POTATO)
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<FireworkPotato> FireworkPotato = REGISTRATE.item(
                    "firework_potato", FireworkPotato::new
            ) .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.EPIC))
            .tag(CuriosTag.POTATO)
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<AlchemistPotato> AlchemistPotato = REGISTRATE.item(
                    "alchemist_potato", AlchemistPotato::new
            ) .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.EPIC))
            .tag(CuriosTag.POTATO)
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<DiamondCrystalPotato> DiamondCrystalPotato = REGISTRATE.item(
                    "diamond_crystal_potato", DiamondCrystalPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<EdelweissPotato> EdelweissPotato = REGISTRATE.item(
                    "edelweiss_potato", EdelweissPotato::new
            ) .properties(p ->
                    p.stacksTo(1)
                    .rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();

    public static final RegistryEntry<EmberPotato> EmberPotato = REGISTRATE.item(
                    "ember_potato", EmberPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<TinyWaterPotato> TinyWaterPotato = REGISTRATE.item(
                    "tiny_water_potato", TinyWaterPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<CrystalPotato> CrystalPotato = REGISTRATE.item(
                    "crystal_potato", CrystalPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<MistyPotato> MistyPotato = REGISTRATE.item(
                "misty_potato", MistyPotato::new
            ) .properties(p -> p.stacksTo(64).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<TangxingPotato> TangxingPotato = REGISTRATE.item(
                    "tangxing_potato", TangxingPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .defaultModel().defaultLang().register();
    public static final RegistryEntry<JijinPotato> JijinPotato = REGISTRATE.item(
                    "jijin_potato", JijinPotato::new
            ) .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .tab(SpItemTabs.spudiversity.getKey())
            .tag(CuriosTag.POTATO)
            .defaultModel().defaultLang().register();
    public static void register() {
        ToolTipBlackList.add(MadeInPotatoMadeInHaven);
        ToolTipBlackList.add(SonicPotato);
        ToolTipBlackList.add(NONAME_POTATO);
        ToolTipBlackList.add(TirithippusPotato);
        ToolTipBlackList.add(SuperPotato);
        ToolTipBlackList.add(MadeInPotato);
        ToolTipBlackList.add(EveryThingPotato);
        ToolTipBlackList.add(TheEndPotato);
        ToolTipBlackList.add(NoNamePotatoDark);
        ToolTipBlackList.add(StarPotato);
        ToolTipBlackList.add(AnnoPotato);
        ToolTipBlackList.add(EnderDragonPotato);
        ToolTipBlackList.add(TarianPotato);
        ToolTipBlackList.add(EdelweissPotato);
        ToolTipBlackList.add(JijinPotato);
        ToolTipBlackList.add(TangxingPotato);
    }
















    public static List<RegistryEntry<? extends Item>> ToolTipBlackList=new ArrayList<>();






}