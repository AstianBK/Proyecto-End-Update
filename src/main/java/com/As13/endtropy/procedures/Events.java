package com.As13.endtropy.procedures;

import com.As13.endtropy.register.BKBlocks;
import com.As13.endtropy.register.BkEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent
    public static void onTeleportEnderPearl(EntityTeleportEvent.EnderPearl event){
        if(event.getPlayer().hasEffect(BkEffect.PURPLE_COAT.get())){
            event.setAttackDamage(0.0F);
        }
    }
}
