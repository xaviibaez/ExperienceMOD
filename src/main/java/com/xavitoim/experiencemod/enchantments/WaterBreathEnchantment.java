package com.xavitoim.experiencemod.enchantments;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.events.WaterBreathEventLogic;
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

public class WaterBreathEnchantment extends Enchantment {

    public WaterBreathEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
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
    public static class WaterBreathEvent {

        @SubscribeEvent
        public static void waterBreathEvent(TickEvent.PlayerTickEvent event) {
            if(!event.player.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()){
                ItemStack itemOnHead = event.player.getItemStackFromSlot(EquipmentSlotType.HEAD);
                Map<Enchantment, Integer> enchantmentsOnItemOnHead = EnchantmentHelper.getEnchantments(itemOnHead);

                if (enchantmentsOnItemOnHead.containsKey(EnchantmentInit.WATERBREATH.get())) {
                    WaterBreathEventLogic.waterBreathEventLogic(event);
                }
            }
        }
    }
}
