package com.As13.endtropy.register;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.enchantment.ProtectionVoidEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BKEnchantment {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Endtropy.MODID);
    public static final RegistryObject<Enchantment> PROTECTION_VOID = REGISTRY.register("protection_void", ProtectionVoidEnchantment::new);

}
