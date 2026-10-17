package com.dinzeer.spudiversity.data.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class CustomAddTemplateModifier extends LootModifier {
    public static final MapCodec<CustomAddTemplateModifier> CODEC =
        RecordCodecBuilder.mapCodec(inst ->
            codecStart(inst)
                .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(m -> m.item))
                .and(Codec.INT.fieldOf("min_count").forGetter(m -> m.minCount))
                .and(Codec.INT.fieldOf("max_count").forGetter(m -> m.maxCount))
                .and(Codec.FLOAT.optionalFieldOf("weight", 1.0f).forGetter(m -> m.weight))
                .apply(inst, CustomAddTemplateModifier::new)
        );

    private final Item item;
    private final int minCount;
    private final int maxCount;
    private final float weight;

    public CustomAddTemplateModifier(LootItemCondition[] conditions, Item item, int min, int max, float weight) {
        super(conditions);
        this.item = item;
        this.minCount = min;
        this.maxCount = max;
        this.weight = weight;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() < weight) {
            int count = minCount + context.getRandom().nextInt(maxCount - minCount + 1);
            generatedLoot.add(new ItemStack(item, count));
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

}
