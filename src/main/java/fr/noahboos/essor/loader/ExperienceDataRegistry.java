package fr.noahboos.essor.loader;

import java.util.Map;

public class ExperienceDataRegistry {
    public static String path = "data/essor/experience/";

    public static final Map<String, Float> EXPERIENCE_DATA_ARMOR;
    public static final Map<String, Float> EXPERIENCE_DATA_AXE;
    public static final Map<String, Float> EXPERIENCE_DATA_BOW;
    public static final Map<String, Float> EXPERIENCE_DATA_CROSSBOW;
    public static final Map<String, Float> EXPERIENCE_DATA_FISHING_ROD;
    public static final Map<String, Float> EXPERIENCE_DATA_FLINT_AND_STEEL;
    public static final Map<String, Float> EXPERIENCE_DATA_HOE;
    public static final Map<String, Float> EXPERIENCE_DATA_MACE;
    public static final Map<String, Float> EXPERIENCE_DATA_PICKAXE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHEAR;
    public static final Map<String, Float> EXPERIENCE_DATA_SHIELD;
    public static final Map<String, Float> EXPERIENCE_DATA_SHOVEL;
    public static final Map<String, Float> EXPERIENCE_DATA_SWORD;
    public static final Map<String, Float> EXPERIENCE_DATA_TRIDENT;

    static {
        EXPERIENCE_DATA_ARMOR = JsonLoader.LoadExperienceData(path + "armor.json");
        EXPERIENCE_DATA_AXE = JsonLoader.LoadExperienceData(path + "axe.json");
        EXPERIENCE_DATA_BOW = JsonLoader.LoadExperienceData(path + "bow.json");
        EXPERIENCE_DATA_CROSSBOW = JsonLoader.LoadExperienceData(path + "crossbow.json");
        EXPERIENCE_DATA_FISHING_ROD = JsonLoader.LoadExperienceData(path + "fishing_rod.json");
        EXPERIENCE_DATA_FLINT_AND_STEEL = JsonLoader.LoadExperienceData(path + "flint_and_steel.json");
        EXPERIENCE_DATA_HOE = JsonLoader.LoadExperienceData(path + "hoe.json");
        EXPERIENCE_DATA_MACE = JsonLoader.LoadExperienceData(path + "mace.json");
        EXPERIENCE_DATA_PICKAXE = JsonLoader.LoadExperienceData(path + "pickaxe.json");
        EXPERIENCE_DATA_SHEAR = JsonLoader.LoadExperienceData(path + "shear.json");
        EXPERIENCE_DATA_SHIELD = JsonLoader.LoadExperienceData(path + "shield.json");
        EXPERIENCE_DATA_SHOVEL = JsonLoader.LoadExperienceData(path + "shovel.json");
        EXPERIENCE_DATA_SWORD = JsonLoader.LoadExperienceData(path + "sword.json");
        EXPERIENCE_DATA_TRIDENT = JsonLoader.LoadExperienceData(path + "trident.json");
    }
}
