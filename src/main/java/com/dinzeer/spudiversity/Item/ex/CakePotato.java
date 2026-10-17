package com.dinzeer.spudiversity.item.ex;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CakePotato extends Item {
    public CakePotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(20)
                .saturationModifier(1f)
                .build()));
    }
}
