package com.As13.endtropy.modbusevent;

import com.As13.endtropy.Endtropy;
import com.As13.endtropy.client.entity.*;
import com.As13.endtropy.client.models.EndSheepFurModel;
import com.As13.endtropy.client.models.EndSheepModel;
import com.As13.endtropy.register.BkEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Endtropy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvent {

    public static ModelLayerLocation FUR = new ModelLayerLocation(
            new ResourceLocation(Endtropy.MODID, "fur"), "fur");

    public static ModelLayerLocation END_SHEEP = new ModelLayerLocation(
            new ResourceLocation(Endtropy.MODID, "end_sheep"), "end_sheep");



    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void registerRulesSpawn(SpawnPlacementRegisterEvent event) {
        event.register(BkEntityTypes.END_SHEEP.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EndAnimals::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(BkEntityTypes.END_COW.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EndAnimals::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(BkEntityTypes.END_PIG.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EndAnimals::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(BkEntityTypes.END_COW.get(), EndCow.createAttributes().build());
        event.put(BkEntityTypes.END_PIG.get(), EndPig.createAttributes().build());
        event.put(BkEntityTypes.END_SHEEP.get(), EndSheep.createAttributes().build());
        event.put(BkEntityTypes.ENDERTHORN.get(), EnderThorn.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FUR, EndSheepFurModel::createFurLayer);
        event.registerLayerDefinition(END_SHEEP, EndSheepModel::createBodyLayer);
    }

}
