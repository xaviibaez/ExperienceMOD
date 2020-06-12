package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.utils.helpers.KeyboardHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraftforge.event.TickEvent;

//@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RunEvent {
    //@SubscribeEvent
    public static void runEvent(TickEvent.PlayerTickEvent event) {
        runEventLogic(event);
    }

    public static void runEventLogic(TickEvent.PlayerTickEvent event) {
        LivingEntity player = event.player;

        if(player instanceof ServerPlayerEntity){
            ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity)player).getStats();

            int distanceTraveled = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM)) +
                    statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM));

            if(KeyboardHelper.isHoldingWASD()){
                player.addPotionEffect(new
                        EffectInstance(Effects.SPEED, 50, distanceTraveled/100000));
            }
        }
    }
}
