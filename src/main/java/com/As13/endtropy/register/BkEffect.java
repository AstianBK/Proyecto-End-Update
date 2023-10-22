package com.As13.endtropy.register;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.client.effect.PurpleCoat;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BkEffect {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Endtropy.MODID);

    public static final RegistryObject<MobEffect> PURPLE_COAT = EFFECT.register("purple_coat", PurpleCoat::new);
}
