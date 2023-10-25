package com.As13.endtropy.client.layers;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.client.entity.EnderThorn;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class EnderEyeLayer <T extends EnderThorn> extends GeoLayerRenderer<T> {
    private final AnimatedGeoModel<T> model;

    public EnderEyeLayer(IGeoRenderer entityRendererIn, AnimatedGeoModel<T> model) {
        super(entityRendererIn);
        this.model = model;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.model.getModelResource(entityLivingBaseIn);
        this.model.getTextureResource(entityLivingBaseIn);
        this.model.getAnimationResource(entityLivingBaseIn);
        this.renderCopyModel(this.model,this.getEntityTexture(entityLivingBaseIn),matrixStackIn,bufferIn,packedLightIn,entityLivingBaseIn,partialTicks,1.0f,1.0f,1.0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(T entityIn) {
        String name="";
        if(entityIn.getEncodeId()!=null){
            name=entityIn.getEncodeId().split(":")[1];
            return  new ResourceLocation(Endtropy.MODID,"textures/entity/"+name+"/"+name+"_eye.png");
        }
        return  new ResourceLocation(Endtropy.MODID,"textures/entity/enderthorn/enderthorn_eye.png");
    }

    @Override
    public RenderType getRenderType(ResourceLocation textureLocation) {
        return RenderType.eyes(textureLocation);
    }
}
