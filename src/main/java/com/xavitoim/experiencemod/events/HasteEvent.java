package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HasteEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void hasteEvent(BreakEvent event) {
        if(activated) {

            LivingEntity player = event.getPlayer();
            World world = player.getEntityWorld();

            if(player instanceof ServerPlayerEntity){
                ExperienceMod.LOGGER.info("check");

                ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();



                //TODO implemnet more ores
                int cobbleBreak = statisticsFromPlayer.getValue(Stats.BLOCK_MINED.get(Blocks.COBBLESTONE));
                int bedrockBreak = statisticsFromPlayer.getValue(Stats.BLOCK_MINED.get(Blocks.BEDROCK));
                int coalBreak = statisticsFromPlayer.getValue(Stats.BLOCK_MINED.get(Blocks.COAL_ORE));

                int blocksBreak = cobbleBreak + bedrockBreak + coalBreak;

                //TODO intentar buscar la lista ya hecha de blocks (Blocks.java)
                /*
                List<Block> listBlocksBreak = new ArrayList<>();
                int blocksBreak = 0;

                for(Block b : listBlocksBreak){
                    if(b.getMaterial(b.getDefaultState()) == Material.ROCK){
                        blocksBreak = blocksBreak + statisticsFromPlayer.getValue(Stats.BLOCK_MINED.get(b));
                    }
                }
                */

                //TODO ajustar numeros
                if(blocksBreak >= 5){
                    if(!messageSend){
                        ((ServerPlayerEntity) player).sendStatusMessage(new TranslationTextComponent("Your haste level is activated"), false);
                        ExperienceMod.LOGGER.info("Blocks destroyed - " + blocksBreak);
                        messageSend = true;
                    }
                    player.addPotionEffect(new
                            EffectInstance(Effects.HASTE, 50, blocksBreak/100000));
                }
            }
        }
    }
}
