package com.dinzeer.spudiversity.compat.avaritia;


import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import committee.nova.mods.avaritia.core.singularity.Singularity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class SRSingularities {




    public static final Singularity cook_cooked_potato = createSingularity(SpPotato.CookCookedPotato,0, 0);

    public static List<Singularity> getDefaults() {
        return List.of(
                cook_cooked_potato
        );
    }







    private static Singularity createSingularity(RegistryEntry<? extends Item> item, int overlayColor, int underlayColor) {
        return Singularity.create(item.getId(),
                "item.spudiversity." + item.getId().getPath(),
                new int[]{overlayColor, underlayColor},
                Ingredient.of(item.get()));
    }


}
