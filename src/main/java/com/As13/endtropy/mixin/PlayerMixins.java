package com.As13.endtropy.mixin;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.api.IEnchantedProtectionVoid;
import com.As13.endtropy.block.EndAnchorBlock;
import com.As13.endtropy.register.BKEnchantment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(Player.class)
public abstract class PlayerMixins extends LivingEntity implements IEnchantedProtectionVoid {
    @Shadow @Final private Inventory inventory;
    public NonNullList<ItemStack> enchantedItems = NonNullList.withSize(42,ItemStack.EMPTY);

    protected PlayerMixins(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Inject(method = "dropEquipment", at = @At(value = "HEAD"))
    private void dropEquipment(CallbackInfo ci) {
        if (!this.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            for(int i = 0 ; i<this.inventory.getContainerSize();i++){
                ItemStack stack = this.inventory.getItem(i);
                if(EnchantmentHelper.getTagEnchantmentLevel(BKEnchantment.PROTECTION_VOID.get(), stack) > 0){
                    this.addEnchantedItem(stack.copy(),i);
                }
            }
        }
    }

    @Inject(method = "findRespawnPositionAndUseSpawnBlock", at = @At(value = "RETURN"), cancellable = true)
    private static void findRespawn(ServerLevel world, BlockPos pos, float f, boolean bl, boolean bl2, CallbackInfoReturnable<Optional<Vec3>> cir) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (Endtropy.respawnAfterCredits) {
            Endtropy.respawnAfterCredits = false;
            return;
        }
        if (block instanceof EndAnchorBlock && blockState.getValue(EndAnchorBlock.CHARGE)>0 && EndAnchorBlock.canSetSpawn(world)) {
            Optional<Vec3> optional = EndAnchorBlock .findStandUpPosition(EntityType.PLAYER, world, pos);
            if (!bl2 && optional.isPresent()) {
                world.setBlock(pos, blockState.setValue(EndAnchorBlock.CHARGE, blockState.getValue(EndAnchorBlock.CHARGE) - 1), Block.UPDATE_ALL);
            }
            cir.setReturnValue(optional);
        }
    }
    @Inject(method = "addAdditionalSaveData",at =@At(value = "HEAD"))
    public void addAdditionalSaveData(CompoundTag p_36265_, CallbackInfo ci) {
        ListTag listTag=new ListTag();
        if(!this.enchantedItems.isEmpty()){
            for (int i = 0 ; i<this.enchantedItems.size();i++){
                CompoundTag tag = new CompoundTag();
                tag.putByte("Slot",(byte)i);
                this.enchantedItems.get(i).save(tag);
                listTag.add(tag);
            }
        }
        p_36265_.put("enchantedItems",listTag);
    }

    @Inject(method = "readAdditionalSaveData",at =@At(value = "HEAD"))
    public void readAdditionalSaveData(CompoundTag p_36215_, CallbackInfo ci) {
        this.enchantedItems.clear();
        if(p_36215_.contains("enchantedItems",9)){
            ListTag tags = p_36215_.getList("enchantedItems",10);
            for(int i = 0; i<tags.size();i++){
                CompoundTag tag = tags.getCompound(i);
                int k = tag.getByte("Slot") & 255;
                ItemStack stack = ItemStack.of(tag);
                this.enchantedItems.set(k,stack);
            }
        }
    }

    @Override
    public void addEnchantedItem(ItemStack stack,int index) {
        this.enchantedItems.set(index,stack);
    }

    @Override
    public NonNullList<ItemStack> getEnchantedItems() {
        return this.enchantedItems;
    }

    @Override
    public void clearEnchantedItems() {
        this.enchantedItems.clear();
    }
}

