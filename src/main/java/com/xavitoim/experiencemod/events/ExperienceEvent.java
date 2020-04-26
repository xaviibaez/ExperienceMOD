package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
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
            ExperienceMod.LOGGER.info("testJumpEvent fired");
            LivingEntity livingEntity = event.getEntityLiving();
            World world = livingEntity.getEntityWorld();

            if(!world.isRemote){
                ClientPlayerEntity player = null;
                StatisticsManager s = player.getStats();
                ExperienceMod.LOGGER.info(s);
            }
        }
    }
}
