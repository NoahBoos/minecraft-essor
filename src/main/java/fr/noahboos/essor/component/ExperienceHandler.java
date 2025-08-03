package fr.noahboos.essor.component;

import fr.noahboos.essor.loader.ExperienceDataRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

public class ExperienceHandler {
    static void AddExperience(EquipmentLevelingData data, float experienceToAdd) {
        data.SetCurrentExperience(data.GetCurrentExperience() + experienceToAdd);
        while (data.GetCurrentExperience() >= data.GetLevelExperienceThreshold()) {
            LevelUp(data);
        }
    }

    static void LevelUp(EquipmentLevelingData data) {
        data.SetLevel(data.GetLevel() + 1);
        data.SetCurrentExperience(data.GetCurrentExperience() - data.GetLevelExperienceThreshold());
        data.SetLevelExperienceThreshold(data.GetLevelExperienceThreshold() + 100);
    }

    public static void OnBlockBreak(ItemStack itemInHand, Block block) {
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main au moment où il a cassé le bloc.
        EquipmentLevelingData data = itemInHand.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Identifiant complet du bloc que le joueur a cassé.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();

        // Jeu de conditions if/else accueillant le code relatif aux gains d'expériences.
        if (itemInHand.getItem() instanceof AxeItem) {

        } else if (itemInHand.getItem() instanceof HoeItem) {

        } else if (itemInHand.getItem() instanceof PickaxeItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_PICKAXE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_PICKAXE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof ShovelItem) {

        }
    }
}
