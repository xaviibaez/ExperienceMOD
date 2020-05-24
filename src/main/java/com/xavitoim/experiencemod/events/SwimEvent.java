package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SwimEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void swimEvent(TickEvent.PlayerTickEvent event) {
        if (activated) {
            LivingEntity player = event.player;

            if (player instanceof ServerPlayerEntity) {
                ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

                int distanceSwam = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM));

                if (player.isInWater()) {

                    if (!messageSend) {
                        ((ServerPlayerEntity) player).sendStatusMessage(new TranslationTextComponent("Your swim level is activated"), false);
                        ExperienceMod.LOGGER.info("Distance Swam - " + distanceSwam);
                        messageSend = true;
                    }
                    player.addPotionEffect(new
                            EffectInstance(Effects.DOLPHINS_GRACE, 50, distanceSwam/100000));
                }
            }
        }
    }
}
