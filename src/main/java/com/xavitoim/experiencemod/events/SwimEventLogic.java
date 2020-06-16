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
    private static final boolean activated = true;

    public static void swimEventLogic(TickEvent.PlayerTickEvent event) {
        if (activated) {
            LivingEntity player = event.player;

            if (player instanceof ServerPlayerEntity) {
                ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

                int distanceSwam = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM));
                int breathUnderWater = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM));

                if (player.isInWater()) {
                    //TODO separalo en otro evento para que se pueda poner este encantamiento en HEAD en lugar de FEET
                    player.addPotionEffect(new
                            EffectInstance(Effects.WATER_BREATHING, 50, breathUnderWater/100000));
                    if(KeyboardHelper.isHoldingWASD()){
                        player.addPotionEffect(new
                                EffectInstance(Effects.DOLPHINS_GRACE, 50, distanceSwam/100000));
                    }
                }
            }
        }
    }
}
