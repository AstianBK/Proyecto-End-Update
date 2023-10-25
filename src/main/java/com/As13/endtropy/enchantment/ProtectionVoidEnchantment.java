package com.As13.endtropy.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ProtectionVoidEnchantment extends Enchantment {
    public ProtectionVoidEnchantment() {
        super(Rarity.UNCOMMON,EnchantmentCategory.BREAKABLE,EquipmentSlot.values());
    }
}
