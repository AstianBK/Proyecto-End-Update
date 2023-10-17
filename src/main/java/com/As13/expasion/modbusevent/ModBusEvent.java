package com.As13.expasion.modbusevent;

import com.As13.expasion.Expansion;
import com.As13.expasion.client.entity.EndCow;
import com.As13.expasion.client.entity.EndPig;
import com.As13.expasion.client.entity.EndSheep;
import com.As13.expasion.client.models.EndSheepFurModel;
import com.As13.expasion.client.models.EndSheepModel;
import com.As13.expasion.register.BkEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Expansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvent {

    public static ModelLayerLocation FUR = new ModelLayerLocation(
            new ResourceLocation(Expansion.MODID, "fur"), "fur");

    public static ModelLayerLocation END_SHEEP = new ModelLayerLocation(
            new ResourceLocation(Expansion.MODID, "end_sheep"), "end_sheep");



    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(BkEntityTypes.END_COW.get(), EndCow.createAttributes().build());
        event.put(BkEntityTypes.END_PIG.get(), EndPig.createAttributes().build());
        event.put(BkEntityTypes.END_SHEEP.get(), EndSheep.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FUR, EndSheepFurModel::createFurLayer);
        event.registerLayerDefinition(END_SHEEP, EndSheepModel::createBodyLayer);
    }

}
