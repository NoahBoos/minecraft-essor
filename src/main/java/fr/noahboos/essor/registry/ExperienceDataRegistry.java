package fr.noahboos.essor.registry;

import fr.noahboos.essor.loader.JsonLoader;

import java.util.Map;

public class ExperienceDataRegistry {
    public static String path = "data/essor/experience/";

    public static final Map<String, Float> EXPERIENCE_DATA_ARMOR;
    public static final Map<String, Float> EXPERIENCE_DATA_AXE_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_AXE_STRIPPABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_BOW_KILLABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_CROSSBOW_KILLABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_FISHING_ROD;
    public static final Map<String, Float> EXPERIENCE_DATA_FLINT_AND_STEEL;
    public static final Map<String, Float> EXPERIENCE_DATA_HOE_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_HOE_TILLABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_MACE_KILLABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_PICKAXE_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHEAR_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHEAR_CUTTABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHEAR_SHEARABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHIELD_KILLABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHOVEL_BREAKABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SHOVEL_DIGGABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_SWORD_KILLABLE;
    public static final Map<String, Float> EXPERIENCE_DATA_TRIDENT_KILLABLE;

    static {
        EXPERIENCE_DATA_ARMOR = JsonLoader.LoadExperienceData(path + "armor.json");
        EXPERIENCE_DATA_AXE_BREAKABLE = JsonLoader.LoadExperienceData(path + "axe-breakable.json");
        EXPERIENCE_DATA_AXE_STRIPPABLE = JsonLoader.LoadExperienceData(path + "axe-strippable.json");
        EXPERIENCE_DATA_BOW_KILLABLE = JsonLoader.LoadExperienceData(path + "bow-killable.json");
        EXPERIENCE_DATA_CROSSBOW_KILLABLE = JsonLoader.LoadExperienceData(path + "crossbow-killable.json");
        EXPERIENCE_DATA_FISHING_ROD = JsonLoader.LoadExperienceData(path + "fishing_rod.json");
        EXPERIENCE_DATA_FLINT_AND_STEEL = JsonLoader.LoadExperienceData(path + "flint_and_steel.json");
        EXPERIENCE_DATA_HOE_BREAKABLE = JsonLoader.LoadExperienceData(path + "hoe-breakable.json");
        EXPERIENCE_DATA_HOE_TILLABLE = JsonLoader.LoadExperienceData(path + "hoe-tillable.json");
        EXPERIENCE_DATA_MACE_KILLABLE = JsonLoader.LoadExperienceData(path + "mace-killable.json");
        EXPERIENCE_DATA_PICKAXE_BREAKABLE = JsonLoader.LoadExperienceData(path + "pickaxe-breakable.json");
        EXPERIENCE_DATA_SHEAR_BREAKABLE = JsonLoader.LoadExperienceData(path + "shear-breakable.json");
        EXPERIENCE_DATA_SHEAR_CUTTABLE = JsonLoader.LoadExperienceData(path + "shear-cuttable.json");
        EXPERIENCE_DATA_SHEAR_SHEARABLE = JsonLoader.LoadExperienceData(path + "shear-shearable.json");
        EXPERIENCE_DATA_SHIELD_KILLABLE = JsonLoader.LoadExperienceData(path + "shield-killable.json");
        EXPERIENCE_DATA_SHOVEL_BREAKABLE = JsonLoader.LoadExperienceData(path + "shovel-breakable.json");
        EXPERIENCE_DATA_SHOVEL_DIGGABLE = JsonLoader.LoadExperienceData(path + "shovel-diggable.json");
        EXPERIENCE_DATA_SWORD_KILLABLE = JsonLoader.LoadExperienceData(path + "sword-killable.json");
        EXPERIENCE_DATA_TRIDENT_KILLABLE = JsonLoader.LoadExperienceData(path + "trident-killable.json");
    }
}
