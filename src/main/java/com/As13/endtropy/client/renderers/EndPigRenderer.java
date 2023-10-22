package com.As13.endtropy.client.renderers;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.client.entity.EndPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;

public class EndPigRenderer extends MobRenderer<EndPig, PigModel<EndPig>> {
    public EndPigRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_,new PigModel<>(p_173956_.bakeLayer(ModelLayers.PIG)),0.7F);
        this.addLayer(new SaddleLayer<>(this, new PigModel<>(p_173956_.bakeLayer(ModelLayers.PIG_SADDLE)), new ResourceLocation("textures/entity/pig/pig_saddle.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(EndPig p_114482_) {
        return new ResourceLocation(Endtropy.MODID,"textures/entity/end_pig/end_pig.png");
    }
}
