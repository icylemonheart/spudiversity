// PlayerPermanentAbilities.java
package com.dinzeer.spudiversity.capability.impl;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerPermanentAbilities implements INBTSerializable<CompoundTag> {
    public static final Capability<PlayerPermanentAbilities> CAPABILITY =
            CapabilityManager.get(new CapabilityToken<>() {});

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
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("active_potato", activePotato);
        nbt.putString("active_potato_off", activePotatoOff);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.activePotato = nbt.getString("active_potato");
        this.activePotatoOff = nbt.getString("active_potato_off");
    }

    public static class Provider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        private final PlayerPermanentAbilities abilities = new PlayerPermanentAbilities();
        private final LazyOptional<PlayerPermanentAbilities> optional = LazyOptional.of(() -> abilities);

        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if (cap == CAPABILITY) {
                return optional.cast();
            }
            return LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            return abilities.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            abilities.deserializeNBT(nbt);
        }
    }
}