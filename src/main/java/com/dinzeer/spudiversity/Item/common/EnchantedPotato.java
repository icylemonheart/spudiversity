package com.dinzeer.spudiversity.item.common;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;

public class EnchantedPotato extends Item {
    public EnchantedPotato(Properties p_41383_) {
        super(p_41383_.rarity(Rarity.EPIC).food(Foods.ENCHANTED_GOLDEN_APPLE));
    }
    public boolean isFoil(ItemStack p_41172_) {
        return true;
    }
}
