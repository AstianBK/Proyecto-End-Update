package com.As13.endtropy.register;

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
    public static final CreativeModeTab EXPANSION_TAB = new CreativeModeTab("Endtropy") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BkItems.PILK.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> pItems) {
            super.fillItemList(pItems);
            PreOrdenInit();
            pItems.sort(stackComparator);
        }
    };

    public static void PreOrdenInit(){
        List<Item> itemList= Arrays.asList(BkItems.PILK.get(),BkItems.ENDERTHORN_SPAWN_EGG.get(),BkItems.WARPED_THORN.get());

        stackComparator= Ordering.explicit(itemList).onResultOf(ItemStack::getItem);
    }

}
