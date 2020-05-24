package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Bus.FORGE)
public class JumpEvent {
    private static final boolean activated = true;

    @SubscribeEvent
    public static void jumpEvent(LivingJumpEvent event) {
        if(activated) {
            LivingEntity player = event.getEntityLiving();

            if(player instanceof PlayerEntity){
                if(player instanceof ServerPlayerEntity){
                    int jumpsMade = ((ServerPlayerEntity)player).getStats().getValue(Stats.CUSTOM.get(Stats.JUMP));

                    player.addPotionEffect(new
                            EffectInstance(Effects.JUMP_BOOST, 50, jumpsMade/100000));
                }
            }
        }
    }
}

