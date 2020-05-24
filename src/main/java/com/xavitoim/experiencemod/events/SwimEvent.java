package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.utils.helpers.KeyboardHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SwimEvent {
    private static final boolean activated = true;

    @SubscribeEvent
    public static void swimEvent(TickEvent.PlayerTickEvent event) {
        if (activated) {
            LivingEntity player = event.player;

            if (player instanceof ServerPlayerEntity) {
                ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

                int distanceSwam = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM));
                int breathUnderWater = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM));

                if (player.isInWater()) {
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
