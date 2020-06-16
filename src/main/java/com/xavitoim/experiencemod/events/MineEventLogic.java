package com.xavitoim.experiencemod.events;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MineEventLogic {

    public static void hasteEvent(BreakEvent event) {
        int blocksBreakRock = 0;
        Collection collectionOfBlocks =  ForgeRegistries.BLOCKS.getValues();
        List<Block> listBlocksBreak = new ArrayList<>(collectionOfBlocks);

        LivingEntity player = event.getPlayer();

        if(player instanceof ServerPlayerEntity){

            for(Block block : listBlocksBreak){
                if(block.getMaterial(block.getDefaultState()) == Material.ROCK){
                    blocksBreakRock = blocksBreakRock + ((ServerPlayerEntity) player).getStats().getValue(Stats.BLOCK_MINED.get(block));
                }
            }

            if(event.getState().getBlock().getMaterial(event.getState()) == Material.ROCK){
                player.addPotionEffect(new
                        EffectInstance(Effects.HASTE, 50, blocksBreakRock/100000));
            }
        }
    }
}
