package fr.noahboos.essor.event;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber
public class EquipmentLevelingEvent {
    // Un set regroupant l'entièreté des classes correspondant à une pièce d'équipement améliorable.
    private static final Set<Class<?>> UPGRADABLE_ITEM_CLASSES  = Set.of(
            SwordItem.class, PickaxeItem.class, AxeItem.class, ShovelItem.class, HoeItem.class,
            ArmorItem.class, BowItem.class, CrossbowItem.class, TridentItem.class, ShieldItem.class,
            FishingRodItem.class, FlintAndSteelItem.class, MaceItem.class
    );

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        // Récupération de l'item fabriqué et de la liste de ses composants de données.
        ItemStack craftedItem = event.getCrafting();

        // Si l'item n'est pas une pièce d'équipement améliorable, on arrête l'exécution de la fonction.
        if (!UPGRADABLE_ITEM_CLASSES.contains(craftedItem.getItem().getClass())) {
            return;
        }

        // Si l'item ne possède pas le composant de données "DC_EQUIPMENT_LEVELING_DATA", on le lui ajoute.
        if (!craftedItem.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
            craftedItem.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData(1, 100, 0f));
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        // Référence du tooltip affiché.
        List<Component> tooltip = event.getToolTip();
        // Récupération de l'item que le joueur survole dans son inventaire.
        ItemStack hoveredItem = event.getItemStack();

        // Si l'item possède le composant de données "DC_EQUIPMENT_LEVELING_DATA", on ajoute au tooltip les informations relatives à l'expérience de l'item.
        if (hoveredItem.has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
            EquipmentLevelingData hoveredItemData = hoveredItem.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
            tooltip.add(Component.translatable("tooltip.essor.level", hoveredItemData.GetLevel(), hoveredItemData.GetCurrentExperience(), hoveredItemData.GetLevelExperienceThreshold()));
        }
    }
}
