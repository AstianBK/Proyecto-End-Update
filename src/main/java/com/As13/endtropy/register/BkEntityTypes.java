package com.As13.endtropy.register;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.client.entity.EndCow;
import com.As13.endtropy.client.entity.EndPig;
import com.As13.endtropy.client.entity.EndSheep;
import com.As13.endtropy.client.entity.EnderThorn;
import com.As13.endtropy.client.projectile.WarpedThornProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BkEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Endtropy.MODID);

    public static final RegistryObject<EntityType<EndPig>> END_PIG =
            ENTITY_TYPES.register("end_pig",
                    () -> EntityType.Builder.of(EndPig::new, MobCategory.MONSTER)
                            .sized(0.60f, 1.0f)
                            .build(new ResourceLocation(Endtropy.MODID, "end_pig").toString()));

    public static final RegistryObject<EntityType<EndCow>> END_COW =
            ENTITY_TYPES.register("end_cow",
                    () -> EntityType.Builder.of(EndCow::new, MobCategory.MONSTER)
                            .sized(0.60f, 1.0f)
                            .build(new ResourceLocation(Endtropy.MODID, "end_cow").toString()));

    public static final RegistryObject<EntityType<EndSheep>> END_SHEEP =
            ENTITY_TYPES.register("end_sheep",
                    () -> EntityType.Builder.of(EndSheep::new, MobCategory.MONSTER)
                            .sized(0.60f, 1.0f)
                            .build(new ResourceLocation(Endtropy.MODID, "end_sheep").toString()));

    public static final RegistryObject<EntityType<EnderThorn>> ENDERTHORN =
            ENTITY_TYPES.register("enderthorn",
                    () -> EntityType.Builder.of(EnderThorn::new, MobCategory.MONSTER)
                            .sized(2.0f, 1.5f)
                            .build(new ResourceLocation(Endtropy.MODID, "enderthorn").toString()));

    public static final RegistryObject<EntityType<WarpedThornProjectile>> WARPED_THORN = ENTITY_TYPES
            .register("warped_thorn", () -> EntityType.Builder.<WarpedThornProjectile>of(WarpedThornProjectile::new, MobCategory.MISC)
                    .fireImmune().sized(0.2F, 0.2F).build(Endtropy.MODID + "warped_thorn"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
