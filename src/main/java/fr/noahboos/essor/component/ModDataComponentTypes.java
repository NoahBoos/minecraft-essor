package fr.noahboos.essor.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essor.Essor;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModDataComponentTypes {
    // Registre différé permettant l'enregistrement des différents composants de données.
    public static final DeferredRegister<DataComponentType<?>> DR_DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Essor.MOD_ID);

    // Codec personnalisé permettant la (dé)sérialisation d'un objet de type EquipmentLevelingData.
    public static final Codec<EquipmentLevelingData> C_EQUIPMENT_LEVELING_DATA = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("level").forGetter(EquipmentLevelingData::GetLevel),
            Codec.INT.fieldOf("levelExperienceThreshold").forGetter(EquipmentLevelingData::GetLevelExperienceThreshold),
            Codec.FLOAT.fieldOf("currentExperience").forGetter(EquipmentLevelingData::GetCurrentExperience)
    ).apply(instance, EquipmentLevelingData::new));

    // Déclaration du composant de données entreposant les données relatives au leveling d'une pièce d'équipement.
    public static final DataComponentType<EquipmentLevelingData> DC_EQUIPMENT_LEVELING_DATA =
            DataComponentType.<EquipmentLevelingData>builder().persistent(C_EQUIPMENT_LEVELING_DATA).build();

    // Enregistrement du composant de données DC_EQUIPMENT_LEVELING_DATA.
    public static final RegistryObject<DataComponentType<EquipmentLevelingData>> EQUIPMENT_LEVELING =
            DR_DATA_COMPONENT_TYPES.register("equipment_leveling", () -> DC_EQUIPMENT_LEVELING_DATA);
}
