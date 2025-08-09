package fr.noahboos.essor.utils;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

import static fr.noahboos.essor.component.ExperienceHandler.AddExperience;

public class ExperienceUtils {
    public static void VerifyAndApplyExperience(Level level, Map<Class<?>, Map<String, Float>> experienceRegistriesMap, ItemStack itemToExperience, String registryKey) {
        EquipmentLevelingData itemToExperienceData = itemToExperience.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);

        for (Map.Entry<Class<?>, Map<String, Float>> entry : experienceRegistriesMap.entrySet()) {
            if (entry.getKey().isInstance(itemToExperience.getItem())) {
                Map<String, Float> experienceRegistry = entry.getValue();
                if (experienceRegistry.containsKey(registryKey)) {
                    Float experienceToAdd =  experienceRegistry.get(registryKey);
                    AddExperience(level, itemToExperienceData, experienceToAdd, itemToExperience);
                }
                break;
            }
        }
    }
}
