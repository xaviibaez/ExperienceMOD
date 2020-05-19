package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MineEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void hasteEvent(BreakEvent event) {
        int blocksBreakRock = 0;
        Collection collectionOfBlocks =  ForgeRegistries.BLOCKS.getValues();
        List<Block> listBlocksBreak = new ArrayList<>(collectionOfBlocks);

        if(activated) {
            LivingEntity player = event.getPlayer();

            if(player instanceof ServerPlayerEntity){
                ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

                for(Block block : listBlocksBreak){
                    if(block.getMaterial(block.getDefaultState()) == Material.ROCK){
                        blocksBreakRock = blocksBreakRock + statisticsFromPlayer.getValue(Stats.BLOCK_MINED.get(block));
                    }
                }

                if(blocksBreakRock >= 1){
                    if(!messageSend){
                        ((ServerPlayerEntity) player).sendStatusMessage(new TranslationTextComponent("Your haste level is activated"), false);
                        ExperienceMod.LOGGER.info("Blocks destroyed - " + blocksBreakRock);
                        messageSend = true;
                    }
                    player.addPotionEffect(new
                            EffectInstance(Effects.HASTE, 50, blocksBreakRock/100000));
                }
            }
        }
    }
}
