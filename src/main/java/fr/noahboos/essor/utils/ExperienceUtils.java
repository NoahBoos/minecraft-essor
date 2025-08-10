package fr.noahboos.essor.utils;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import fr.noahboos.essor.registry.EnchantmentRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.joml.Random;

import java.util.*;
import java.util.stream.Collectors;

import static fr.noahboos.essor.component.ExperienceHandler.AddExperience;

public class ExperienceUtils {
    public static void VerifyAndApplyExperience(Level level, Map<Class<?>, Map<String, Float>> experienceRegistriesMap, ItemStack itemToExperience, String registryKey) {
        EquipmentLevelingData itemToExperienceData = itemToExperience.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);

        for (Map.Entry<Class<?>, Map<String, Float>> entry : experienceRegistriesMap.entrySet()) {
            if (entry.getKey().isInstance(itemToExperience.getItem())) {
                Map<String, Float> experienceRegistry = entry.getValue();
                if (experienceRegistry.containsKey(registryKey)) {
                    Float experienceToAdd =  experienceRegistry.get(registryKey);
                    AddExperience(level, itemToExperience, experienceToAdd);
                }
                break;
            }
        }
    }

    public static void ApplyEnchantmentForLevelUp(Level level, Map<Integer, Map<String, Integer>> rewardsTable, ItemStack itemToEnchant) {
        // Récupère le jeu de récompenses possibles pour le niveau actuel.
        Map<String, Integer> reward = rewardsTable.get(itemToEnchant.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA).GetLevel());
        if (reward == null || reward.isEmpty()) {
            return;
        }
        // Converti les clés de récompense en Holder<Enchantment>.
        Map<Holder<Enchantment>, Integer> rewardEnchantments = reward.entrySet().stream().collect(Collectors.toMap(
                e -> EnchantmentRegistry.GetEnchantmentByID(e.getKey(), level.registryAccess()),
                Map.Entry::getValue
        ));
        // Cherche si itemToEnchant possède déjà un des enchantements du jeu de récompenses possibles.
        for (Map.Entry<Holder<Enchantment>, Integer> entry : rewardEnchantments.entrySet()) {
            Holder<Enchantment> enchantment = entry.getKey();
            // Si une correspondance est repérée, alors on améliore l'enchantement d'un niveau.
            if (itemToEnchant.getEnchantments().keySet().contains(enchantment)) {
                int newLevel = itemToEnchant.getEnchantments().getLevel(enchantment) + 1;
                itemToEnchant.enchant(enchantment, newLevel);
                return;
            }
        }
        // Si aucun enchantement du jeu de récompenses possibles n'est présent sur itemToEnchant, alors choisit un enchantement aléatoire parmi ceux présents dans le jeu de récompenses possibles.
        // L'enchantement choisi est alors appliqué au niveau mentionné dans rewardEnchantments.
        Holder<Enchantment> randomEnchantment = rewardEnchantments.keySet().stream().skip(new Random().nextInt(rewardEnchantments.size())).findFirst().orElse(null);
        if (randomEnchantment != null) {
            int enchantLevel = rewardEnchantments.get(randomEnchantment);
            itemToEnchant.enchant(randomEnchantment, enchantLevel);
        }
    }
}
