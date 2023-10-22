package com.As13.endtropy.mixin;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.block.RespawnEndBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(Player.class)
public abstract class PlayerMixins{
    @Inject(method = "findRespawnPositionAndUseSpawnBlock", at = @At(value = "RETURN"), cancellable = true)
    private static void findRespawn(ServerLevel world, BlockPos pos, float f, boolean bl, boolean bl2, CallbackInfoReturnable<Optional<Vec3>> cir) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (Endtropy.respawnAfterCredits) {
            Endtropy.respawnAfterCredits = false;
            return;
        }

        if (block instanceof RespawnEndBlock && RespawnEndBlock.canSetSpawn(world)) {
            Optional<Vec3> optional = RespawnEndBlock.findStandUpPosition(EntityType.PLAYER, world, pos);
            if (!bl2 && optional.isPresent()) {
                world.setBlock(pos, blockState.setValue(RespawnEndBlock.CHARGE, blockState.getValue(RespawnEndBlock.CHARGE) - 1), Block.UPDATE_ALL);
            }

            cir.setReturnValue(optional);
        }
    }
}
