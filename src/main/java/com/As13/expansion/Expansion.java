package com.As13.expansion;

import com.As13.expansion.client.renderers.EndCowRenderer;
import com.As13.expansion.client.renderers.EndPigRenderer;
import com.As13.expansion.client.renderers.EndSheepRenderer;
import com.As13.expansion.register.BKBlocks;
import com.As13.expansion.register.BkEffect;
import com.As13.expansion.register.BkEntityTypes;
import com.As13.expansion.register.BkItems;
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

@Mod(Expansion.MODID)
public class Expansion {
    public static final String MODID = "expansion";
    public static boolean respawnAfterCredits=false;
    public Expansion() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();
        BkEntityTypes.register(modEventBus);
        BkEffect.EFFECT.register(modEventBus);
        BkItems.ITEMS.register(modEventBus);
        BKBlocks.register(modEventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->{
            modEventBus.addListener(this::registerRender);
        });

        MinecraftForge.EVENT_BUS.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    private void registerRender(FMLCommonSetupEvent event){
        EntityRenderers.register(BkEntityTypes.END_COW.get(), EndCowRenderer::new);
        EntityRenderers.register(BkEntityTypes.END_PIG.get(), EndPigRenderer::new);
        EntityRenderers.register(BkEntityTypes.END_SHEEP.get(), EndSheepRenderer::new);
    }

}
