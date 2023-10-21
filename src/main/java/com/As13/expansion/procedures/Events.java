package com.As13.expansion.procedures;

import com.As13.expasion.register.BkEffect;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent
    public static void onTeleportEnderPearl(EntityTeleportEvent.EnderPearl event){
        if(event.getPlayer().hasEffect(BkEffect.PURPLE_COAT.get())){
            event.setAttackDamage(0.0F);
        }
    }
}
