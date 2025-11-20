package com.dinzeer.spudiversity.data;

import com.dinzeer.spudiversity.Spudiversity;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class SpCuriosTestProvider extends CuriosDataProvider {

    public SpCuriosTestProvider(String modId, PackOutput output,
                                ExistingFileHelper fileHelper,
                                CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }


    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.createSlot("potato")
                .size(3)
                .icon(Spudiversity.prefix("slot/potato"))
                .addCosmetic(true);

         this.createEntities("potato_entities")
                 .addPlayer()
                 .addSlots("potato");
    }
}