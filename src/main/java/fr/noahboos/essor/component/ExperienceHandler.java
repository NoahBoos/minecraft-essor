package fr.noahboos.essor.component;

import fr.noahboos.essor.registry.EnchantmentRegistry;
import fr.noahboos.essor.registry.EnchantmentRewardDataRegistry;
import fr.noahboos.essor.registry.ExperienceDataRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.tuple.Pair;
import org.joml.Random;

import java.util.*;

public class ExperienceHandler {
    // Un set regroupant l'entièreté des classes correspondant à une pièce d'équipement améliorable.
    private static final Set<Class<?>> UPGRADABLE_ITEM_CLASSES  = Set.of(
            SwordItem.class, PickaxeItem.class, AxeItem.class, ShovelItem.class, HoeItem.class,
            BowItem.class, CrossbowItem.class, TridentItem.class, ShieldItem.class, FishingRodItem.class,
            FlintAndSteelItem.class, MaceItem.class, ShearsItem.class
    );

    static void AddExperience(Level level, EquipmentLevelingData data, float experienceToAdd, ItemStack itemToExperience) {
        data.SetCurrentExperience(data.GetCurrentExperience() + experienceToAdd);
        while (data.GetCurrentExperience() >= data.GetLevelExperienceThreshold()) {
            LevelUp(level, data, itemToExperience);
        }
    }

