package com.As13.endtropy.register;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.block.EndAnchorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BkBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Endtropy.MODID);

    public static final RegistryObject<BlockEntityType<EndAnchorBlockEntity>> END_ANCHOR=
            BLOCK_ENTITY_TYPES.register("end_anchor_entity",
                    ()-> BlockEntityType.Builder.of(EndAnchorBlockEntity::new,
                            BKBlocks.END_ANCHOR.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }

}
