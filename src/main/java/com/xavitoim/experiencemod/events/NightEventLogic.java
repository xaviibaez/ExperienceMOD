package com.xavitoim.experiencemod.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NightEventLogic {

    @SubscribeEvent
    public static void nightEvent(TickEvent.PlayerTickEvent event) {
            LivingEntity player = event.player;
            World world = player.getEntityWorld();

            if (player instanceof ServerPlayerEntity) {
                int nightVisionLevel = ((ServerPlayerEntity) player).getStats().getValue(Stats.CUSTOM.get(Stats.SLEEP_IN_BED));

                if (!world.isDaytime()) {
                    player.addPotionEffect(new
                            EffectInstance(Effects.NIGHT_VISION, 250, nightVisionLevel / 100000));
                }
            }

    }
}