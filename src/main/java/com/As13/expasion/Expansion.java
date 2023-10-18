package com.As13.expasion;

import com.As13.expasion.client.renderers.EndCowRenderer;
import com.As13.expasion.client.renderers.EndPigRenderer;
import com.As13.expasion.client.renderers.EndSheepRenderer;
import com.As13.expasion.register.BKBlocks;
import com.As13.expasion.register.BkEntityTypes;
import com.As13.expasion.register.BkEffect;
import com.As13.expasion.register.BkItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(Expansion.MODID)
public class Expansion {
    public static final String MODID = "expansion";
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
