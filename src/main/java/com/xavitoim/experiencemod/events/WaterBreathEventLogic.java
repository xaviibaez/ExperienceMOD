package com.xavitoim.experiencemod.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraftforge.event.TickEvent;

public class WaterBreathEventLogic {

    public static void waterBreathEventLogic(TickEvent.PlayerTickEvent event) {
        LivingEntity player = event.player;

        if (player instanceof ServerPlayerEntity) {
            ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

            int breathUnderWater = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM));

            if (player.isInWater()) {
                player.addPotionEffect(new
                        EffectInstance(Effects.WATER_BREATHING, 50, breathUnderWater/100000));
            }
        }
    }
}
