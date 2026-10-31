package com.dinzeer.spudiversity.api;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Common-side registry for the items rendered by {@code ForeverRenderingLayer}.
 *
 * <p>MAIN is used by normal perpetual potatoes. OFF is reserved for the
 * independent off-slot potato (currently Tangxing); normal perpetual potatoes
 * must only be registered to MAIN.</p>
 */
public final class PerpetualPotatoRenderRegistry {

    private static final Map<String, Supplier<ItemStack>> MAIN_STACKS = new ConcurrentHashMap<>();
    private static final Map<String, Supplier<ItemStack>> OFF_STACKS = new ConcurrentHashMap<>();

    private PerpetualPotatoRenderRegistry() {
    }

    public static void registerMain(String key, ResourceLocation itemId) {
        registerMain(key, () -> stackFor(itemId));
    }

    public static void registerMain(String key, Supplier<ItemStack> stackFactory) {
        register(MAIN_STACKS, key, stackFactory);
    }

    public static void registerOff(String key, ResourceLocation itemId) {
        registerOff(key, () -> stackFor(itemId));
    }

    public static void registerOff(String key, Supplier<ItemStack> stackFactory) {
        register(OFF_STACKS, key, stackFactory);
    }

    public static ItemStack getMainStack(String key) {
        ItemStack stack = getFrom(MAIN_STACKS, key);
        if (!stack.isEmpty()) {
            return stack;
        }

        ResourceLocation id = parseNamespacedKey(key);
        return id == null ? ItemStack.EMPTY : stackFor(id);
    }

    public static ItemStack getOffStack(String key) {
        return getFrom(OFF_STACKS, key);
    }

    public static boolean isMainRegistered(String key) {
        return key != null && MAIN_STACKS.containsKey(key);
    }

    public static boolean isOffRegistered(String key) {
        return key != null && OFF_STACKS.containsKey(key);
    }

    private static void register(Map<String, Supplier<ItemStack>> registry, String key,
                                 Supplier<ItemStack> stackFactory) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(stackFactory, "stackFactory");
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Perpetual potato render key must not be empty");
        }
        registry.put(key, stackFactory);
    }

    private static ItemStack getFrom(Map<String, Supplier<ItemStack>> registry, String key) {
        if (key == null || key.isEmpty()) {
            return ItemStack.EMPTY;
        }

        Supplier<ItemStack> stackFactory = registry.get(key);
        if (stackFactory == null) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = stackFactory.get();
        return stack == null ? ItemStack.EMPTY : stack;
    }

    private static ItemStack stackFor(ResourceLocation itemId) {
        Objects.requireNonNull(itemId, "itemId");
        Item item = BuiltInRegistries.ITEM.get(itemId);
        return item == null ? ItemStack.EMPTY : item.getDefaultInstance();
    }

    private static ResourceLocation parseNamespacedKey(String key) {
        if (key == null || key.indexOf(':') <= 0) {
            return null;
        }
        return ResourceLocation.tryParse(key);
    }
}
