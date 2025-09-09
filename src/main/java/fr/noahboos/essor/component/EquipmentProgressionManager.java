package fr.noahboos.essor.component;

import fr.noahboos.essor.registry.EnchantmentRegistry;
import fr.noahboos.essor.registry.EnchantmentRewardRegistry;
import fr.noahboos.essor.utils.InventoryUtils;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

import java.util.*;
import java.util.stream.Collectors;

import static fr.noahboos.essor.utils.Constants.UPGRADABLE_ITEM_CLASSES;

public class EquipmentProgressionManager {
    public static void AddExperience(Player player, Level level, ItemStack itemToExperience,  float experienceToAdd) {
        // Récupération du conteneur de données attaché à l'item à améliorer.
        EquipmentLevelingData data = itemToExperience.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (data == null) return;
        // Code relatif au gain d'expérience.
        data.SetCurrentExperience((float) Math.round((data.GetCurrentExperience() + (experienceToAdd * data.GetExperienceMultiplier())) * 1000f) / 1000f);
        while (data.GetCurrentExperience() >= data.GetRequiredExperienceToLevelUp()) {
            LevelUp(player, level, itemToExperience);
        }
        while (data.GetLevel() >= data.GetRequiredLevelToPrestige() && data.GetPrestige() < 10) {
            PrestigeUp(player, level, itemToExperience);
        }
        ActionBar.DisplayXPCount((ServerPlayer) player, itemToExperience);
        InventoryUtils.InventorySync((ServerPlayer) player);
    }

    static void LevelUp(Player player, Level level, ItemStack itemToLevelUp) {
        // Récupération du conteneur de données attaché à l'item à améliorer.
        EquipmentLevelingData data = itemToLevelUp.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (data == null) return;
        // Incrémente le niveau et ajuste les points d'expériences et le seuil d'expérience en conséquence.
        data.SetLevel(data.GetLevel() + 1);
        data.SetCurrentExperience(data.GetCurrentExperience() - data.GetRequiredExperienceToLevelUp());
        data.SetRequiredExperienceToLevelUp(100 + (100 * data.GetLevel()));
        // Répare complètement l'item en réinitialisant la quantité de durabilité manquante.
        itemToLevelUp.setDamageValue(0);
        // Map contenant la table de récompenses suivant le format Map<Niveau, Map<ID_Enchantement, Niveau_Enchantement>>.
        Map<Integer, Map<String, Integer>> rewardsTable = null;
        // Batterie if/else permettant de réaffecter rewardsTable.
        if (itemToLevelUp.getItem() instanceof ArmorItem) {
            rewardsTable = EnchantmentRewardRegistry.ENCHANTMENT_REWARD_DATA_ARMOR.get(((ArmorItem) itemToLevelUp.getItem()).getType());
        } else {
            for (Class<?> type : UPGRADABLE_ITEM_CLASSES) {
                if (itemToLevelUp.getItem().getClass().equals(type)) {
                    rewardsTable = EnchantmentRewardRegistry.ENCHANTMENT_REWARD_DATA_ITEMS.get(type);
                    break;
                }
            }
        }
        // Si une table existe et si elle contient des informations concernant une récompense pour le nouveau niveau de la pièce d'équipement, alors on applique l'enchantement.
        if (rewardsTable != null && rewardsTable.containsKey(data.GetLevel())) {
            ApplyEnchantmentForLevelUp(level, rewardsTable, itemToLevelUp);
        }

        player.sendSystemMessage(Component.translatable("chat.essor.levelUpMessage", itemToLevelUp.getDisplayName(), data.GetLevel()));
    }

    static void PrestigeUp(Player player, Level level, ItemStack itemToPrestige) {
        EquipmentLevelingData data = itemToPrestige.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (data == null) return;

        data.SetPrestige(data.GetPrestige() + 1);
        data.SetLevel(data.GetLevel() - data.GetRequiredLevelToPrestige());
        data.SetExperienceMultiplier((float) Math.round((data.GetExperienceMultiplier() + 0.25f) * 100f) / 100f);
        data.SetRequiredExperienceToLevelUp(100 + (100 * data.GetLevel()));
        data.SetRequiredLevelToPrestige(10 + (10 * data.GetPrestige()));

        player.sendSystemMessage(Component.translatable("chat.essor.prestigeMessage", itemToPrestige.getDisplayName(), data.GetPrestige()));
    }

