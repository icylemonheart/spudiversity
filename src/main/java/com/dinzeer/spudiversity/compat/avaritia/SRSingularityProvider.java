package com.dinzeer.spudiversity.compat.avaritia;

import committee.nova.mods.avaritia.init.data.provider.base.SingularityProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

import java.util.concurrent.CompletableFuture;

public class SRSingularityProvider extends SingularityProvider {
    public SRSingularityProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider, @MonotonicNonNull ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.addSingularity(SRSingularities.getDefaults());
    }

    @Override
    public String getName() {
        return "Srelic Singularities Data Provider";
    }
}