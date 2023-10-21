package com.As13.expansion.register;

import com.As13.expansion.Expansion;
import com.As13.expansion.item.PilkBucketItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BkItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Expansion.MODID);

    public static final RegistryObject<Item> PILK = ITEMS.register("pilk",()->new PilkBucketItem(
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .effect(()->new MobEffectInstance(BkEffect.PURPLE_COAT.get(),160,0),1.0F).build()).tab(CreativeModeTab.TAB_FOOD)));

}
