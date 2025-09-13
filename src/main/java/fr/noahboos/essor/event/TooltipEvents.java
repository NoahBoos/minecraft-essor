package fr.noahboos.essor.event;

import fr.noahboos.essor.Essor;
import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static fr.noahboos.essor.utils.Constants.CAN_GAIN_CHALLENGE_ITEM_CLASSES;

@Mod.EventBusSubscriber(modid = Essor.MOD_ID, value = Dist.CLIENT)
public class TooltipEvents {
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
            tooltip.add(Component.translatable("tooltip.essor.totalExperienceMultiplier", hoveredItemData.GetTotalExperienceMultiplier()));
            tooltip.add(Component.translatable("tooltip.essor.prestigeExperienceMultiplier", hoveredItemData.GetPrestigeExperienceMultiplier()));
            tooltip.add(Component.translatable("tooltip.essor.challengeExperienceMultiplier", hoveredItemData.GetChallengeExperienceMultiplier()));
            tooltip.add(Component.empty());
            tooltip.add(Component.translatable("tooltip.essor.level", hoveredItemData.GetLevel(), hoveredItemData.GetCurrentExperience(), hoveredItemData.GetRequiredExperienceToLevelUp()));
            StringBuilder levelProgressBar = new StringBuilder();
            int levelSegments = 25;
            int levelFilledSegments = (int) (((float) hoveredItemData.GetCurrentExperience() / (float) hoveredItemData.GetRequiredExperienceToLevelUp()) * levelSegments);
            levelProgressBar.append("§a■".repeat(Math.max(0, levelFilledSegments)));
            levelProgressBar.append("§7□".repeat(Math.max(0, levelSegments - levelFilledSegments)));
            tooltip.add(Component.literal(levelProgressBar.toString()));
            if (CAN_GAIN_CHALLENGE_ITEM_CLASSES.contains(hoveredItem.getItem().getClass())) {
                if (Screen.hasShiftDown()) {
                    hoveredItemData.GetChallenges().challenges.forEach(challenge -> {
                        tooltip.add(Component.empty());
                        StringBuilder challengeProgressBar = new StringBuilder();
                        challengeProgressBar.append("§a■".repeat(Math.max(0, challenge.currentTier)));
                        challengeProgressBar.append("§7□".repeat(Math.max(0, challenge.maximumTier - challenge.currentTier)));
                        tooltip.add(Component.translatable(challenge.id.replace(":", "."), challenge.progression, (challenge.isCompleted) ? challenge.tiers.get(challenge.currentTier) : challenge.tiers.get(challenge.currentTier + 1)).append(Component.literal(" - ")).append(Component.literal(challengeProgressBar.toString())));
                    });
                } else {
                    tooltip.add(Component.translatable("translation.holdToSeeChallenges"));
                }
            }
        }
    }
}
