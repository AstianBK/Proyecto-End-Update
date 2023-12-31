package com.As13.endtropy;

import com.As13.endtropy.block.client.renderer.EndAnchorBlockRenderer;
import com.As13.endtropy.client.projectile.WarpedThornProjectile;
import com.As13.endtropy.client.renderers.*;
import com.As13.endtropy.register.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(Endtropy.MODID)
public class Endtropy {
    public static final String MODID = "endtropy";
    public static boolean respawnAfterCredits=false;
    public Endtropy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();
        BkEntityTypes.register(modEventBus);
        BkBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        BkEffect.EFFECT.register(modEventBus);
        BkItems.ITEMS.register(modEventBus);
        BKBlocks.register(modEventBus);
        BKEnchantment.REGISTRY.register(modEventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->{
            modEventBus.addListener(this::registerRender);
            modEventBus.addListener(this::registerRendererBlockEntity);
        });

        MinecraftForge.EVENT_BUS.register(this);
    }
    @OnlyIn(Dist.CLIENT)
    public  void registerRendererBlockEntity(FMLCommonSetupEvent event){
        BlockEntityRenderers.register(BkBlockEntityTypes.END_ANCHOR.get(), EndAnchorBlockRenderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    private void registerRender(FMLCommonSetupEvent event){
        EntityRenderers.register(BkEntityTypes.END_COW.get(), EndCowRenderer::new);
        EntityRenderers.register(BkEntityTypes.END_PIG.get(), EndPigRenderer::new);
        EntityRenderers.register(BkEntityTypes.END_SHEEP.get(), EndSheepRenderer::new);
        EntityRenderers.register(BkEntityTypes.ENDERTHORN.get(), EnderThornRenderer::new);
        EntityRenderers.register(BkEntityTypes.WARPED_THORN.get(), WarpedThornRenderer::new);
    }

}
