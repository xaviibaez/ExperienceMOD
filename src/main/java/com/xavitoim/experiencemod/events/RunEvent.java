package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RunEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void runEvent(LivingEvent event) {
        if(activated) {
            LivingEntity player = event.getEntityLiving();
            World world = player.getEntityWorld();

            if(player instanceof PlayerEntity){
                if(player instanceof ServerPlayerEntity){
                    Vec3d i = player.getMotion();
                    ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity)player).getStats();

                    int distanceTraveled = statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM)) +
                            statisticsFromPlayer.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM));

                    if(distanceTraveled >= 3000){
                        if(!messageSend){
                            world.getServer().getPlayerList().sendMessage(new TranslationTextComponent("message.thingtotranslate"));
                            messageSend = true;
                        }
                        player.addPotionEffect(new
                                EffectInstance(Effects.SPEED, Integer.MAX_VALUE, 1));
                    }

                    ExperienceMod.LOGGER.info("Test - " + i);
                }
            }
        }
    }
}