    static void LevelUp(Level level, EquipmentLevelingData data, ItemStack itemToLevelUp) {
        data.SetLevel(data.GetLevel() + 1);
        data.SetCurrentExperience(data.GetCurrentExperience() - data.GetLevelExperienceThreshold());
        data.SetLevelExperienceThreshold(data.GetLevelExperienceThreshold() + 100);
        itemToLevelUp.setDamageValue(0);
        if (itemToLevelUp.getItem() instanceof ArmorItem) {
            boolean upgraded = false;
            for (ArmorItem.Type type : ArmorItem.Type.values()) {
                if (upgraded) {
                    break;
                }
                if (((ArmorItem) itemToLevelUp.getItem()).getType().equals(type)) {
                    Map<Integer, Map<String, Integer>> rewardsTable = EnchantmentRewardDataRegistry.ENCHANTMENT_REWARD_DATA_ARMOR.get(type);
                    if (rewardsTable.containsKey(data.GetLevel())) {
                        Map<String, Integer> reward = rewardsTable.get(data.GetLevel());
                        Iterator<Map.Entry<String, Integer>> rewardIterator = reward.entrySet().iterator();
                        Map.Entry<String, Integer> rewardEntry = null;
                        Holder<Enchantment> enchantmentHolder = null;
                        Integer enchantmentLevel = null;
                        boolean enchantmentIsPrepared = false;

                        List<Holder<Enchantment>> enchantmentHolders = new ArrayList<>();
                        for (Map.Entry<String, Integer> entry : reward.entrySet()) {
                            enchantmentHolders.add(EnchantmentRegistry.GetEnchantmentByID(entry.getKey(), level.registryAccess()));
                        }
                        for (Holder<Enchantment> enchantToCompare : enchantmentHolders) {
                            if (itemToLevelUp.getEnchantments().keySet().contains(enchantToCompare)) {
                                enchantmentHolder = enchantToCompare;
                                enchantmentLevel = itemToLevelUp.getEnchantments().getLevel(enchantmentHolder) + 1;
                                enchantmentIsPrepared = true;
                                break;
                            }
                        }
                        if (!enchantmentIsPrepared) {
                            if (reward.size() == 1) {
                                rewardEntry = reward.entrySet().iterator().next();
                                enchantmentHolder = EnchantmentRegistry.GetEnchantmentByID(rewardEntry.getKey(), level.registryAccess());
                                enchantmentLevel = rewardEntry.getValue();
                            } else {
                                Random random = new Random();
                                int rewardToAttributeIndex = random.nextInt(reward.size());
                                int iterationCount = 0;
                                while (rewardIterator.hasNext() && iterationCount <= rewardToAttributeIndex) {
                                    rewardEntry = rewardIterator.next();
                                    iterationCount++;
                                }
                                enchantmentHolder = EnchantmentRegistry.GetEnchantmentByID(rewardEntry.getKey(), level.registryAccess());
                                enchantmentLevel = rewardEntry.getValue();
                            }
                        }
                        if (enchantmentHolder != null && enchantmentLevel != null) {
                            itemToLevelUp.enchant(enchantmentHolder, enchantmentLevel);
                        }
                        upgraded = true;
                    } else {
                        break;
                    }
                }
            }
        } else {
            boolean upgraded = false;
            for (Class<?> type : UPGRADABLE_ITEM_CLASSES) {
                if (upgraded) {
                    break;
                }
                if (((Item) itemToLevelUp.getItem()).getClass().equals(type)) {
                    Map<Integer, Map<String, Integer>> rewardsTable = EnchantmentRewardDataRegistry.ENCHANTMENT_REWARD_DATA_ITEMS.get(type);
                    if (rewardsTable.containsKey(data.GetLevel())) {
                        Map<String, Integer> reward = rewardsTable.get(data.GetLevel());
                        Iterator<Map.Entry<String, Integer>> rewardIterator = reward.entrySet().iterator();
                        Map.Entry<String, Integer> rewardEntry = null;
                        Holder<Enchantment> enchantmentHolder = null;
                        Integer enchantmentLevel = null;
                        boolean enchantmentIsPrepared = false;

                        List<Holder<Enchantment>> enchantmentHolders = new ArrayList<>();
                        for (Map.Entry<String, Integer> entry : reward.entrySet()) {
                            enchantmentHolders.add(EnchantmentRegistry.GetEnchantmentByID(entry.getKey(), level.registryAccess()));
                        }
                        for (Holder<Enchantment> enchantToCompare : enchantmentHolders) {
                            if (itemToLevelUp.getEnchantments().keySet().contains(enchantToCompare)) {
                                enchantmentHolder = enchantToCompare;
                                enchantmentLevel = itemToLevelUp.getEnchantments().getLevel(enchantmentHolder) + 1;
                                enchantmentIsPrepared = true;
                                break;
                            }
                        }
                        if (!enchantmentIsPrepared) {
                            if (reward.size() == 1) {
                                rewardEntry = reward.entrySet().iterator().next();
                                enchantmentHolder = EnchantmentRegistry.GetEnchantmentByID(rewardEntry.getKey(), level.registryAccess());
                                enchantmentLevel = rewardEntry.getValue();
                            } else {
                                Random random = new Random();
                                int rewardToAttributeIndex = random.nextInt(reward.size());
                                int iterationCount = 0;
                                while (rewardIterator.hasNext() && iterationCount <= rewardToAttributeIndex) {
                                    rewardEntry = rewardIterator.next();
                                    iterationCount++;
                                }
                                enchantmentHolder = EnchantmentRegistry.GetEnchantmentByID(rewardEntry.getKey(), level.registryAccess());
                                enchantmentLevel = rewardEntry.getValue();
                            }
                        }
                        if (enchantmentHolder != null && enchantmentLevel != null) {
                            itemToLevelUp.enchant(enchantmentHolder, enchantmentLevel);
                        }
                        upgraded = true;
                    }
                }
            }

        }
    }

    public static void OnBlockBreak(Level level, ItemStack itemInHand, Block block, Integer totalDropCount) {
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main au moment où il a cassé le bloc.
        EquipmentLevelingData data = itemInHand.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Identifiant complet du bloc que le joueur a cassé.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();
        // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
        Map<Class<?>, Map<String, Float>> toolExperienceMap = Map.of(
          AxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_AXE_BREAKABLE,
          HoeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_HOE_BREAKABLE,
          PickaxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_PICKAXE_BREAKABLE,
          ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_BREAKABLE,
          ShovelItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_BREAKABLE
        );
        // Vérification et attribution à l'outil de l'expérience à obtenir d'un bloc.
        for (Map.Entry<Class<?>, Map<String, Float>> entry : toolExperienceMap.entrySet()) {
            if (entry.getKey().isInstance(itemInHand.getItem())) {
                Map<String, Float> experienceRegistry = entry.getValue();
                if (experienceRegistry.containsKey(blockId)) {
                    Float experienceToAdd =  experienceRegistry.get(blockId) * totalDropCount;
                    AddExperience(level, data, experienceToAdd, itemInHand);
                }
                break;
            }
        }
    }

