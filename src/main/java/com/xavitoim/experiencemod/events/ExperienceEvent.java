package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.*;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Bus.FORGE)
public class ExperienceEvent {
    private static final boolean activated = true;

    @SubscribeEvent
    public static void testJumpEvent(LivingJumpEvent event) {
        if(activated) {
            LivingEntity player = event.getEntityLiving();
            World world = player.getEntityWorld();

            if(player instanceof PlayerEntity){
                ExperienceMod.LOGGER.info("Player entity check");
                if(player instanceof ServerPlayerEntity){
                    ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity)player).getStats();

                    int jumpsMade = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.JUMP));
                    //int distanceTraveled = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM)) +
                    //        statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM));

                    if(jumpsMade >= 100){
                        player.addPotionEffect(new
                                EffectInstance(Effects.JUMP_BOOST, Integer.MAX_VALUE, 1));

                        //((PlayerEntity) player).abilities.setWalkSpeed(((PlayerEntity) player).abilities.getWalkSpeed() * 2);
                    }

                    //TODO Ver Hierarchy
                    ExperienceMod.LOGGER.info("Jumps Made - " + jumpsMade);
                }
                //((PlayerEntity) player).abilities.setWalkSpeed(((PlayerEntity) player).abilities.getWalkSpeed() * 2);
            }
        }
    }
}
