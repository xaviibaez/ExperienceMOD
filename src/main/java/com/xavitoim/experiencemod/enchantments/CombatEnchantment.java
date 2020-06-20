package com.xavitoim.experiencemod.enchantments;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.events.CombatEventLogic;
import com.xavitoim.experiencemod.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

public class CombatEnchantment extends Enchantment {
    public CombatEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
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
    public static class CombatEvent {

        @SubscribeEvent
        public static void combatEvent(AttackEntityEvent event) {
            if(!event.getPlayer().getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()){
                ItemStack itemOnHand = event.getPlayer().getItemStackFromSlot(EquipmentSlotType.MAINHAND);
                Map<Enchantment, Integer> enchantmentsOnItemOnHand = EnchantmentHelper.getEnchantments(itemOnHand);

                if (enchantmentsOnItemOnHand.containsKey(EnchantmentInit.COMBAT.get())) {
                    CombatEventLogic.combatEvent(event);
                }
            }
        }
    }
}
