package fr.noahboos.essor.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essor.Essor;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class ModDataComponentTypes {
    // Registre différé permettant l'enregistrement des différents composants de données.
    public static final DeferredRegister<DataComponentType<?>> DR_DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Essor.MOD_ID);

    public static final Codec<EquipmentLevelingData> C_EQUIPMENT_LEVELING_DATA =
            RecordCodecBuilder.create(instance -> instance.group(
                    // Champs avec valeurs par défaut si absents (évite les resets)
                    Codec.INT.optionalFieldOf("prestige", 0).forGetter(EquipmentLevelingData::GetPrestige),
                    Codec.INT.optionalFieldOf("requiredLevelToPrestige", 25).forGetter(EquipmentLevelingData::GetRequiredLevelToPrestige),
                    Codec.INT.optionalFieldOf("level", 0).forGetter(EquipmentLevelingData::GetLevel),
                    Codec.FLOAT.optionalFieldOf("experienceMultiplier", 1f).forGetter(EquipmentLevelingData::GetExperienceMultiplier),

                    // ---- Migration ancien -> nouveau ----
                    // On LIT le nouveau champ s'il existe…
                    Codec.INT.optionalFieldOf("requiredExperienceToLevelUp")
                            .forGetter(d -> Optional.of(d.GetRequiredExperienceToLevelUp())),
                    // …et on LIT l’ancien champ si présent (lecture seule)
                    Codec.INT.optionalFieldOf("levelExperienceThreshold")
                            .forGetter(d -> Optional.empty()),
                    // -------------------------------------

                    Codec.FLOAT.optionalFieldOf("currentExperience", 0f).forGetter(EquipmentLevelingData::GetCurrentExperience)
            ).apply(instance, (prestige, reqLvlToPrestige, level, expMultiplier, reqExpNewOpt, reqExpOldOpt, currentExp) -> {
                // Java 17+: Optional#or
                int reqExp = reqExpNewOpt.or(() -> reqExpOldOpt).orElse(100);
                return new EquipmentLevelingData(prestige, reqLvlToPrestige, level, expMultiplier, reqExp, currentExp);
            }));

    // Déclaration du composant de données entreposant les données relatives au leveling d'une pièce d'équipement.
    public static final DataComponentType<EquipmentLevelingData> DC_EQUIPMENT_LEVELING_DATA =
            DataComponentType.<EquipmentLevelingData>builder().persistent(C_EQUIPMENT_LEVELING_DATA).build();

    // Enregistrement du composant de données DC_EQUIPMENT_LEVELING_DATA.
    public static final RegistryObject<DataComponentType<EquipmentLevelingData>> EQUIPMENT_LEVELING =
            DR_DATA_COMPONENT_TYPES.register("equipment_leveling", () -> DC_EQUIPMENT_LEVELING_DATA);
}
