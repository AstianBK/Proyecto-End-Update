package com.As13.expansion.register;

import com.google.common.collect.Ordering;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BkCreativeModeTab {
    static Comparator<ItemStack> stackComparator;
    public static final CreativeModeTab EXPANSION_TAB = new CreativeModeTab("Expansion") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SHEEP_SPAWN_EGG);
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> pItems) {
            super.fillItemList(pItems);
            PreOrdenInit();
            pItems.sort(stackComparator);
        }
    };

    public static void PreOrdenInit(){
        List<Item> itemList= Arrays.asList(Items.SHEEP_SPAWN_EGG);

        stackComparator= Ordering.explicit(itemList).onResultOf(ItemStack::getItem);
    }

}
