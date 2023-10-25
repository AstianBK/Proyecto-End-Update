package com.As13.endtropy.client.models;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.client.entity.EnderThorn;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EnderThornModel<I extends Monster> extends AnimatedGeoModel<EnderThorn> {

    private static final ResourceLocation MODEL=new ResourceLocation(Endtropy.MODID,
            "geo/enderthorn.geo.json");

    protected static final ResourceLocation ANIMATION_RESLOC = new ResourceLocation(Endtropy.MODID,
            "animations/enderthorn.animation.json");
    @Override
    public ResourceLocation getModelResource(EnderThorn object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(EnderThorn object) {
        return new ResourceLocation(Endtropy.MODID,
                    "textures/entity/enderthorn/enderthorn.png");

    }

    @Override
    public ResourceLocation getAnimationResource(EnderThorn animatable) {
        return ANIMATION_RESLOC;
    }

    @Override
    public void setCustomAnimations(EnderThorn animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone thornR = this.getAnimationProcessor().getBone("thorns_right");
        IBone thornL = this.getAnimationProcessor().getBone("thorns_left");
        IBone thornBody = this.getAnimationProcessor().getBone("thorn_body");
        boolean flag = animatable.thornOn();
        thornR.setHidden(!flag);
        thornL.setHidden(!flag);
        thornBody.setHidden(!flag);

    }
}
