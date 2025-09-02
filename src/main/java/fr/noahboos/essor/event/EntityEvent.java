package fr.noahboos.essor.event;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ExperienceHandler;
import fr.noahboos.essor.component.ModDataComponentTypes;
import fr.noahboos.essor.utils.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static fr.noahboos.essor.component.ExperienceHandler.AddExperience;

@Mod.EventBusSubscriber
public class EntityEvent {
    @SubscribeEvent
    public static void OnRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide) return;
        boolean canGainExperience = false;
        // Récupération du joueur
        Player player = event.getEntity();
        // Récupération de l'item que le joueur a en main au moment où il casse le bloc.
        ItemStack itemInHand = event.getItemStack();
        // Récupération de l'entité avec laquelle le joueur a intéragi.
        Entity entity = event.getTarget();
        // Identifiant complet de l'entité avec laquelle le joueur vient d'interagir.
        String entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();

        if (entityId.equals("minecraft:sheep")) {
            if (entity instanceof Sheep sheep ) {
                if (!sheep.isBaby() && sheep.readyForShearing()) {
                    canGainExperience = true;
                }
            }
        } else if (entityId.equals("minecraft:mooshroom")) {
            if (entity instanceof MushroomCow mushroomCow) {
                if (!mushroomCow.isBaby() && mushroomCow.readyForShearing()) {
                    canGainExperience = true;
                }
            }
        }

        if (canGainExperience) {
            // Vérification et attribution à l'outil de l'expérience à obtenir d'une action secondaire sur une entité.
            ExperienceHandler.VerifyAndApplyExperience(player, player.level(), Constants.TOOL_TERTIARY_EXPERIENCE_REGISTRIES_MAP, itemInHand, entityId);
            canGainExperience = false;
            System.out.println("You gained experience.");
        }
    }

    @SubscribeEvent
    public static void OnEntityDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide) return;
        // Récupération de l'entité tuée.
        LivingEntity deadEntity = event.getEntity();
        // Récupération de la source des dégâts.
        DamageSource damageSource = event.getSource();
        // Récupération de l'entité étant la source des dégâts.
        Entity killerEntity = damageSource.getEntity();

        // Vérifie si l'entité étant la source des dégâts fataux est un joueur.
        if (!(killerEntity instanceof Player)) {
            return;
        }

        // Récupération de l'item que le joueur a dans sa main principale au moment où il tue l'entité.
        ItemStack mainHandItem = ((Player) killerEntity).getMainHandItem();
        // Récupération de l'item que le joueur a dans sa main secondaire au moment où il tue l'entité.
        ItemStack offHandItem = ((Player) killerEntity).getOffhandItem();


        // Identifiant complet de l'entité avec laquelle le joueur vient d'interagir.
        String entityId = BuiltInRegistries.ENTITY_TYPE.getKey(deadEntity.getType()).toString();
        // Vérification et attribution à la pièce d'équipement en main principale de l'expérience à obtenir de l'élimination d'une entité.
        ExperienceHandler.VerifyAndApplyExperience((Player) killerEntity, event.getEntity().level(), Constants.WEAPON_PRIMARY_EXPERIENCE_REGISTRIES_MAP, mainHandItem, entityId);
        // Vérification et attribution à la pièce d'équipement en main secondaire de l'expérience à obtenir de l'élimination d'une entité.
        ExperienceHandler.VerifyAndApplyExperience((Player) killerEntity, event.getEntity().level(), Constants.WEAPON_PRIMARY_EXPERIENCE_REGISTRIES_MAP, offHandItem, entityId);
    }

    @SubscribeEvent
    public static void OnEntityHurt(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide) return;
        // Récupération du serveur.
        MinecraftServer server = event.getEntity().getServer();
        if (server == null) return;
        // Récupération de l'entité blessée.
        LivingEntity hurtEntity = event.getEntity();
        // Collection itérable contenant les pièces d'armures équipées par le joueur blessé au moment où il a pris les dégâts.
        Iterable<ItemStack> hurtArmor = null;
        // Somme des dégâts encaissés par le joueur blessé.
        Float damageAmount = event.getAmount();

        if(!(hurtEntity instanceof Player)) {
            return;
        }
        // Si l'entité blessée est un joueur, alors on récupère son armure qu'on entrepose dans l'objet itérable hurtArmor.
        hurtArmor = hurtEntity.getArmorSlots();

        // Déclaration d'une map au format <ArmorItem.Type, Pair<ItemStack, EquipmentLevelingData>> pour simplifier le code et éviter un switch dans le jeu de condition suivant.
        EnumMap<ArmorItem.Type, Pair<ItemStack, EquipmentLevelingData>> armorItemData = new  EnumMap<>(ArmorItem.Type.class);
        // Si le joueur portait une armure, alors extrait les composants de données de chaque pièce en les injectant dans la map déclaré en haut.
        for (ItemStack item : hurtArmor) {
            if (item.getItem() instanceof ArmorItem armorItem) {
                armorItemData.put(armorItem.getType(), Pair.of(item, item.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)));
            }
        }

        // Somme des points d'expériences à ajouter à l'armure.
        float experienceToAddToArmor = (float) (damageAmount * 3.75);

        // Itération directe sur les types d’armure
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            Pair<ItemStack, EquipmentLevelingData> pair = armorItemData.get(type);
            if (pair != null && pair.getRight() != null) {
                AddExperience((Player) hurtEntity, server.getLevel(Level.OVERWORLD), pair.getLeft(), experienceToAddToArmor);
            }
        }
    }
}