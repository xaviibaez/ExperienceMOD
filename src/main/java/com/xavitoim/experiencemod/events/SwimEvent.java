package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.utils.helpers.KeyboardHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class SwimEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void athleticsEvent(LivingEvent event) {
        if (activated) {
            LivingEntity player = event.getEntityLiving();

            if (player instanceof PlayerEntity) {
                ExperienceMod.LOGGER.info("check 0");
                if (player instanceof ServerPlayerEntity) {
                    ExperienceMod.LOGGER.info("check 1");

                    ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

                    int distanceSwam = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM));

                    if (distanceSwam >= 1) {
                        ExperienceMod.LOGGER.info("check 2");
//&& KeyboardHelper.isHoldingSpace() && player.getActivePotionEffects().size() != 0
                        if (player.isInWater()) {
                            ExperienceMod.LOGGER.info("check 3   ");

                            if (!messageSend) {
                                ((ServerPlayerEntity) player).sendStatusMessage(new TranslationTextComponent("Your swim level is activated"), false);
                                ExperienceMod.LOGGER.info("Distance Swam - " + distanceSwam);
                                messageSend = true;
                            }
                            player.addPotionEffect(new
                                    EffectInstance(Effects.DOLPHINS_GRACE, 50, distanceSwam / 100000));
                        }
                    }
                }
            }
        }
    }
}
