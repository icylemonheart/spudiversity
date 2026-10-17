// PlayerKillBossAbilities.java
package com.dinzeer.spudiversity.capability.impl;

import com.dinzeer.spudiversity.Spudiversity;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PlayerKillBossAbilities implements INBTSerializable<CompoundTag> {
    public static final EntityCapability<PlayerKillBossAbilities, Void> CAPABILITY =
            EntityCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "kill_bosses_abilities"),
                    PlayerKillBossAbilities.class);

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
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("kill_wither", getKillWither());
        nbt.putBoolean("kill_ender_dragon", getKillEnderDragon());
        nbt.putBoolean("kill_warden", getKillWarden());
        nbt.putBoolean("kill_old_grudian", getKillOldGrudian());
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.killWither = nbt.getBoolean("kill_wither");
        this.killEnderDragon = nbt.getBoolean("kill_ender_dragon");
        this.killWarden = nbt.getBoolean("kill_warden");
        this.killOldGrudian = nbt.getBoolean("kill_old_grudian");
    }
}
