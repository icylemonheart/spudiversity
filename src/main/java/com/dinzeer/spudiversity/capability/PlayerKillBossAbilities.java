// PlayerPermanentAbilities.java
package com.dinzeer.spudiversity.capability;

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

public class PlayerKillBossAbilities implements INBTSerializable<CompoundTag> {
    public static final Capability<PlayerKillBossAbilities> CAPABILITY =
            CapabilityManager.get(new CapabilityToken<>() {});



    private Boolean killWither = false;
    private Boolean killEnderDragon = false;
    private Boolean killWarden = false;
    private Boolean killOldGrudian = false;

    public Boolean getKillEnderDragon() {
        return killEnderDragon;
    }
    public void setKillEnderDragon(Boolean killEnderDragon) {
        this.killEnderDragon = killEnderDragon;
    }

    public Boolean getKillWither() {
        return killWither;
    }

    public void setKillWither(Boolean killWither) {
        this.killWither = killWither;
    }

    public Boolean getKillWarden() {
        return killWarden;
    }

    public void setKillWarden(Boolean killWarden) {
        this.killWarden = killWarden;
    }

    public Boolean getKillOldGrudian() {
        return killOldGrudian;
    }

    public void setKillOldGrudian(Boolean killOldGrudian) {
        this.killOldGrudian = killOldGrudian;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("kill_wither", getKillWither());
        nbt.putBoolean("kill_ender_dragon", getKillEnderDragon());
        nbt.putBoolean("kill_warden", getKillWarden());
        nbt.putBoolean("kill_old_grudian", getKillOldGrudian());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.killWither = nbt.getBoolean("kill_wither");
        this.killEnderDragon = nbt.getBoolean("kill_ender_dragon");
        this.killWarden = nbt.getBoolean("kill_warden");
        this.killOldGrudian = nbt.getBoolean("kill_old_grudian");
    }

    public static class Provider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        private final PlayerKillBossAbilities abilities = new PlayerKillBossAbilities();
        private final LazyOptional<PlayerKillBossAbilities> optional = LazyOptional.of(() -> abilities);

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