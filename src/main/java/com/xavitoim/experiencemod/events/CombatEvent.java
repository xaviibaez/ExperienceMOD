package com.xavitoim.experiencemod.events;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CombatEvent {
    private static final boolean activated = true;
    private static boolean messageSend;

    @SubscribeEvent
    public static void combatEvent(AttackEntityEvent event) {
        if(activated) {
            LivingEntity player = event.getPlayer();

            if(player instanceof ServerPlayerEntity){
                ServerStatisticsManager statisticsFromPlayer = ((ServerPlayerEntity) player).getStats();

                Collection collectionOfEntities =  ForgeRegistries.ENTITIES.getValues();
                List<Entity> listEntities = new ArrayList<>(collectionOfEntities);
                int creaturesKilled = 0;

                for(Entity e : listEntities){
                    if(!e.getType().getClassification().getPeacefulCreature()){
                        creaturesKilled = creaturesKilled + statisticsFromPlayer.getValue(Stats.ENTITY_KILLED.get(e.getType()));
                    }
                }

                if(creaturesKilled >= 100){
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
