package fr.noahboos.essor.utils;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import fr.noahboos.essor.component.challenge.ChallengesFactory;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import static fr.noahboos.essor.utils.Constants.UPGRADABLE_ITEM_CLASSES;
import static fr.noahboos.essor.utils.Constants.UPGRADABLE_ITEM_CLASSES_NO_ARMOUR;

public class InventoryUtils {
    public static void InventorySync(ServerPlayer player) {
        // Container ouvert
        player.containerMenu.broadcastChanges();

        // Tous les slots hors container (-2)
        int stateId = player.containerMenu.getStateId();

        // Hotbar (0-8)
        for (int i = 0; i <= 8; i++) {
            player.connection.send(new ClientboundContainerSetSlotPacket(-2, stateId, i, player.getInventory().getItem(i)));
        }

        // Inventaire principal (9-35)
        for (int i = 9; i <= 35; i++) {
            player.connection.send(new ClientboundContainerSetSlotPacket(-2, stateId, i, player.getInventory().getItem(i)));
        }

        // Armure (36-39)
        for (int i = 36; i <= 39; i++) {
            player.connection.send(new ClientboundContainerSetSlotPacket(-2, stateId, i, player.getInventory().getItem(i)));
        }

        // Main hand (slot sélectionné)
        player.connection.send(new ClientboundContainerSetSlotPacket(-2, stateId, player.getInventory().selected, player.getMainHandItem()));

        // Off-hand (slot 40)
        player.connection.send(new ClientboundContainerSetSlotPacket(-2, stateId, 40, player.getOffhandItem()));
    }

    public static void ApplyEquipmentLevelingDataToInventoryItems(Inventory inventory) {
        for (ItemStack itemStack : inventory.items) {
            if (UPGRADABLE_ITEM_CLASSES.contains(itemStack.getItem().getClass()) && !itemStack.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
                itemStack.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData());
                if (UPGRADABLE_ITEM_CLASSES_NO_ARMOUR.contains(itemStack.getItem().getClass())) {
                    ChallengesFactory.AssignChallenges(itemStack);
                }
            }
        }

        for (ItemStack itemStack : inventory.armor) {
            if (UPGRADABLE_ITEM_CLASSES.contains(itemStack.getItem().getClass()) && !itemStack.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
                itemStack.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData());
            }
        }

        for (ItemStack itemStack : inventory.offhand) {
            if (UPGRADABLE_ITEM_CLASSES.contains(itemStack.getItem().getClass()) && !itemStack.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
                itemStack.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData());
                if (UPGRADABLE_ITEM_CLASSES_NO_ARMOUR.contains(itemStack.getItem().getClass())) {
                    ChallengesFactory.AssignChallenges(itemStack);
                }
            }
        }
    }
}
