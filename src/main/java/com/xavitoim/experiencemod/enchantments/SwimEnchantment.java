package com.xavitoim.experiencemod.enchantments;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.events.SwimEventLogic;
import com.xavitoim.experiencemod.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

public class SwimEnchantment extends Enchantment {

    public SwimEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
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

    @Mod.EventBusSubscriber(modid = ExperienceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class SwimEvent {

        @SubscribeEvent
        public static void swimEvent(TickEvent.PlayerTickEvent event) {
            if(!event.player.getItemStackFromSlot(EquipmentSlotType.FEET).isEmpty()){
                ItemStack itemOnFeet = event.player.getItemStackFromSlot(EquipmentSlotType.FEET);
                Map<Enchantment, Integer> enchantmentsOnItemOnFeet = EnchantmentHelper.getEnchantments(itemOnFeet);

                if (enchantmentsOnItemOnFeet.containsKey(EnchantmentInit.SWIM.get())) {
                    SwimEventLogic.swimEventLogic(event);
                }
            }

        }
    }
}
