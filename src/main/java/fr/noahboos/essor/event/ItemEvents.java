package fr.noahboos.essor.event;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import fr.noahboos.essor.component.challenge.ChallengesFactory;
import fr.noahboos.essor.utils.InventoryUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static fr.noahboos.essor.utils.Constants.UPGRADABLE_ITEM_CLASSES;
import static fr.noahboos.essor.utils.Constants.UPGRADABLE_ITEM_CLASSES_NO_ARMOUR;

@Mod.EventBusSubscriber
public class ItemEvents {
    @SubscribeEvent
    public static void OnItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        // Récupération de l'item fabriqué.
        ItemStack craftedItem = event.getCrafting();

        // Si l'item n'est pas une pièce d'équipement améliorable, on arrête l'exécution de la fonction.
        if (!UPGRADABLE_ITEM_CLASSES.contains(craftedItem.getItem().getClass())) {
            return;
        }

        // Si l'item ne possède pas le composant de données "DC_EQUIPMENT_LEVELING_DATA", on le lui ajoute.
        if (!craftedItem.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
            craftedItem.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData());
            ChallengesFactory.AssignChallenges(craftedItem);
        }
    }

    @SubscribeEvent
    public static void OnItemPickedUp(EntityItemPickupEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        // Récupération de l'item ramassé.
        ItemStack pickedUpItem = event.getItem().getItem();

        // Si l'item est une pièce d'équipement améliorable et qu'il ne possède pas le composant de données "DC_EQUIPMENT_LEVELING_DATA", on le lui ajoute.
        if (UPGRADABLE_ITEM_CLASSES.contains(pickedUpItem.getItem().getClass()) && !pickedUpItem.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
            pickedUpItem.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData());
            if (UPGRADABLE_ITEM_CLASSES_NO_ARMOUR.contains(pickedUpItem.getItem().getClass())) {
                ChallengesFactory.AssignChallenges(pickedUpItem);
            }
        }
    }
}
