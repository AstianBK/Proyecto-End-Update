package com.As13.endtropy.client.renderers;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.client.entity.EndSheep;
import com.As13.endtropy.client.layers.EndSheepFurLayer;
import com.As13.endtropy.client.models.EndSheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EndSheepRenderer extends MobRenderer<EndSheep, EndSheepModel<EndSheep>> {
    public EndSheepRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_,new EndSheepModel<>(p_173956_.bakeLayer(ModelLayers.SHEEP)),0.7F);
        this.addLayer(new EndSheepFurLayer(this,p_173956_.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(EndSheep p_114482_) {
        return new ResourceLocation(Endtropy.MODID,"textures/entity/end_sheep/end_sheep.png");
    }
}
