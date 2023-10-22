package com.As13.endtropy.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EndAnchorBlockEntity extends TheEndPortalBlockEntity {
    public EndAnchorBlockEntity(BlockEntityType<?> p_155855_, BlockPos p_155856_, BlockState p_155857_) {
        super(p_155855_, p_155856_, p_155857_);
    }

    public EndAnchorBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
}