    public static void VerifyAndApplyExperience(Player player, Level level, Map<Class<?>, Map<String, Float>> experienceRegistriesMap, ItemStack itemToExperience, String registryKey, Integer preMultiplier) {
        for (Map.Entry<Class<?>, Map<String, Float>> entry : experienceRegistriesMap.entrySet()) {
            if (entry.getKey().isInstance(itemToExperience.getItem())) {
                Map<String, Float> experienceRegistry = entry.getValue();
                if (experienceRegistry.containsKey(registryKey)) {
                    float experienceToAdd =  experienceRegistry.get(registryKey) * preMultiplier;
                    AddExperience(player, level, itemToExperience, experienceToAdd);
                }
                break;
            }
        }
    }

    public static void VerifyAndApplyChallengeProgress(ItemStack itemToProgress, String challengeTarget) {
        EquipmentLevelingData data = itemToProgress.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (data == null) return;

        data.GetChallenges().challenges.forEach(challenge -> {
            if (challenge.targets.isEmpty()) {
                challenge.IncrementProgress(1, data);
            }
            if (challenge.targets.contains(challengeTarget)) {
                challenge.IncrementProgress(1, data);
            }
        });
    }

    public static void VerifyAndApplyChallengeProgressForSecondaryInteraction(ItemStack itemToProgress, String challengeTarget) {
        EquipmentLevelingData data =  itemToProgress.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (data == null) return;

        if (itemToProgress.getItem().getClass() == HoeItem.class) {
            data.GetChallenges().challenges.forEach(challenge -> {
                if (challenge.targets.contains(challengeTarget)) {
                    challenge.IncrementProgress(1, data);
                }
            });
        }
    }

    public static void ApplyEnchantmentForLevelUp(Level level, Map<Integer, Map<String, Integer>> rewardsTable, ItemStack itemToEnchant) {
        // Récupération du conteneur de données attaché à l'item à enchanter.
        EquipmentLevelingData data = itemToEnchant.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (data == null) return;
        // Récupère le jeu de récompenses possibles pour le niveau actuel.
        Map<String, Integer> reward = rewardsTable.get(data.GetLevel());
        if (reward == null || reward.isEmpty()) return;
        // Converti les clés de récompense en Holder<Enchantment>.
        Map<Holder<Enchantment>, Integer> rewardEnchantments = reward.entrySet().stream().collect(Collectors.toMap(
                e -> EnchantmentRegistry.GetEnchantmentByID(e.getKey(), level.registryAccess()),
                Map.Entry::getValue
        ));
        // Cherche si itemToEnchant possède déjà un des enchantements du jeu de récompenses possibles.
        for (Map.Entry<Holder<Enchantment>, Integer> entry : rewardEnchantments.entrySet()) {
            Holder<Enchantment> enchantment = entry.getKey();
            // Si une correspondance est repérée, alors on améliore l'enchantement d'un niveau, à condition que l'amélioration ne soit pas encore obtenu.
            if (itemToEnchant.getEnchantments().keySet().contains(enchantment)) {
                if (itemToEnchant.getEnchantments().getLevel(enchantment) < entry.getValue()) {
                    itemToEnchant.enchant(enchantment, entry.getValue());
                }
                return;
            }
        }
        // Si aucun enchantement du jeu de récompenses possibles n'est présent sur itemToEnchant, alors choisit un enchantement aléatoire parmi ceux présents dans le jeu de récompenses possibles.
        // L'enchantement choisi est alors appliqué au niveau mentionné dans rewardEnchantments.
        Holder<Enchantment> randomEnchantment = rewardEnchantments.keySet().stream().skip(new org.joml.Random().nextInt(rewardEnchantments.size())).findFirst().orElse(null);
        if (randomEnchantment != null) {
            int enchantLevel = rewardEnchantments.get(randomEnchantment);
            itemToEnchant.enchant(randomEnchantment, enchantLevel);
        }
    }

}