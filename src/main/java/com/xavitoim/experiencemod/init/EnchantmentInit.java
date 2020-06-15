package com.xavitoim.experiencemod.init;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.enchantments.JumpEnchantment;
import com.xavitoim.experiencemod.enchantments.MineEnchantment;
import com.xavitoim.experiencemod.enchantments.RunEnchantment;
import com.xavitoim.experiencemod.enchantments.SwimEnchantment;
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

    public static final RegistryObject<Enchantment> JUMP = ENCHANTMENTS.register("jump",
            () -> new JumpEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET,
                    new EquipmentSlotType[] {EquipmentSlotType.FEET}));

    public static final RegistryObject<Enchantment> SWIM = ENCHANTMENTS.register("swim",
            () -> new SwimEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET,
                    new EquipmentSlotType[] {EquipmentSlotType.FEET}));

    /*public static final RegistryObject<Enchantment> COMBAT = ENCHANTMENTS.register("combat",
            () -> new RunEnchantment(Enchantment.Rarity.RARE, EnchantmentType.WEAPON);
*/
    public static final RegistryObject<Enchantment> MINE = ENCHANTMENTS.register("mine",
            () -> new MineEnchantment(Enchantment.Rarity.RARE, EnchantmentType.DIGGER,
                    new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}));




}
