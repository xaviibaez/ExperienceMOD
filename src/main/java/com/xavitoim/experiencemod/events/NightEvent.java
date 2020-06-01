package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NightEvent {
    private static final boolean activated = true;

    @SubscribeEvent
    public static void nightEvent(TickEvent.PlayerTickEvent event) {
        if (activated) {
            LivingEntity player = event.player;
            World world = player.getEntityWorld();

            if (player instanceof ServerPlayerEntity) {
                int nightVisionLevel = ((ServerPlayerEntity) player).getStats().getValue(Stats.CUSTOM.get(Stats.SLEEP_IN_BED));

                if (!world.isDaytime()) {
                    player.addPotionEffect(new
                            EffectInstance(Effects.NIGHT_VISION, 500, nightVisionLevel / 100000));
                }
                else {
                    player.addPotionEffect(new
                            EffectInstance(Effects.NIGHT_VISION, 0, nightVisionLevel / 100000));
                }
            }
        }
    }
}