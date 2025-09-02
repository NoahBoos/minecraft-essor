package fr.noahboos.essor.registry;

import fr.noahboos.essor.loader.JsonLoader;
import net.minecraft.world.item.*;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentRewardRegistry {
    public static String path = "data/essor/reward/enchantments/";

    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_HELMET;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_CHESTPLATE;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_LEGGINGS;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_BOOTS;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_AXE;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_BOW;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_CROSSBOW;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_FISHING_ROD;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_FLINT_AND_STEEL;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_HOE;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_MACE;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_PICKAXE;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SHEAR;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SHIELD;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SHOVEL;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_SWORD;
    public static final Map<Integer, Map<String, Integer>> ENCHANTMENT_REWARD_DATA_TRIDENT;

    public static Map<ArmorItem.Type, Map<Integer, Map<String, Integer>>> ENCHANTMENT_REWARD_DATA_ARMOR = new HashMap<>();
    public static Map<Class<?>, Map<Integer, Map<String, Integer>>> ENCHANTMENT_REWARD_DATA_ITEMS = new HashMap<>();

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

        ENCHANTMENT_REWARD_DATA_ARMOR.put(ArmorItem.Type.HELMET, ENCHANTMENT_REWARD_DATA_HELMET);
        ENCHANTMENT_REWARD_DATA_ARMOR.put(ArmorItem.Type.CHESTPLATE, ENCHANTMENT_REWARD_DATA_CHESTPLATE);
        ENCHANTMENT_REWARD_DATA_ARMOR.put(ArmorItem.Type.LEGGINGS, ENCHANTMENT_REWARD_DATA_LEGGINGS);
        ENCHANTMENT_REWARD_DATA_ARMOR.put(ArmorItem.Type.BOOTS, ENCHANTMENT_REWARD_DATA_BOOTS);

        ENCHANTMENT_REWARD_DATA_ITEMS.put(AxeItem.class, ENCHANTMENT_REWARD_DATA_AXE);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(BowItem.class, ENCHANTMENT_REWARD_DATA_BOW);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(CrossbowItem.class, ENCHANTMENT_REWARD_DATA_CROSSBOW);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(HoeItem.class, ENCHANTMENT_REWARD_DATA_HOE);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(MaceItem.class, ENCHANTMENT_REWARD_DATA_MACE);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(PickaxeItem.class, ENCHANTMENT_REWARD_DATA_PICKAXE);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(ShearsItem.class, ENCHANTMENT_REWARD_DATA_SHEAR);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(ShieldItem.class, ENCHANTMENT_REWARD_DATA_SHIELD);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(ShovelItem.class, ENCHANTMENT_REWARD_DATA_SHOVEL);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(SwordItem.class, ENCHANTMENT_REWARD_DATA_SWORD);
        ENCHANTMENT_REWARD_DATA_ITEMS.put(TridentItem.class, ENCHANTMENT_REWARD_DATA_TRIDENT);
    }
}
