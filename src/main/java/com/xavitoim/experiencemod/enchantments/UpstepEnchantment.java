package com.xavitoim.experiencemod.enchantments;

import com.xavitoim.experiencemod.ExperienceMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class UpstepEnchantment extends Enchantment {

    public UpstepEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    //TODO check if this works
    @Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class RunEvent {

        @SubscribeEvent
        public static void runEvent(TickEvent.PlayerTickEvent event) {
            com.xavitoim.experiencemod.events.RunEvent.runEventSpeed(event);
        }
    }
}
