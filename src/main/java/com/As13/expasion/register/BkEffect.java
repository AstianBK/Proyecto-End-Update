package com.As13.expasion.register;

import com.As13.expasion.Expansion;
import com.As13.expasion.client.effect.PurpleCoat;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BkEffect {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Expansion.MODID);

    public static final RegistryObject<MobEffect> PURPLE_COAT = EFFECT.register("purple_coat", PurpleCoat::new);
}
