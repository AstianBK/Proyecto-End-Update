package com.As13.expansion.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RespawnEndBlockEntity extends TheEndPortalBlockEntity {
    public RespawnEndBlockEntity(BlockEntityType<?> p_155855_, BlockPos p_155856_, BlockState p_155857_) {
        super(p_155855_, p_155856_, p_155857_);
    }

    public RespawnEndBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
}
