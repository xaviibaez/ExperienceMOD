package com.xavitoim.experiencemod.enchantments;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.events.JumpEventLogic;
import com.xavitoim.experiencemod.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

public class JumpEnchantment extends Enchantment {

    public JumpEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
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
    public static class JumpEvent {

        @SubscribeEvent
        public static void jumpEvent(LivingEvent.LivingJumpEvent event) {
            if(!event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET).isEmpty()){
                ItemStack itemOnFeet = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET);
                Map<Enchantment, Integer> enchantmentsOnItemOnFeet = EnchantmentHelper.getEnchantments(itemOnFeet);

                if (enchantmentsOnItemOnFeet.containsKey(EnchantmentInit.JUMP.get())) {
                    JumpEventLogic.jumpEventLogic(event);
                }
            }
        }
    }
}
