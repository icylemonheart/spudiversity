package com.dinzeer.spudiversity.capability.impl;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlchemistPotatoCapability implements IAlchemistPotatoCap, INBTSerializable<CompoundTag> {
    public static final Capability<IAlchemistPotatoCap> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
    private static final int MAX_SLOTS = 10;
    private final List<ItemStack> potions = new ArrayList<>(MAX_SLOTS);

    public AlchemistPotatoCapability() {
        for (int i = 0; i < MAX_SLOTS; i++) {
            potions.add(ItemStack.EMPTY);
        }
    }

    @Override
    public boolean addPotion(ItemStack potion) {
        if (potion.isEmpty()) return false;
        // 寻找第一个空位
        for (int i = 0; i < MAX_SLOTS; i++) {
            if (potions.get(i).isEmpty()) {
                potions.set(i, potion.copyWithCount(1));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ItemStack> getStoredPotions() {
        return new ArrayList<>(potions);
    }

    @Override
    public ItemStack getRandomPotion(int randomIndex) {
        if (randomIndex < 0 || randomIndex >= MAX_SLOTS) return ItemStack.EMPTY;
        return potions.get(randomIndex);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        for (ItemStack stack : potions) {
            list.add(stack.save(new CompoundTag()));
        }
        tag.put("Potions", list);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        potions.clear();
        ListTag list = nbt.getList("Potions", Tag.TAG_COMPOUND);
        for (int i = 0; i < MAX_SLOTS; i++) {
            if (i < list.size()) {
                potions.add(ItemStack.of(list.getCompound(i)));
            } else {
                potions.add(ItemStack.EMPTY);
            }
        }
    }

    public static class Provider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        private final AlchemistPotatoCapability cap = new AlchemistPotatoCapability();
        private final LazyOptional<IAlchemistPotatoCap> holder = LazyOptional.of(() -> cap);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return INSTANCE.orEmpty(cap, holder);
        }

        @Override
        public CompoundTag serializeNBT() {
            return cap.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            cap.deserializeNBT(nbt);
        }
    }
}