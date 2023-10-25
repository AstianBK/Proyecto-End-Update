package com.As13.endtropy.client.projectile;

import com.As13.endtropy.register.BkEntityTypes;
import com.As13.endtropy.register.BkItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class WarpedThornProjectile extends Arrow implements ItemSupplier {
    public WarpedThornProjectile(EntityType<? extends Arrow> p_36858_, Level p_36859_) {
        super(p_36858_, p_36859_);
    }

    public WarpedThornProjectile(Level p_36866_, LivingEntity p_36867_) {
        super(BkEntityTypes.WARPED_THORN.get(), p_36866_);
        this.setOwner(p_36867_);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        if(p_36757_.getEntity() instanceof LivingEntity){
            LivingEntity target= (LivingEntity) p_36757_.getEntity();
            target.invulnerableTime=0;
        }
        super.onHitEntity(p_36757_);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(BkItems.FAKE_WARPED_THORN.get());
    }
}
