package fr.noahboos.essor.component;

import fr.noahboos.essor.registry.EnchantmentRewardRegistry;
import fr.noahboos.essor.utils.ExperienceUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.*;

public class ExperienceHandler {
    // Un set regroupant l'entièreté des classes correspondant à une pièce d'équipement améliorable.
    private static final Set<Class<?>> UPGRADABLE_ITEM_CLASSES  = Set.of(
            SwordItem.class, PickaxeItem.class, AxeItem.class, ShovelItem.class, HoeItem.class,
            BowItem.class, CrossbowItem.class, TridentItem.class, ShieldItem.class, FishingRodItem.class,
            FlintAndSteelItem.class, MaceItem.class, ShearsItem.class
    );

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
            ExperienceUtils.ApplyEnchantmentForLevelUp(level, rewardsTable, itemToLevelUp);
        }

        player.sendSystemMessage(Component.translatable("chat.essor.levelUpMessage", itemToLevelUp.getDisplayName(), data.GetLevel()));
    }

    static void PrestigeUp(Player player, Level level, ItemStack itemToPrestige) {
        EquipmentLevelingData data = itemToPrestige.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (data == null) return;

        data.SetPrestige(data.GetPrestige() + 1);
        data.SetLevel(data.GetLevel() - data.GetRequiredLevelToPrestige());
        data.SetExperienceMultiplier((float) Math.round((data.GetExperienceMultiplier() + 0.1f) * 100f) / 100f);
        data.SetRequiredExperienceToLevelUp(100 + (100 * data.GetLevel()));
        data.SetRequiredLevelToPrestige(25 + (25 * data.GetPrestige()));

        player.sendSystemMessage(Component.translatable("chat.essor.prestigeMessage", itemToPrestige.getDisplayName(), data.GetPrestige()));
    }
}