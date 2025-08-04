package fr.noahboos.essor.event;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ExperienceHandler;
import fr.noahboos.essor.component.ModDataComponentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber
public class EquipmentLevelingEvent {
    // Un set regroupant l'entièreté des classes correspondant à une pièce d'équipement améliorable.
    private static final Set<Class<?>> UPGRADABLE_ITEM_CLASSES  = Set.of(
            SwordItem.class, PickaxeItem.class, AxeItem.class, ShovelItem.class, HoeItem.class,
            ArmorItem.class, BowItem.class, CrossbowItem.class, TridentItem.class, ShieldItem.class,
            FishingRodItem.class, FlintAndSteelItem.class, MaceItem.class, ShearsItem.class
    );

    // @LEGACY Un Integer entreposant la durabilité de l'item utilisé pour effectuer une action. Il est utilisé comme un moyen de sécurité évitant la multiple exécution des méthodes relatives à l'ajout d'expérience dans certains écouteurs d'évènements.
    // private static int itemDurability = -1;

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
        }

        // Parcourt l'inventaire du joueur et assigne à chaque pièce d'équipement améliorable ne le possédant pas le conteneur de données "DC_EQUIPMENT_LEVELING_DATA".
        for (ItemStack itemInInventory : event.getEntity().getInventory().items) {
            if (UPGRADABLE_ITEM_CLASSES.contains(itemInInventory.getItem().getClass()) && !itemInInventory.getComponents().has(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA)) {
                itemInInventory.set(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA, new EquipmentLevelingData());
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
            tooltip.add(Component.translatable("tooltip.essor.level", hoveredItemData.GetLevel(), hoveredItemData.GetCurrentExperience(), hoveredItemData.GetLevelExperienceThreshold()));
        }
    }

    @SubscribeEvent
    public static void OnBlockBreak(BlockEvent.BreakEvent event) {
        // Récupération de l'item que le joueur a en main au moment où il casse le bloc.
        ItemStack itemInHand = event.getPlayer().getMainHandItem();
        // Récupération du bloc que le joueur vient de casser.
        Block block = event.getState().getBlock();

        // Déclenchement de la méthode du gestionnaire d'expérience en lien avec l'événement onBlockBreak.
        ExperienceHandler.OnBlockBreak(itemInHand, block);
    }

    @SubscribeEvent
    public static void OnRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        // Récupération de l'item que le joueur a en main au moment où il casse le bloc.
        ItemStack itemInHand = event.getItemStack();
        // Récupération de la position du bloc sur lequel le joueur vient de cliquer.
        BlockPos blockPosition = event.getPos();
        // Récupération du bloc sur lequel le joueur vient de cliquer.
        Block block = event.getLevel().getBlockState(blockPosition).getBlock();

        // Déclenchement de la méthode du gestionnaire d'expérience en lien avec l'événement OnRightClickBlock.
        ExperienceHandler.OnRightClickBlock(itemInHand, block);
    }

    @SubscribeEvent
    public static void OnRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        // Récupération de l'item que le joueur a en main au moment où il casse le bloc.
        ItemStack itemInHand = event.getItemStack();
        // Récupération de l'entité avec laquelle le joueur a intéragi.
        Entity entity = event.getTarget();

        // Déclenchement de la méthode du gestionnaire d'expérience en lien avec l'événement OnRightClickEntity.
        ExperienceHandler.OnRightClickEntity(itemInHand, entity);
    }
}
