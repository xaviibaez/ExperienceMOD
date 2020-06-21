package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.utils.helpers.KeyboardHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraftforge.event.TickEvent;

public class SwimEventLogic {

    public static void swimEventLogic(TickEvent.PlayerTickEvent event) {
        LivingEntity player = event.player;

        if (player instanceof ServerPlayerEntity) {
            ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

            int distanceSwam = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM));

            if (player.isInWater() && KeyboardHelper.isHoldingWASD()) {
                player.addPotionEffect(new
                        EffectInstance(Effects.DOLPHINS_GRACE, 50, distanceSwam/100000));
            }
        }
    }
}
