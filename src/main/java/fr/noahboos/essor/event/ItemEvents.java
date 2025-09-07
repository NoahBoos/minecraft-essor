package fr.noahboos.essor.event;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import fr.noahboos.essor.component.challenge.ChallengesFactory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static fr.noahboos.essor.utils.Constants.UPGRADABLE_ITEM_CLASSES;
import static fr.noahboos.essor.utils.Constants.UPGRADABLE_ITEM_CLASSES_NO_ARMOUR;

@Mod.EventBusSubscriber
public class ItemEvents {
    @SubscribeEvent
    public static void OnItemCrafted(PlayerEvent.ItemCraftedEvent event) {
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

        // Parcourt l'inventaire du joueur et assigne à chaque pièce d'équipement améliorable ne le possédant pas le conteneur de données "DC_EQUIPMENT_LEVELING_DATA".
        for (ItemStack itemInInventory : event.getEntity().getInventory().items) {
            if (UPGRADABLE_ITEM_CLASSES.contains(itemInInventory.getItem().getClass()) && !itemInInventory.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
                itemInInventory.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData());
                if (UPGRADABLE_ITEM_CLASSES_NO_ARMOUR.contains(itemInInventory.getItem().getClass())) {
                    ChallengesFactory.AssignChallenges(itemInInventory);
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnItemPickedUp(EntityItemPickupEvent event) {
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

    @SubscribeEvent
    public static void OnItemTooltip(ItemTooltipEvent event) {
        // Référence du tooltip affiché.
        List<Component> tooltip = event.getToolTip();
        // Récupération de l'item que le joueur survole dans son inventaire.
        ItemStack hoveredItem = event.getItemStack();

        // Si l'item possède le composant de données "DC_EQUIPMENT_LEVELING_DATA", on ajoute au tooltip les informations relatives à l'expérience de l'item.
        if (hoveredItem.has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
            EquipmentLevelingData hoveredItemData = hoveredItem.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
            if (hoveredItemData == null) return;

            StringBuilder prestigeProgressBar = new StringBuilder();
            int prestigeSegments = 10;
            int prestigeFilledSegments = hoveredItemData.GetPrestige();
            prestigeProgressBar.append("§6★".repeat(Math.max(0, prestigeFilledSegments)));
            prestigeProgressBar.append("§7☆".repeat(Math.max(0, prestigeSegments - prestigeFilledSegments)));
            tooltip.add(Component.empty());
            tooltip.add(Component.literal(Component.translatable("tooltip.essor.prestige", hoveredItemData.GetPrestige()).getString() + prestigeProgressBar.toString()));
            tooltip.add(Component.translatable("tooltip.essor.experienceMultiplier", hoveredItemData.GetExperienceMultiplier()));
            tooltip.add(Component.empty());
            tooltip.add(Component.translatable("tooltip.essor.level", hoveredItemData.GetLevel(), hoveredItemData.GetCurrentExperience(), hoveredItemData.GetRequiredExperienceToLevelUp()));
            StringBuilder levelProgressBar = new StringBuilder();
            int levelSegments = 25;
            int levelFilledSegments = (int) (((float) hoveredItemData.GetCurrentExperience() / (float) hoveredItemData.GetRequiredExperienceToLevelUp()) * levelSegments);
            levelProgressBar.append("§a■".repeat(Math.max(0, levelFilledSegments)));
            levelProgressBar.append("§7□".repeat(Math.max(0, levelSegments - levelFilledSegments)));
            tooltip.add(Component.literal(levelProgressBar.toString()));
        }
    }
}
