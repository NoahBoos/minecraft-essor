package fr.noahboos.essor.component;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class ActionBar {
    public static void DisplayXPCount(ServerPlayer player, ItemStack itemInHand) {
        EquipmentLevelingData equipmentLevelingData = itemInHand.get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);
        if (equipmentLevelingData == null) return;

        String experienceProgressCount = Component.translatable("translation.level").getString() + " " + equipmentLevelingData.GetLevel() + " - " + equipmentLevelingData.GetCurrentExperience() + " / " + equipmentLevelingData.GetRequiredExperienceToLevelUp() + " " + Component.translatable("translation.experiencePoint").getString();
        player.displayClientMessage(Component.literal(experienceProgressCount), true);
    }
}
