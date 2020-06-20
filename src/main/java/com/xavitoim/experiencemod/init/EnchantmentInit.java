package com.xavitoim.experiencemod.init;

import com.xavitoim.experiencemod.ExperienceMod;
import com.xavitoim.experiencemod.enchantments.*;
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

    //TODO testear
    public static final RegistryObject<Enchantment> NIGHT = ENCHANTMENTS.register("night",
            () -> new NightEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_HEAD,
                    new EquipmentSlotType[] {EquipmentSlotType.HEAD}));

    public static final RegistryObject<Enchantment> COMBAT = ENCHANTMENTS.register("combat",
            () -> new CombatEnchantment(Enchantment.Rarity.RARE, EnchantmentType.WEAPON,
                    new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}));

    //TODO testear
    public static final RegistryObject<Enchantment> MINE = ENCHANTMENTS.register("mine",
            () -> new MineEnchantment(Enchantment.Rarity.RARE, EnchantmentType.DIGGER,
                    new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}));




}
