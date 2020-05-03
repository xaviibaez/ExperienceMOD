package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HasteEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void hasteEvent(LivingDestroyBlockEvent event) {
        if(activated) {
            LivingEntity player = event.getEntityLiving();
            World world = player.getEntityWorld();

            ExperienceMod.LOGGER.info("player haste" + player.getClass());


            if(player instanceof PlayerEntity){
                if(player instanceof ServerPlayerEntity){
                    ExperienceMod.LOGGER.info("check");

                    ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity)player).getStats();

                    int blocksBreak = statisticsFromPlayer.getValue(Stats.BLOCK_MINED.get(Block.getStateById(173).getBlock()));

                    ExperienceMod.LOGGER.info("Blocks destroyed - " + blocksBreak);
                }
            }
        }
    }
}
