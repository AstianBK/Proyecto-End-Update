package com.As13.endtropy.client.renderers;

import com.As13.endtropy.client.entity.EnderThorn;
import com.As13.endtropy.client.layers.EnderEyeLayer;
import com.As13.endtropy.client.models.EnderThornModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EnderThornRenderer extends GeoEntityRenderer<EnderThorn> {

    public EnderThornRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager,new EnderThornModel<>());
        this.addLayer(new EnderEyeLayer<>(this,new EnderThornModel<>()));
        this.shadowRadius = 0.5f;
    }

    @Override
    public void render(EnderThorn animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public RenderType getRenderType(EnderThorn animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        animatable.refreshDimensions();
        stack.scale(1.5f, 1.5f, 1.5f);
        return super.getRenderType(animatable,partialTicks,stack,renderTypeBuffer,vertexBuilder,packedLightIn,textureLocation);
    }
}
