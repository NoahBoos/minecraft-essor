package fr.noahboos.essor.loader;

import java.util.Map;

public class ExperienceDataRegistry {
    public static String path = "data/essor/experience/";

    public static final Map<String, Float> EXPERIENCE_DATA_ARMOR;
    public static final Map<String, Float> EXPERIENCE_DATA_AXE_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_AXE_STRIPPABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_BOW;
    public static final Map<String, Float> EXPERIENCE_DATA_CROSSBOW;
    public static final Map<String, Float> EXPERIENCE_DATA_FISHING_ROD;
    public static final Map<String, Float> EXPERIENCE_DATA_FLINT_AND_STEEL;
    public static final Map<String, Float> EXPERIENCE_DATA_HOE_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_HOE_TILLABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_MACE;
    public static final Map<String, Float> EXPERIENCE_DATA_PICKAXE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHEAR_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHEAR_CUTTABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHEAR_SHEARABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHIELD;
    public static final Map<String, Float> EXPERIENCE_DATA_SHOVEL_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHOVEL_DIGGABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SWORD;
    public static final Map<String, Float> EXPERIENCE_DATA_TRIDENT;

    static {
        EXPERIENCE_DATA_ARMOR = JsonLoader.LoadExperienceData(path + "armor.json");
        EXPERIENCE_DATA_AXE_BREAKABLE = JsonLoader.LoadExperienceData(path + "axe-breakable.json");
        EXPERIENCE_DATA_AXE_STRIPPABLE = JsonLoader.LoadExperienceData(path + "axe-strippable.json");
        EXPERIENCE_DATA_BOW = JsonLoader.LoadExperienceData(path + "bow.json");
        EXPERIENCE_DATA_CROSSBOW = JsonLoader.LoadExperienceData(path + "crossbow.json");
        EXPERIENCE_DATA_FISHING_ROD = JsonLoader.LoadExperienceData(path + "fishing_rod.json");
        EXPERIENCE_DATA_FLINT_AND_STEEL = JsonLoader.LoadExperienceData(path + "flint_and_steel.json");
        EXPERIENCE_DATA_HOE_BREAKABLE = JsonLoader.LoadExperienceData(path + "hoe-breakable.json");
        EXPERIENCE_DATA_HOE_TILLABLE = JsonLoader.LoadExperienceData(path + "hoe-tillable.json");
        EXPERIENCE_DATA_MACE = JsonLoader.LoadExperienceData(path + "mace.json");
        EXPERIENCE_DATA_PICKAXE = JsonLoader.LoadExperienceData(path + "pickaxe.json");
        EXPERIENCE_DATA_SHEAR_BREAKABLE = JsonLoader.LoadExperienceData(path + "shear-breakable.json");
        EXPERIENCE_DATA_SHEAR_CUTTABLE = JsonLoader.LoadExperienceData(path + "shear-cuttable.json");
        EXPERIENCE_DATA_SHEAR_SHEARABLE = JsonLoader.LoadExperienceData(path + "shear-shearable.json");
        EXPERIENCE_DATA_SHIELD = JsonLoader.LoadExperienceData(path + "shield.json");
        EXPERIENCE_DATA_SHOVEL_BREAKABLE = JsonLoader.LoadExperienceData(path + "shovel-breakable.json");
        EXPERIENCE_DATA_SHOVEL_DIGGABLE = JsonLoader.LoadExperienceData(path + "shovel-diggable.json");
        EXPERIENCE_DATA_SWORD = JsonLoader.LoadExperienceData(path + "sword.json");
        EXPERIENCE_DATA_TRIDENT = JsonLoader.LoadExperienceData(path + "trident.json");
    }
}
