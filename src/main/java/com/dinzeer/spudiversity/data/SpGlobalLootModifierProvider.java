package com.dinzeer.spudiversity.data;

import com.dinzeer.spudiversity.Spudiversity;

import com.dinzeer.spudiversity.data.loot.CustomAddTemplateModifier;
import com.dinzeer.spudiversity.regsitry.SpItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;


public class SpGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public SpGlobalLootModifierProvider(PackOutput output) {
        super(output, Spudiversity.MODID);
    }

    @Override
    protected void start() {
        String endCityTreasureLootTable = "chests/end_city_treasure";
        LootItemCondition.Builder condition = LootTableIdCondition.builder(ResourceLocation.parse(endCityTreasureLootTable));
        Item template = SpItem.EdelweissUpgradeTemplate.get();
        add("end_city_upgrade_template",
                new CustomAddTemplateModifier(new LootItemCondition[] { condition.build() }, template, 1, 1,0.18f));
        add("end_city_bake_potato",
                new CustomAddTemplateModifier(new LootItemCondition[] { condition.build() }, Items.BAKED_POTATO, 1, 16,0.2f));


        // 参数解释：AddItemModifier(LootItemCondition condition, Item item, int countMin, int countMax, int weight)
    }
}