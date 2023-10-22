package com.As13.endtropy.mixin;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.api.IEnchantedProtectionVoid;
import com.As13.endtropy.block.EndAnchorBlock;
import com.As13.endtropy.register.BKBlocks;
import com.As13.endtropy.register.BKEnchantment;
import com.As13.endtropy.register.BkItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerList.class)

public abstract class PlayerListMixins {
    @Inject(method = "respawn", at = @At(value = "RETURN"), cancellable = true)
    private void respawn(ServerPlayer player,boolean b, CallbackInfoReturnable<ServerPlayer> cir) {
        ServerPlayer respawnPlayer = cir.getReturnValue();
        if(player instanceof IEnchantedProtectionVoid iPlayer){
            List<ItemStack> enchantedItems=iPlayer.getEnchantedItems();
            if(!enchantedItems.isEmpty()){
                for(int i = 0 ; i<enchantedItems.size(); i++){
                    ItemStack stack=enchantedItems.get(i);
                    if(!stack.isEmpty()){

                        respawnPlayer.getInventory().setItem(i,stack);
                    }
                }
                iPlayer.clearEnchantedItems();
            }
        }
        cir.setReturnValue(respawnPlayer);
    }
}
