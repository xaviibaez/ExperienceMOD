package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Bus.FORGE)
public class ExperienceEvent {
    private static final boolean activated = true;

    @SubscribeEvent
    public static void testJumpEvent(LivingJumpEvent event) {
        if(activated) {
            LivingEntity player = event.getEntityLiving();
            World world = player.getEntityWorld();

            if(player instanceof PlayerEntity){
                ExperienceMod.LOGGER.info("Player entity check");
                if(player instanceof ServerPlayerEntity){
                    ServerStatisticsManager s = ((ServerPlayerEntity)player).getStats();
                    //I need to know how to get the jumps made on the s.getValue()

                    //s.getValue();
                    ExperienceMod.LOGGER.info("TEST - " + s);
                }
                //((PlayerEntity) player).abilities.setWalkSpeed(((PlayerEntity) player).abilities.getWalkSpeed() * 2);
            }
        }
    }
}
