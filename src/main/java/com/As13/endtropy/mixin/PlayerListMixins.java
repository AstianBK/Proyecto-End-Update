package com.As13.endtropy.mixin;

import com.As13.endtropy.api.IEnchantedProtectionVoid;
import com.As13.endtropy.register.BKEnchantment;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                    Map<Enchantment,Integer> map = stack.getAllEnchantments().entrySet().stream().filter(
                            e->e.getKey() != BKEnchantment.PROTECTION_VOID.get()).
                            collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
                    if(!stack.isEmpty()){
                        EnchantmentHelper.setEnchantments(map,stack);
                        respawnPlayer.getInventory().setItem(i,stack);
                    }
                }
                iPlayer.clearEnchantedItems();
            }
        }
        cir.setReturnValue(respawnPlayer);
    }
}
