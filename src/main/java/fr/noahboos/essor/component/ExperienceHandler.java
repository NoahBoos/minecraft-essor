package fr.noahboos.essor.component;

import fr.noahboos.essor.loader.ExperienceDataRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;

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
            if (ExperienceDataRegistry.EXPERIENCE_DATA_AXE_BREAKABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_AXE_BREAKABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof HoeItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_HOE_BREAKABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_HOE_BREAKABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof PickaxeItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_PICKAXE_BREAKABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_PICKAXE_BREAKABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof ShearsItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_BREAKABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_BREAKABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof ShovelItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_BREAKABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_BREAKABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        }
    }

    public static void OnRightClickBlock(ItemStack itemInHand, Block block) {
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main au moment où il a fait un click droit sur le bloc.
        EquipmentLevelingData data = itemInHand.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Identifiant complet du bloc sur lequel le joueur vient de cliquer.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();

        // Jeu de conditions if/else accueillant le code relatif aux gains d'expériences.
        if (itemInHand.getItem() instanceof AxeItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_AXE_STRIPPABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_AXE_STRIPPABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof HoeItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_HOE_TILLABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_HOE_TILLABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof ShearsItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_CUTTABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_CUTTABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        } else if (itemInHand.getItem() instanceof ShovelItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_DIGGABLE.containsKey(blockId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_DIGGABLE.get(blockId);
                AddExperience(data, experienceToAdd);
            }
        }
    }

    public static void OnRightClickEntity(ItemStack itemInHand, Entity entity) {
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main au moment où il a fait un click droit sur le bloc.
        EquipmentLevelingData data = itemInHand.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Identifiant complet de l'entité avec laquelle le joueur vient d'interagir.
        String entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();

        // Jeu de conditions if/else accueillant le code relatif aux gains d'expériences.
        if (itemInHand.getItem() instanceof ShearsItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_SHEARABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_SHEARABLE.get(entityId);
                AddExperience(data, experienceToAdd);
            }
        }
    }

    public static void OnEntityDeath(ItemStack mainHandItem, ItemStack offHandItem, LivingEntity deadEntity) {
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main principale au moment où il a fait un click droit sur le bloc.
        EquipmentLevelingData mainHandItemData = mainHandItem.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main secondaire au moment où il a fait un click droit sur le bloc.
        EquipmentLevelingData offHandItemData = offHandItem.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Identifiant complet de l'entité avec laquelle le joueur vient d'interagir.
        String entityId = BuiltInRegistries.ENTITY_TYPE.getKey(deadEntity.getType()).toString();

        // Jeu de conditions if/else accueillant le code relatif aux gains d'expériences pour l'item dans la main principale.
        if (mainHandItem.getItem() instanceof BowItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_BOW_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_BOW_KILLABLE.get(entityId);
                AddExperience(mainHandItemData, experienceToAdd);
            }
        } else if (mainHandItem.getItem() instanceof CrossbowItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_CROSSBOW_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_CROSSBOW_KILLABLE.get(entityId);
                AddExperience(mainHandItemData, experienceToAdd);
            }
        } else if (mainHandItem.getItem() instanceof MaceItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_MACE_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_MACE_KILLABLE.get(entityId);
                AddExperience(mainHandItemData, experienceToAdd);
            }
        } else if (mainHandItem.getItem() instanceof SwordItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SWORD_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SWORD_KILLABLE.get(entityId);
                AddExperience(mainHandItemData, experienceToAdd);
            }
        } else if (mainHandItem.getItem() instanceof TridentItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_TRIDENT_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_TRIDENT_KILLABLE.get(entityId);
                AddExperience(mainHandItemData, experienceToAdd);
            }
        }

        // Jeu de conditions if/else accueillant le code relatif aux gains d'expériences pour l'item dans la main secondaire.
        if (offHandItem.getItem() instanceof ShieldItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHIELD_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHIELD_KILLABLE.get(entityId);
                AddExperience(offHandItemData, experienceToAdd);
            }
        }
    }

    public static void OnEntityHurt(Float damageAmount, Iterable<ItemStack> hurtArmor) {
        // Déclaration d'une map au format <ArmorItem.Type, EquipmentLevelingData> pour simplifier le code et éviter un switch dans le jeu de condition suivant.
        EnumMap<ArmorItem.Type, EquipmentLevelingData> armorItemData = new  EnumMap<>(ArmorItem.Type.class);
        // Si le joueur portait une armure, alors extrait les composants de données de chaque pièce en les injectant dans la map déclaré en haut.
        if (hurtArmor != null) {
            for (ItemStack item : hurtArmor) {
                if (item.getItem() instanceof ArmorItem armorItem) {
                    armorItemData.put(armorItem.getType(), item.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA));
                }
            }
        }

        // Récupération des composants de données "DC_EQUIPMENT_LEVELING_DATA" des éléments de l'armure.
        EquipmentLevelingData helmetItemData = armorItemData.getOrDefault(ArmorItem.Type.HELMET, null);
        EquipmentLevelingData chestplateItemData = armorItemData.getOrDefault(ArmorItem.Type.CHESTPLATE, null);
        EquipmentLevelingData leggingsItemData = armorItemData.getOrDefault(ArmorItem.Type.LEGGINGS, null);
        EquipmentLevelingData bootsItemData = armorItemData.getOrDefault(ArmorItem.Type.BOOTS, null);
        // Somme des points d'expériences à ajouter à l'armure.
        float experienceToAddToArmor = (float) (damageAmount * 2.5);

        // Jeu de conditions if accueillant le code relatif aux gains d'expériences pour les pièces d'armures du joueur ayant encaissé les dégâts..
        if (helmetItemData != null) {
            AddExperience(helmetItemData, experienceToAddToArmor);
        }
        if (chestplateItemData != null) {
            AddExperience(chestplateItemData, experienceToAddToArmor);
        }
        if (leggingsItemData != null) {
            AddExperience(leggingsItemData, experienceToAddToArmor);
        }
        if (bootsItemData != null) {
            AddExperience(bootsItemData, experienceToAddToArmor);
        }
    }
}
