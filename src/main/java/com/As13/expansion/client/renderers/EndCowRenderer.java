package com.As13.expansion.client.renderers;

import com.As13.expansion.Expansion;
import com.As13.expansion.client.entity.EndCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EndCowRenderer  extends MobRenderer<EndCow, CowModel<EndCow>> {
    public EndCowRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_,new CowModel<>(p_173956_.bakeLayer(ModelLayers.COW)),0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(EndCow p_114482_) {
        return new ResourceLocation(Expansion.MODID,"textures/entity/end_cow/end_cow.png");
    }
}
