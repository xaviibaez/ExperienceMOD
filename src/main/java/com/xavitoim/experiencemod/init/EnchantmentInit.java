package com.xavitoim.experiencemod.init;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.enchantments.RunEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<>(
            ForgeRegistries.ENCHANTMENTS, ExperienceMod.MOD_ID);

    public static final RegistryObject<Enchantment> RUN = ENCHANTMENTS.register("run",
            () -> new RunEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET,
                    new EquipmentSlotType[] {EquipmentSlotType.FEET}));
}