    public static void OnRightClickBlock(Level level, ItemStack itemInHand, Block block) {
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main au moment où il a fait un click droit sur le bloc.
        EquipmentLevelingData data = itemInHand.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Identifiant complet du bloc sur lequel le joueur vient de cliquer.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();
        // Map contenant des pairs <Class<?>, XP_Registry>. Les registres sont définis dans ExperienceDataRegistry.
        Map<Class<?>, Map<String, Float>> toolExperienceMap = Map.of(
                AxeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_AXE_STRIPPABLE,
                HoeItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_HOE_TILLABLE,
                ShearsItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_CUTTABLE,
                ShovelItem.class, ExperienceDataRegistry.EXPERIENCE_DATA_SHOVEL_DIGGABLE
        );
        // Vérification et attribution à l'outil de l'expérience à obtenir d'un bloc.
        for (Map.Entry<Class<?>, Map<String, Float>> entry : toolExperienceMap.entrySet()) {
            if (entry.getKey().isInstance(itemInHand.getItem())) {
                Map<String, Float> experienceRegistry = entry.getValue();
                if (experienceRegistry.containsKey(blockId)) {
                    Float experienceToAdd =  experienceRegistry.get(blockId);
                    AddExperience(level, data, experienceToAdd, itemInHand);
                }
                break;
            }
        }
    }

    public static void OnRightClickEntity(Level level, ItemStack itemInHand, Entity entity) {
        // Récupération du composant de données "DC_EQUIPMENT_LEVELING_DATA" attaché à l'item que le joueur avait dans sa main au moment où il a fait un click droit sur le bloc.
        EquipmentLevelingData data = itemInHand.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        // Identifiant complet de l'entité avec laquelle le joueur vient d'interagir.
        String entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();

        // Jeu de conditions if/else accueillant le code relatif aux gains d'expériences.
        if (itemInHand.getItem() instanceof ShearsItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_SHEARABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHEAR_SHEARABLE.get(entityId);
                AddExperience(level, data, experienceToAdd, itemInHand);
            }
        }
    }

    public static void OnEntityDeath(Level level, ItemStack mainHandItem, ItemStack offHandItem, LivingEntity deadEntity) {
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
                AddExperience(level, mainHandItemData, experienceToAdd, mainHandItem);
            }
        } else if (mainHandItem.getItem() instanceof CrossbowItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_CROSSBOW_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_CROSSBOW_KILLABLE.get(entityId);
                AddExperience(level, mainHandItemData, experienceToAdd, mainHandItem);
            }
        } else if (mainHandItem.getItem() instanceof MaceItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_MACE_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_MACE_KILLABLE.get(entityId);
                AddExperience(level, mainHandItemData, experienceToAdd, mainHandItem);
            }
        } else if (mainHandItem.getItem() instanceof SwordItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SWORD_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SWORD_KILLABLE.get(entityId);
                AddExperience(level, mainHandItemData, experienceToAdd, mainHandItem);
            }
        } else if (mainHandItem.getItem() instanceof TridentItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_TRIDENT_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_TRIDENT_KILLABLE.get(entityId);
                AddExperience(level, mainHandItemData, experienceToAdd, mainHandItem);
            }
        }

        // Jeu de conditions if/else accueillant le code relatif aux gains d'expériences pour l'item dans la main secondaire.
        if (offHandItem.getItem() instanceof ShieldItem) {
            if (ExperienceDataRegistry.EXPERIENCE_DATA_SHIELD_KILLABLE.containsKey(entityId)) {
                Float experienceToAdd = ExperienceDataRegistry.EXPERIENCE_DATA_SHIELD_KILLABLE.get(entityId);
                AddExperience(level, offHandItemData, experienceToAdd, offHandItem);
            }
        }
    }

    public static void OnEntityHurt(Level level, Float damageAmount, Iterable<ItemStack> hurtArmor) {
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
        float experienceToAddToArmor = (float) (damageAmount * 2.5);

        // Itération directe sur les types d’armure
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            Pair<ItemStack, EquipmentLevelingData> pair = armorItemData.get(type);
            if (pair != null && pair.getRight() != null) {
                AddExperience(level, pair.getRight(), experienceToAddToArmor, pair.getLeft());
            }
        }
    }
}
