package com.dinzeer.spudiversity.compat.avaritia;


import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import committee.nova.mods.avaritia.core.singularity.Singularity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class SRSingularities {




    public static final Singularity COOK_COOKED_POTATO = createSingularity(SpPotato.CookCookedPotato,0, 0);
    public static final Singularity POISONOUS_POTATO = createSingularity(Items.POISONOUS_POTATO,7757615, 3232545,"poisonous_potato");

    public static List<Singularity> getDefaults() {
        return List.of(
                COOK_COOKED_POTATO,
                POISONOUS_POTATO
        );
    }







    private static Singularity createSingularity(RegistryEntry<? extends Item> item, int overlayColor, int underlayColor) {
        return Singularity.create(item.getId(),
                "item.spudiversity." + item.getId().getPath(),
                new int[]{overlayColor, underlayColor},
                Ingredient.of(item.get()));
    }
    private static Singularity createSingularity(Item item, int overlayColor, int underlayColor,String id) {
        return Singularity.create(Spudiversity.prefix(id),
                "singularity.spudiversity." + id,
                new int[]{overlayColor, underlayColor},
                Ingredient.of(item));
    }

}
