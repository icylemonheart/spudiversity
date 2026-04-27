package com.dinzeer.spudiversity.capability.impl;

import net.minecraft.world.item.ItemStack;
import java.util.List;

public interface IAlchemistPotatoCap {
    /**
     * 尝试注入一瓶药水，成功返回true，失败返回false（已满或无效）
     */
    boolean addPotion(ItemStack potion);

    /**
     * 获取所有存储的药水（最多10个，可能包含空栈）
     */
    List<ItemStack> getStoredPotions();

    /**
     * 随机获取一个存储的药水（可能为空）
     * @param randomIndex 0-9 随机索引
     */
    ItemStack getRandomPotion(int randomIndex);
}