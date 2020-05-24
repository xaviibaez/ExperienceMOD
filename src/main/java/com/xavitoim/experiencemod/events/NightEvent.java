package com.xavitoim.experiencemod.events;

import com.google.common.collect.Iterators;
import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

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
                    if(!player.getActivePotionEffects().contains(Effects.NIGHT_VISION)){
                        player.addPotionEffect(new
                                EffectInstance(Effects.NIGHT_VISION, Integer.MAX_VALUE, nightVisionLevel / 100000));
                    }
                }
                else{
                    ExperienceMod.LOGGER.info("check 0 - " + player.getActivePotionEffects().size());
                    player.getActivePotionEffects().remove(Effects.NIGHT_VISION);


                    Iterator<EffectInstance> iterator = player.activePotionsMap.values().iterator();

                    //TODO hacer esta vaina
                    while(iterator.hasNext()) {
                        EffectInstance effect = iterator.next();
                        if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent(player, effect))) continue;
                        ((ServerPlayerEntity) player).onFinishedPotionEffect(effect);
                        iterator.remove();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void nightEvent(PotionEvent.PotionRemoveEvent event) {


    }
}