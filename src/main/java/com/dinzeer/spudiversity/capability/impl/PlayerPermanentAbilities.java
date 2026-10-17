// PlayerPermanentAbilities.java
package com.dinzeer.spudiversity.capability.impl;

import com.dinzeer.spudiversity.Spudiversity;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PlayerPermanentAbilities implements INBTSerializable<CompoundTag> {
    public static final EntityCapability<PlayerPermanentAbilities, Void> CAPABILITY =
            EntityCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "permanent_abilities"),
                    PlayerPermanentAbilities.class);

    private String activePotato = "";
    private String activePotatoOff = "";

    public String getActivePotato() {
        return activePotato;
    }

    public void setActivePotato(String crystalType) {
        this.activePotato = crystalType;
    }
    public void setActivePotatoOff(String crystalType) {
        this.activePotatoOff = crystalType;
    }
    public String getActivePotatoOff() {
        return activePotatoOff;
    }

    public void clearActivePotato() {
        this.activePotato = "";
    }
    public boolean hasActivePotato() {
        return !activePotato.isEmpty();
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("active_potato", activePotato);
        nbt.putString("active_potato_off", activePotatoOff);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.activePotato = nbt.getString("active_potato");
        this.activePotatoOff = nbt.getString("active_potato_off");
    }
}
