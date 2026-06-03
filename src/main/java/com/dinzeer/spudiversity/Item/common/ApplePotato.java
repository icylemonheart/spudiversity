package com.dinzeer.spudiversity.item.common;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ApplePotato extends Item {
    public ApplePotato(Properties p_41383_) {
        super(p_41383_.food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()));
    }

}
