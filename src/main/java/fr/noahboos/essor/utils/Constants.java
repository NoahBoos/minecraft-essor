package fr.noahboos.essor.utils;

import fr.noahboos.essor.registry.ExperienceDataRegistry;
import net.minecraft.world.item.*;

import java.util.Map;
import java.util.Set;

public class Constants {
    // Un set regroupant l'entièreté des classes correspondant à une pièce d'équipement améliorable.
    public static final Set<Class<?>> UPGRADABLE_ITEM_CLASSES  = Set.of(
            SwordItem.class, PickaxeItem.class, AxeItem.class, ShovelItem.class, HoeItem.class,
            ArmorItem.class, BowItem.class, CrossbowItem.class, TridentItem.class, ShieldItem.class,
            FishingRodItem.class, FlintAndSteelItem.class, MaceItem.class, ShearsItem.class
    );

    // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
    public static final Map<Class<?>, Map<String, Float>> TOOL_PRIMARY_EXPERIENCE_REGISTRIES_MAP = Map.of(
        AxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_AXE_BREAKABLE,
        HoeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_HOE_BREAKABLE,
        PickaxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_PICKAXE_BREAKABLE,
        ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_BREAKABLE,
        ShovelItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_BREAKABLE
    );

    // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
    public static final Map<Class<?>, Map<String, Float>> TOOL_SECONDARY_EXPERIENCE_REGISTRIES_MAP = Map.of(
        AxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_AXE_STRIPPABLE,
        HoeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_HOE_TILLABLE,
        ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_CUTTABLE,
        ShovelItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_DIGGABLE
    );

    // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
    public static final Map<Class<?>, Map<String, Float>> TOOL_TERTIARY_EXPERIENCE_REGISTRIES_MAP = Map.of(
        ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_SHEARABLE
    );

    // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
    public static final Map<Class<?>, Map<String, Float>> WEAPON_PRIMARY_EXPERIENCE_REGISTRIES_MAP = Map.of(
        BowItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_BOW_KILLABLE,
        CrossbowItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_CROSSBOW_KILLABLE,
        MaceItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_MACE_KILLABLE,
        SwordItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SWORD_KILLABLE,
        TridentItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_TRIDENT_KILLABLE,
        ShieldItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHIELD_KILLABLE
    );
}
