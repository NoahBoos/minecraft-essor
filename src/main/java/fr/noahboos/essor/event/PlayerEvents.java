package fr.noahboos.essor.event;

import fr.noahboos.essor.utils.InventoryUtils;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void OnPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide) return;

        // Parcourt l'inventaire du joueur et assigne à chaque pièce d'équipement améliorable ne le possédant pas le conteneur de données "DC_EQUIPMENT_LEVELING_DATA".
        InventoryUtils.ApplyEquipmentLevelingDataToInventoryItems(event.getEntity().getInventory());
    }
}
