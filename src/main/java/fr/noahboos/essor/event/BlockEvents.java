package fr.noahboos.essor.event;

import fr.noahboos.essor.component.ExperienceHandler;
import fr.noahboos.essor.utils.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class BlockEvents {
    @SubscribeEvent
    public static void OnBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        // Récupération de l'item que le joueur a en main au moment où il casse le bloc.
        ItemStack itemInHand = player.getMainHandItem();
        // Récupération du bloc que le joueur vient de casser.
        Block block = event.getState().getBlock();

        List<ItemStack> drops = Block.getDrops(event.getState(), ((ServerLevel) event.getLevel()), event.getPos(), event.getLevel().getBlockEntity(event.getPos()), event.getPlayer(), itemInHand);
        int totalDropCount = drops.stream().mapToInt(ItemStack::getCount).sum();

        // Identifiant complet du bloc que le joueur a cassé.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();
        // Vérification et attribution à l'outil de l'expérience à obtenir d'un bloc.
        ExperienceHandler.VerifyAndApplyExperience(player, player.level(), Constants.TOOL_PRIMARY_EXPERIENCE_REGISTRIES_MAP, itemInHand, blockId, totalDropCount);
    }

    @SubscribeEvent
    public static void OnRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        // Récupération de l'item que le joueur a en main au moment où il casse le bloc.
        ItemStack itemInHand = event.getItemStack();
        // Récupération de la position du bloc sur lequel le joueur vient de cliquer.
        BlockPos blockPosition = event.getPos();
        // Récupération du bloc sur lequel le joueur vient de cliquer.
        Block block = event.getLevel().getBlockState(blockPosition).getBlock();

        // Identifiant complet du bloc sur lequel le joueur vient de cliquer.
        String blockId = BuiltInRegistries.BLOCK.getKey(block).toString();
        // Vérification et attribution à l'outil de l'expérience à obtenir d'une action secondaire sur bloc.
        ExperienceHandler.VerifyAndApplyExperience(player, player.level(), Constants.TOOL_SECONDARY_EXPERIENCE_REGISTRIES_MAP, itemInHand, blockId);
    }
}
