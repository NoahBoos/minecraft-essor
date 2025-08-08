package fr.noahboos.essor.registry;

import fr.noahboos.essor.loader.JsonLoader;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentRewardDataRegistry {
    public static String path = "data/essor/reward/enchantments";

    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_HELMET;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_CHESTPLATE;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_LEGGINGS;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_BOOTS;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_AXE;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_BOW;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_CROSSBOW;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_FISHING_ROD;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_FLINT_AND_STEEL;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_HOE;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_MACE;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_PICKAXE;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SHEAR;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SHIELD;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SHOVEL;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SWORD;
    public static final Map<String, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_TRIDENT;

    static {
        ENCHANTMENT_REWARD_DATA_HELMET = JsonLoader.LoadRewardData(path + "helmet.json");
        ENCHANTMENT_REWARD_DATA_CHESTPLATE = JsonLoader.LoadRewardData(path + "chestplate.json");
        ENCHANTMENT_REWARD_DATA_LEGGINGS = JsonLoader.LoadRewardData(path + "leggings.json");
        ENCHANTMENT_REWARD_DATA_BOOTS = JsonLoader.LoadRewardData(path + "boots.json");
        ENCHANTMENT_REWARD_DATA_AXE = JsonLoader.LoadRewardData(path + "axe.json");
        ENCHANTMENT_REWARD_DATA_BOW = JsonLoader.LoadRewardData(path + "bow.json");
        ENCHANTMENT_REWARD_DATA_CROSSBOW = JsonLoader.LoadRewardData(path + "crossbow.json");
        ENCHANTMENT_REWARD_DATA_FISHING_ROD = JsonLoader.LoadRewardData(path + "fishing_rod.json");
        ENCHANTMENT_REWARD_DATA_FLINT_AND_STEEL = JsonLoader.LoadRewardData(path + "flint_and_steel.json");
        ENCHANTMENT_REWARD_DATA_HOE = JsonLoader.LoadRewardData(path + "hoe.json");
        ENCHANTMENT_REWARD_DATA_MACE = JsonLoader.LoadRewardData(path + "mace.json");
        ENCHANTMENT_REWARD_DATA_PICKAXE = JsonLoader.LoadRewardData(path + "pickaxe.json");
        ENCHANTMENT_REWARD_DATA_SHEAR = JsonLoader.LoadRewardData(path + "shear.json");
        ENCHANTMENT_REWARD_DATA_SHIELD = JsonLoader.LoadRewardData(path + "shield.json");
        ENCHANTMENT_REWARD_DATA_SHOVEL = JsonLoader.LoadRewardData(path + "shovel.json");
        ENCHANTMENT_REWARD_DATA_SWORD = JsonLoader.LoadRewardData(path + "sword.json");
        ENCHANTMENT_REWARD_DATA_TRIDENT = JsonLoader.LoadRewardData(path + "trident.json");
    }
}
