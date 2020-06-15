package com.xavitoim.experiencemod.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class JumpEventLogic {

    public static void jumpEventLogic(LivingJumpEvent event) {
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

