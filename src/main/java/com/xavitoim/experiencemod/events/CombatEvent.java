package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CombatEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void combatEvent(AttackEntityEvent event) {
        int creaturesKilled;

        if(activated) {
            LivingEntity player = event.getPlayer();

            if(player instanceof ServerPlayerEntity){
                ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();
                creaturesKilled = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.MOB_KILLS));

                if(event.getTarget().getType().getClassification() == EntityClassification.MONSTER){
                    if(!messageSend){
                        ((ServerPlayerEntity) player).sendStatusMessage(new TranslationTextComponent("Your mob killed level is activated"), false);
                        ExperienceMod.LOGGER.info("Mobs killed - " + creaturesKilled);
                        messageSend = true;
                    }
                    player.addPotionEffect(new
                            EffectInstance(Effects.STRENGTH, 50, creaturesKilled/100000));
                }
            }
        }
    }
}
