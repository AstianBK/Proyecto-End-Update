package com.As13.endtropy.register;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.block.RespawnEndBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BkBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Endtropy.MODID);

    public static final RegistryObject<BlockEntityType<RespawnEndBlockEntity>> RESPAWN_END=
            BLOCK_ENTITY_TYPES.register("respawn_end_entity",
                    ()-> BlockEntityType.Builder.of(RespawnEndBlockEntity::new,
                            BKBlocks.RESPAWN_END.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }

}
