package fr.noahboos.essor.component;

import fr.noahboos.essor.registry.EnchantmentRewardDataRegistry;
import fr.noahboos.essor.registry.ExperienceDataRegistry;
import fr.noahboos.essor.utils.ExperienceUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.tuple.Pair;

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
        data.SetCurrentExperience(data.GetCurrentExperience() + (experienceToAdd * data.GetExperienceMultiplier()));
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
            rewardsTable = EnchantmentRewardDataRegistry.ENCHANTMENT_REWARD_DATA_ARMOR.get(((ArmorItem) itemToLevelUp.getItem()).getType());
        } else {
            for (Class<?> type : UPGRADABLE_ITEM_CLASSES) {
                if (itemToLevelUp.getItem().getClass().equals(type)) {
                    rewardsTable = EnchantmentRewardDataRegistry.ENCHANTMENT_REWARD_DATA_ITEMS.get(type);
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

    public static void OnBlockBreak(Player player, Level level, ItemStack itemInHand, Block block, Integer totalDropCount) {
        // Identifiant complet du bloc que le joueur a cassé.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();
        // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
        Map<Class<?>, Map<String, Float>> experienceRegistriesMap = Map.of(
                AxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_AXE_BREAKABLE,
                HoeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_HOE_BREAKABLE,
                PickaxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_PICKAXE_BREAKABLE,
                ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_BREAKABLE,
                ShovelItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_BREAKABLE
        );
        // Vérification et attribution à l'outil de l'expérience à obtenir d'un bloc.
        ExperienceUtils.VerifyAndApplyExperience(player, level, experienceRegistriesMap, itemInHand, blockId, totalDropCount);
    }

    public static void OnRightClickBlock(Player player, Level level, ItemStack itemInHand, Block block) {
        // Identifiant complet du bloc sur lequel le joueur vient de cliquer.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();
        // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
        Map<Class<?>, Map<String, Float>> experienceRegistriesMap = Map.of(
                AxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_AXE_STRIPPABLE,
                HoeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_HOE_TILLABLE,
                ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_CUTTABLE,
                ShovelItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_DIGGABLE
        );
        // Vérification et attribution à l'outil de l'expérience à obtenir d'une action secondaire sur bloc.
        ExperienceUtils.VerifyAndApplyExperience(player, level, experienceRegistriesMap, itemInHand, blockId);
    }

    public static void OnRightClickEntity(Player player, Level level, ItemStack itemInHand, Entity entity) {
        // Identifiant complet de l'entité avec laquelle le joueur vient d'interagir.
        String entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
        // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
        Map<Class<?>, Map<String, Float>> experienceRegistriesMap = Map.of(
                ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_SHEARABLE
        );
        // Vérification et attribution à l'outil de l'expérience à obtenir d'une action secondaire sur une entité.
        ExperienceUtils.VerifyAndApplyExperience(player, level, experienceRegistriesMap, itemInHand, entityId);
    }

    public static void OnEntityDeath(Player player, Level level, ItemStack mainHandItem, ItemStack offHandItem, LivingEntity deadEntity) {
        // Identifiant complet de l'entité avec laquelle le joueur vient d'interagir.
        String entityId = BuiltInRegistries.ENTITY_TYPE.getKey(deadEntity.getType()).toString();
        // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
        Map<Class<?>, Map<String, Float>> experienceRegistriesMap = Map.of(
                BowItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_BOW_KILLABLE,
                CrossbowItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_CROSSBOW_KILLABLE,
                MaceItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_MACE_KILLABLE,
                SwordItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SWORD_KILLABLE,
                TridentItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_TRIDENT_KILLABLE,
                ShieldItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHIELD_KILLABLE
        );
        // Vérification et attribution à la pièce d'équipement en main principale de l'expérience à obtenir de l'élimination d'une entité.
        ExperienceUtils.VerifyAndApplyExperience(player, level, experienceRegistriesMap, mainHandItem, entityId);
        // Vérification et attribution à la pièce d'équipement en main secondaire de l'expérience à obtenir de l'élimination d'une entité.
        ExperienceUtils.VerifyAndApplyExperience(player, level, experienceRegistriesMap, offHandItem, entityId);
    }

    public static void OnEntityHurt(Player player, Level level, Float damageAmount, Iterable<ItemStack> hurtArmor) {
        // Déclaration d'une map au format <ArmorItem.Type, Pair<ItemStack, EquipmentLevelingData>> pour simplifier le code et éviter un switch dans le jeu de condition suivant.
        EnumMap<ArmorItem.Type, Pair<ItemStack, EquipmentLevelingData>> armorItemData = new  EnumMap<>(ArmorItem.Type.class);
        // Si le joueur portait une armure, alors extrait les composants de données de chaque pièce en les injectant dans la map déclaré en haut.
        if (hurtArmor != null) {
            for (ItemStack item : hurtArmor) {
                if (item.getItem() instanceof ArmorItem armorItem) {
                    armorItemData.put(armorItem.getType(), Pair.of(item, item.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)));
                }
            }
        }

        // Somme des points d'expériences à ajouter à l'armure.
        float experienceToAddToArmor = (float) (damageAmount * 3.75);

        // Itération directe sur les types d’armure
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            Pair<ItemStack, EquipmentLevelingData> pair = armorItemData.get(type);
            if (pair != null && pair.getRight() != null) {
                AddExperience(player, level, pair.getLeft(), experienceToAddToArmor);
            }
        }
    }
}