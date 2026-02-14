package com.dinzeer.spudiversity.data;

import com.dinzeer.spudiversity.regsitry.SpICFPotato;
import com.dinzeer.spudiversity.regsitry.SpItem;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.dinzeer.spudiversity.regsitry.SpTwPotato;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import committee.nova.mods.avaritia.init.data.provider.recipe.ModShapedRecipeBuilder;
import committee.nova.mods.avaritia.init.registry.ModBlocks;
import committee.nova.mods.avaritia.init.registry.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import twilightforest.init.TFItems;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import static com.dinzeer.spudiversity.Spudiversity.MODID;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

public class SpRecipeGen  {

    public static void buildRecipes(Consumer<FinishedRecipe> consumer){
        // 土豆盛宴系列合成配方
        addPotatoFeastRecipes(consumer);

        // 其他特殊土豆合成配方
        addSpecialPotatoRecipes(consumer);





        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("iceandfire"))
                .addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpICFPotato.FireDragonPotato.get(), 1)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', IafItemRegistry.FIRE_DRAGON_BLOOD.get())
                        .define('P', SpPotato.ObsidianPotato.get())
                        .unlockedBy("has_fire_dragon_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                        ::save)
                .build(consumer, new ResourceLocation(MODID, "fire_dragon_potato"));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("iceandfire"))
                .addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpICFPotato.IceDragonPotato.get(), 1)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', IafItemRegistry.ICE_DRAGON_BLOOD.get())
                        .define('P', SpPotato.ObsidianPotato.get())
                        .unlockedBy("has_ice_dragon_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                        ::save)
                .build(consumer, new ResourceLocation(MODID, "ice_dragon_potato"));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("iceandfire"))
                .addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpICFPotato.LightningDragonPotato.get(), 1)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', IafItemRegistry.LIGHTNING_DRAGON_BLOOD.get())
                        .define('P', SpPotato.ObsidianPotato.get())
                        .unlockedBy("lightning_dragon_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                        ::save)
                .build(consumer, new ResourceLocation(MODID, "lightning_dragon_potato"));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("twilightforest"))
                .addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpTwPotato.FortificationWandPotato.get(), 1)
                        .pattern("ABC")
                        .pattern("DEF")
                        .pattern("GHI")
                        .define('A', Items.EMERALD)
                        .define('B', TFItems.FORTIFICATION_SCEPTER.get())
                        .define('C', Items.DIAMOND)
                        .define('D', Items.GOLD_BLOCK)
                        .define('E', Items.POTATO)
                        .define('F', Items.REDSTONE)
                        .define('G', Items.BONE)
                        .define('H', TFItems.LICH_TROPHY.get())
                        .define('I', Items.APPLE)
                        .unlockedBy("fortification_wand_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                        ::save)
                .build(consumer, new ResourceLocation(MODID, "fortification_wand_potato"));
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("twilightforest"))
                .addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpTwPotato.ZombiePotato.get(), 1)
                        .pattern("ABC")
                        .pattern("DEF")
                        .pattern(" G ")
                        .define('A', Items.ROTTEN_FLESH)
                        .define('B', Items.WITHER_SKELETON_SKULL)
                        .define('C', Items.BONE)
                        .define('D', Items.GUNPOWDER)
                        .define('E', Items.POTATO)
                        .define('F', Items.SPIDER_EYE)
                        .define('G',TFItems.ZOMBIE_SCEPTER.get())
                        .unlockedBy("zombie_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                        ::save)
                .build(consumer, new ResourceLocation(MODID, "zombie_potato"));
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("twilightforest"))
                .addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpTwPotato.DuskPotato.get(), 1)
                        .pattern(" A ")
                        .pattern("BCD")
                        .pattern(" E ")
                        .define('A', Items.ENDER_EYE)
                        .define('B', Items.GUNPOWDER)
                        .define('C', SpPotato.DIAMOND_POTATO.get())
                        .define('D', Items.BLAZE_POWDER)
                        .define('E', TFItems.TWILIGHT_SCEPTER.get())

                        .unlockedBy("dusk_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                        ::save)
                .build(consumer, new ResourceLocation(MODID, "dusk_potato"));
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("twilightforest"))
                .addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpTwPotato.BloodPotato.get(), 1)
                        .pattern("ABC")
                        .pattern("DEF")
                        .pattern("GHI")
                        .define('A', TFItems.FIERY_BLOOD.get())
                        .define('B', Items.BEACON)
                        .define('C', TFItems.GLASS_SWORD.get())
                        .define('D', Items.GHAST_TEAR)
                        .define('E', SpPotato.NetheritePotato.get())
                        .define('F', TFItems.KNIGHTMETAL_CHESTPLATE.get())
                        .define('G', Items.TOTEM_OF_UNDYING)
                        .define('H', TFItems.LIFEDRAIN_SCEPTER.get())
                        .define('I', Items.GOLDEN_APPLE)
                        .unlockedBy("blood_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                        ::save)
                .build(consumer, new ResourceLocation(MODID, "blood_potato"));
    }

    private static void addPotatoFeastRecipes(Consumer<FinishedRecipe> consumer) {
        // 胡萝卜土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.CarrotsPotato.get(), 1)
                .pattern("CCC")
                .pattern("CPC")
                .pattern("CCC")
                .define('C', Items.CARROT)
                .define('P', Items.BAKED_POTATO)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);

        // 煤炭土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.CoalPotatoes.get(), 1)
                .pattern("CCC")
                .pattern("CPC")
                .pattern("CCC")
                .define('C', Items.COAL)
                .define('P', SpPotato.CarrotsPotato.get())
                .unlockedBy("has_carrots_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.CarrotsPotato.get()))
                .save(consumer);

        // 铜土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.COPPER_POTATO.get(), 1)
                .pattern("III")
                .pattern("IPI")
                .pattern("III")
                .define('I', Items.COPPER_INGOT)
                .define('P', SpPotato.CoalPotatoes.get())
                .unlockedBy("has_coal_potatoes", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.CoalPotatoes.get()))
                .save(consumer);

        // 青金石土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.LapsPotato.get(), 1)
                .pattern("LLL")
                .pattern("LPL")
                .pattern("LLL")
                .define('L', Items.LAPIS_LAZULI)
                .define('P', SpPotato.COPPER_POTATO.get())
                .unlockedBy("has_copper_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.COPPER_POTATO.get()))
                .save(consumer);

        // 铁土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.IRON_POTATO.get(), 1)
                .pattern("III")
                .pattern("IPI")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('P', SpPotato.LapsPotato.get())
                .unlockedBy("has_laps_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.LapsPotato.get()))
                .save(consumer);

        // 红石土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.REDSTONE_POTATO.get(), 1)
                .pattern("RRR")
                .pattern("RPR")
                .pattern("RRR")
                .define('R', Items.REDSTONE)
                .define('P', SpPotato.IRON_POTATO.get())
                .unlockedBy("has_iron_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.IRON_POTATO.get()))
                .save(consumer);

        // 金土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.GOLD_POTATO.get(), 1)
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .define('P', SpPotato.REDSTONE_POTATO.get())
                .unlockedBy("has_redstone_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.REDSTONE_POTATO.get()))
                .save(consumer);

        // 钻石土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.DIAMOND_POTATO.get(), 1)
                .pattern("DDD")
                .pattern("DPD")
                .pattern("DDD")
                .define('D', Items.DIAMOND)
                .define('P', SpPotato.GOLD_POTATO.get())
                .unlockedBy("has_gold_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.GOLD_POTATO.get()))
                .save(consumer);

        // 黑曜石土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.ObsidianPotato.get(), 1)
                .pattern("OOO")
                .pattern("OPO")
                .pattern("OOO")
                .define('O', Items.OBSIDIAN)
                .define('P', SpPotato.DIAMOND_POTATO.get())
                .unlockedBy("has_diamond_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.DIAMOND_POTATO.get()))
                .save(consumer);

        // 绿宝石土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.EmeraldPotato.get(), 1)
                .pattern("EEE")
                .pattern("EPE")
                .pattern("EEE")
                .define('E', Items.EMERALD)
                .define('P', Items.BAKED_POTATO)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);

        // 下界合金土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.NetheritePotato.get(), 1)
                .pattern("NNN")
                .pattern("NPN")
                .pattern("NNN")
                .define('N', Items.NETHERITE_INGOT)
                .define('P', SpPotato.EmeraldPotato.get())
                .unlockedBy("has_emerald_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.EmeraldPotato.get()))
                .save(consumer);

        // 下界之星土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.NetherStarPotato.get(), 1)
                .pattern("SSS")
                .pattern("SPS")
                .pattern("SSS")
                .define('S', Items.NETHER_STAR)
                .define('P', SpPotato.NetheritePotato.get())
                .unlockedBy("has_netherite_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.NetheritePotato.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.BlazePotato.get(), 1)
                .pattern("SSS")
                .pattern("SPS")
                .pattern("SSS")
                .define('S', Items.LAVA_BUCKET)
                .define('P', SpPotato.ObsidianPotato.get())
                .unlockedBy("has_blaze_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.EnchantedPotato.get(), 1)
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GGG")
                .define('G', Items.GOLD_BLOCK)
                .define('P', SpPotato.GOLD_POTATO.get())
                .unlockedBy("has_golden_enc_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.GOLD_POTATO.get()))
                .save(consumer);

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(Items.BAKED_POTATO),
                RecipeCategory.MISC,
               SpPotato.CookCookedPotato.get(),
                0.7f,
                200
        ).unlockedBy(
                "cook_cooked_potato",
                RegistrateRecipeProvider.has( SpPotato.CookCookedPotato.get())
        ).save(consumer, new ResourceLocation(MODID, "cook_cooked_potato"));

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(SpPotato.InfinitePotato.get()),
                RecipeCategory.MISC,
                SpPotato.InfinitePotatoCooked.get(),
                0.7f,
                200
        ).unlockedBy(
                "cook_infinite_potato",
                RegistrateRecipeProvider.has( SpPotato.InfinitePotatoCooked.get())
        ).save(consumer, new ResourceLocation(MODID, "cook_infinite_potato"));




        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.HONEY_POTATO.get(), 1)
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GGG")
                .define('G', Items.HONEY_BOTTLE)
                .define('P', Items.BAKED_POTATO)
                .unlockedBy("has_honey_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.PotatoSalaed.get(), 1)
                .pattern("GFG")
                .pattern("FPF")
                .pattern("GFG")
                .define('G', Items.SWEET_BERRIES)
                .define('P',  SpPotato.HONEY_POTATO.get())
                .define('F', Items.GLOW_BERRIES)
                .unlockedBy("has_potato_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.MysteriousPotato.get(), 1)
                .pattern("   ")
                .pattern("ABC")
                .pattern("   ")
                .define('A', SpItem.Cyclone.get())
                .define('B',  SpPotato.GOLD_POTATO.get())
                .define('C', SpItem.Joker.get())
                .unlockedBy("has_mysterious_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.GOLD_POTATO.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.LootKingPotato.get(), 1)
                .pattern("ABA")
                .pattern("CAC")
                .pattern("BCB")
                .define('A', SpPotato.DIAMOND_POTATO.get())
                .define('B',  SpPotato.EnchantedPotato.get())
                .define('C', SpPotato.EmeraldPotato.get())
                .unlockedBy("has_loot_king_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.EnchantedPotato.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpItem.WaveCrystal.get(), 1)
                .pattern("A A")
                .pattern(" B ")
                .pattern("A A")
                .define('A', Items.SEA_LANTERN)
                .define('B', SpItem.WaveCore.get())
                .unlockedBy("has_wave_crystal", InventoryChangeTrigger.TriggerInstance.hasItems( SpItem.WaveCore.get()))
                .save(consumer);


    }

    private static void addSpecialPotatoRecipes(Consumer<FinishedRecipe> consumer) {
        // 缇宝土豆 - 钻石土豆 + 末影土豆（8）
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.TirithippusPotato.get(), 1)
                .pattern("EEE")
                .pattern("EDE")
                .pattern("EEE")
                .define('E', SpPotato.EnderPotato.get())
                .define('D', SpPotato.DIAMOND_POTATO.get())
                .unlockedBy("has_diamond_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.DIAMOND_POTATO.get()))
                .save(consumer);

        // 末影土豆 - 烤土豆 + 末影珍珠（8）
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.EnderPotato.get(), 1)
                .pattern("EEE")
                .pattern("EPE")
                .pattern("EEE")
                .define('E', Items.ENDER_PEARL)
                .define('P', Items.BAKED_POTATO)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);

        // 菲比土豆 - 复杂的合成配方
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.FeibuPotato.get(), 1)
                .pattern("GCG")
                .pattern("NPT")
                .pattern("GRG")
                .define('G', Items.GLOWSTONE_DUST)
                .define('C', Items.CLOCK)
                .define('N', Items.NETHERITE_INGOT)
                .define('P', Items.BAKED_POTATO)
                .define('T', Items.TNT)
                .define('R', Items.GOLDEN_APPLE)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);

        // 潮汐土豆
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.OceanPotato.get(), 1)
                .pattern("HCH")
                .pattern("IPI")
                .pattern("HSH")
                .define('H', Items.HEART_OF_THE_SEA)
                .define('C', SpItem.WaveCrystal.get())
                .define('I', Items.INK_SAC)
                .define('P', Items.BAKED_POTATO)
                .define('S', Items.NAUTILUS_SHELL)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);

        // 禅定土豆 - 烤土豆 + 绿色染料（8）
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.GreenPotato.get(), 1)
                .pattern("DDD")
                .pattern("DPD")
                .pattern("DDD")
                .define('D', Items.GREEN_DYE)
                .define('P', Items.BAKED_POTATO)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.SonicPotato.get(), 1)
                .pattern("DDD")
                .pattern("DPD")
                .pattern("DDD")
                .define('D', Items.ECHO_SHARD)
                .define('P', SpPotato.ObsidianPotato.get())
                .unlockedBy("has_sonic_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.ObsidianPotato.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.CakePotato.get(), 1)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', SpPotato.COPPER_POTATO.get())
                .define('B', SpPotato.CarrotsPotato.get())
                .define('C', SpPotato.CoalPotatoes.get())
                .define('D', SpPotato.LapsPotato.get())
                .define('E', Items.CAKE)
                .define('F', SpPotato.IRON_POTATO.get())
                .define('G', SpPotato.REDSTONE_POTATO.get())
                .define('H', SpPotato.GOLD_POTATO.get())
                .define('I', SpPotato.DIAMOND_POTATO.get())
                .unlockedBy("has_cake", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CAKE))
                .save(consumer);

        // 蛋糕王土豆 - 复杂的合成配方
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.CakeKingPotato.get(), 1)
                .pattern("NDE")
                .pattern("SCB")
                .pattern("EWN")
                .define('N', Items.NETHERITE_INGOT)
                .define('D', Items.DRAGON_EGG)
                .define('E', SpPotato.EnchantedPotato.get())
                .define('S', Items.NETHER_STAR)
                .define('C', SpPotato.CakePotato.get())
                .define('B', Items.BEACON)
                .define('W', Items.ELYTRA)
                .unlockedBy("has_cake_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.CakePotato.get()))
                .save(consumer);

        // 土豆制造 - 复杂的合成配方
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.MadeInPotato.get(), 1)
                .pattern("NEN")
                .pattern("CPC")
                .pattern("NCN")
                .define('N', Items.NETHER_STAR)
                .define('E', SpPotato.EnchantedPotato.get())
                .define('C', Items.CLOCK)
                .define('P', SpPotato.NetherStarPotato.get())
                .unlockedBy("has_nether_star_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.NetherStarPotato.get()))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.InfinitePotato.get(), 1)
                .pattern("CCC")
                .pattern("CPC")
                .pattern("CCC")
                .define('C', Items.POTATO)
                .define('P', Items.NETHER_STAR)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.AnnoPotato.get(), 1)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DDD")
                .define('A', Items.PINK_WOOL)
                .define('B', Items.SUGAR)
                .define('C', SpPotato.REDSTONE_POTATO.get())
                .define('D', Items.WHITE_WOOL)
                .unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.SuperPotato.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', Items.TNT)
                .define('B', Items.NETHER_STAR)
                .define('C', Items.GLOWSTONE_DUST)
                .define('D', Items.BAKED_POTATO)
                .unlockedBy("has_super_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.NetherStarPotato.get()))
                .save(consumer);






        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpItem.bear_rabbit_tank.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("AEA")
                .define('A', Items.NETHER_STAR)
                .define('B', SpPotato.IRON_POTATO.get())
                .define('C', SpItem.rabbit.get())
                .define('D', SpPotato.DIAMOND_POTATO.get())
                .define('E', SpPotato.REDSTONE_POTATO.get())
                .unlockedBy("has_iron_potato", InventoryChangeTrigger.TriggerInstance.hasItems(SpPotato.IRON_POTATO.get()))
                .save(consumer);

        // 气泡兔坦土豆合成表
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, SpPotato.BuildPotato.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', Items.NETHER_STAR)
                .define('B', SpPotato.DIAMOND_POTATO.get())
                .define('C', SpItem.rabbit.get())
                .define('D', SpItem.bear_rabbit_tank.get())
                .unlockedBy("has_bear_rabbit_tank", InventoryChangeTrigger.TriggerInstance.hasItems(SpItem.bear_rabbit_tank.get()))
                .save(consumer);

    }

    public static void infinityRecipe(Consumer<FinishedRecipe> consumer) {
        ModShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SpPotato.EveryThingPotato.get())
                .pattern("SPPPPPPPH")
                .pattern("pTCMSLJTP")
                .pattern("pRBNUOiRP")
                .pattern("pADIbIDSP")
                .pattern("pnDEPADsP")
                .pattern("pgDIeIDGP")
                .pattern("pRd#HrMRP")
                .pattern("pTZsS?BTP")
                .pattern("cPPP!PPPc")

                .showNotification(true)
                .unlockedBy("has_item", has((ItemLike)ModItems.infinity_ingot.get())).save(consumer);
    }


    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike tagKey) {
        return inventoryTrigger(new ItemPredicate[]{ItemPredicate.Builder.item().of(tagKey).build()});
    }




    public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
        return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
    }
}