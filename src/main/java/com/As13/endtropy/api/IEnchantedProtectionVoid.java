package com.As13.endtropy.api;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

public interface IEnchantedProtectionVoid {
    void addEnchantedItem(ItemStack stack,int index);
    void clearEnchantedItems();
    NonNullList<ItemStack> getEnchantedItems();
}
