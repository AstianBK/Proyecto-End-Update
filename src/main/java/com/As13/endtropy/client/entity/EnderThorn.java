package com.As13.endtropy.client.entity;

import com.As13.endtropy.client.projectile.WarpedThornProjectile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EnderThorn extends Monster implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Byte> DATA_GOALS = SynchedEntityData.defineId(EnderThorn.class, EntityDataSerializers.BYTE);
    private int throwTimer;
    private boolean throwSpine;
    public EnderThorn(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.throwTimer=0;
        this.throwSpine=false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.16D)
                .add(Attributes.ATTACK_DAMAGE,8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4D)
                .add(Attributes.ARMOR,7.0D)
                .add(Attributes.FOLLOW_RANGE,45.0D);
    }
    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1,new EnderThornAttackGoal(this,1.5D,true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    private   <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && !this.isAggressive() && !this.swinging && !this.throwSpine) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderthorn.move", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(1.0D);
        }else if(event.isMoving() && this.isAggressive() && !this.swinging && !this.throwSpine){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderthorn.move", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        }else if(this.swinging){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderthorn.attackmelee", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            event.getController().setAnimationSpeed(1.0D);
        }else if(this.throwSpine){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderthorn.spinethrow", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            event.getController().setAnimationSpeed(1.0D);
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderthorn.idle", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(1.0D);
        }
        return PlayState.CONTINUE;
    }

    public void setThornMode(boolean pBoolean){
        byte b0 = this.entityData.get(DATA_GOALS);
        this.entityData.set(DATA_GOALS,pBoolean ? (byte)(b0 | 2) : (byte)(b0 & -3));
        if(pBoolean){
            this.playSound(SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH,1.0F,-3.0F);
        }

    }

    public boolean thornOn(){
        return (this.entityData.get(DATA_GOALS) & 2)!=0;
    }

    public void throwSpine(LivingEntity target){
        for(int i=0 ; i<3 ; i++){
            WarpedThornProjectile thorn=new WarpedThornProjectile(this.level,this);
            thorn.setPos(this.getX(),this.getY()+1.5D,this.getZ());
            thorn.shootFromRotation(this,this.getXRot(),this.yBodyRot-10.0F+10.0F*i,0.0F,1.0F,0.1f);
            this.level.addFreshEntity(thorn);
        }
        this.playSound(SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH,1.0F,-3.0F);

    }

    private boolean canThrow() {
        return this.throwTimer==0;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.throwTimer>0){
            this.throwTimer--;
        }
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if(this.thornOn()){
            if(p_21016_.getEntity() instanceof LivingEntity && p_21016_ instanceof EntityDamageSource){
                if(!((EntityDamageSource)p_21016_).isThorns()){
                    LivingEntity source = (LivingEntity) p_21016_.getEntity();
                    source.hurt(DamageSource.thorns(this),4.0F);
                }
            }
        }

        return super.hurt(p_21016_, p_21017_);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putBoolean("thornOn",this.thornOn());
    }

    @Override
    public void die(DamageSource p_21014_) {
        if(this.thornOn()){
            this.rainOfThorns();
        }
        super.die(p_21014_);
    }

    public void rainOfThorns(){
        for(int i=0;i<10;i++){
            for (int k=0;k<10;k++){
                WarpedThornProjectile thorn=new WarpedThornProjectile(this.level,this);
                thorn.setPos(this.getX(),this.getY()+1.0D,this.getZ());
                thorn.shootFromRotation(this, 30.0F*i, 30.0F*k, 0.0F, 1.5F, 1.0F);
                this.level.addFreshEntity(thorn);
            }
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        this.setThornMode(p_21450_.getBoolean("thornOn"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_GOALS,(byte)0);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this,"predicate",
                0.0F,this::predicate));
    }

    @Override
    public void handleEntityEvent(byte p_21375_) {
        if(p_21375_==4){
            this.throwSpine=true;
        }else if (p_21375_ == 60){
            this.throwSpine=false;
            this.throwTimer=200;
        }else {
            super.handleEntityEvent(p_21375_);
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    static class EnderThornAttackGoal extends MeleeAttackGoal{
        private final EnderThorn enderThorn;
        private int spineAttack;

        public EnderThornAttackGoal(EnderThorn p_25552_, double p_25553_, boolean p_25554_) {
            super(p_25552_, p_25553_, p_25554_);
            this.enderThorn=p_25552_;
            this.spineAttack=0;
        }

        @Override
        protected void checkAndPerformAttack(@NotNull LivingEntity entity, double distance) {
            double d0 = this.getAttackReachSqr(entity)+2.0D;
            if (distance <= d0 && this.getTicksUntilNextAttack() <= 0) {
                this.resetAttackCooldown();
                this.enderThorn.playSound(SoundEvents.ENDERMAN_SCREAM, 1.0F, -3.0F);
                this.enderThorn.swing(InteractionHand.MAIN_HAND);
                this.enderThorn.doHurtTarget(entity);
            }
        }



        @Override
        public void stop() {
            super.stop();
            this.enderThorn.setThornMode(false);
            this.spineAttack=0;
            this.enderThorn.throwSpine=false;
        }

        @Override
        public void tick() {
            super.tick();
            LivingEntity target = this.enderThorn.getTarget();
            if(target!=null){
                double d0 = this.enderThorn.distanceTo(target);
                if(!this.enderThorn.throwSpine){
                    if(d0 < 8 && !this.enderThorn.thornOn()){
                        this.enderThorn.setThornMode(true);
                    }else if(d0 > 10 && this.enderThorn.canThrow()){
                        this.spineAttack=12;
                        this.enderThorn.throwSpine=true;
                        this.enderThorn.level.broadcastEntityEvent(this.enderThorn,(byte) 4);
                    }
                }else {
                    this.spineAttack--;
                    this.enderThorn.getNavigation().stop();
                    this.enderThorn.lookAt(target,30.0F,30.0F);
                    this.enderThorn.setYBodyRot(this.enderThorn.getYHeadRot());
                    this.enderThorn.yBodyRot=this.enderThorn.getYHeadRot();
                    if(this.spineAttack==6){
                        this.enderThorn.throwSpine(target);
                    }
                    if(this.spineAttack==0){
                        this.enderThorn.throwSpine=false;
                        this.enderThorn.level.broadcastEntityEvent(this.enderThorn,(byte) 60);
                        this.enderThorn.throwTimer=200;
                    }
                }
            }
        }
    }

}
