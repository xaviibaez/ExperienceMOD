package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.utils.helpers.KeyboardHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.*;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Bus.FORGE)
public class AthleticsEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void athleticsEvent(LivingJumpEvent event) {
        if(activated) {
            LivingEntity player = event.getEntityLiving();
            World world = player.getEntityWorld();

            if(player instanceof PlayerEntity){
                if(player instanceof ServerPlayerEntity){
                    ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity)player).getStats();

                    int jumpsMade = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.JUMP));
                    int distanceTraveled = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM)) +
                            statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM));
                    int distanceSwam = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM));

                    if(jumpsMade >= 300){
                        if(!messageSend){
                            ((ServerPlayerEntity) player).sendStatusMessage(new TranslationTextComponent("Your jump level is activated"), false);
                            ExperienceMod.LOGGER.info("Jumps Made - " + jumpsMade);
                        }
                        player.addPotionEffect(new
                                EffectInstance(Effects.JUMP_BOOST, 50, jumpsMade/100000));
                    }

                    //TODO crear clase separada para SPEED Effect con el evento TickEvent.PlayerTickEvent
                    if(distanceTraveled >= 5000) {
                        if (!messageSend) {
                            ((ServerPlayerEntity) player).sendStatusMessage(new TranslationTextComponent("Your run level is activated"), false);
                            ExperienceMod.LOGGER.info("Distance Traveled - " + distanceTraveled);
                            messageSend = true;
                        }
                        player.addPotionEffect(new
                                EffectInstance(Effects.SPEED, 50, distanceTraveled/100000));
                    }
                }
            }
        }
    }
}

