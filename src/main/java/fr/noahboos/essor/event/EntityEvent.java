package fr.noahboos.essor.event;

import fr.noahboos.essor.component.ExperienceHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityEvent {
    @SubscribeEvent
    public static void OnRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        // Récupération de l'item que le joueur a en main au moment où il casse le bloc.
        ItemStack itemInHand = event.getItemStack();
        // Récupération de l'entité avec laquelle le joueur a intéragi.
        Entity entity = event.getTarget();

        // Déclenchement de la méthode du gestionnaire d'expérience en lien avec l'événement OnRightClickEntity.
        ExperienceHandler.OnRightClickEntity(player, event.getLevel(), itemInHand, entity);
    }

    @SubscribeEvent
    public static void OnEntityDeath(LivingDeathEvent event) {
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

        // Déclenchement de la méthode du gestionnaire d'expérience en lien avec l'événement OnEntityDeath().
        ExperienceHandler.OnEntityDeath((Player) killerEntity, event.getEntity().level(), mainHandItem, offHandItem, deadEntity);
    }

    @SubscribeEvent
    public static void OnEntityHurt(LivingHurtEvent event) {
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
        // Déclenchement de la méthode du gestionnaire d'expérience en lien avec l'événement OnEntityHurt().
        ExperienceHandler.OnEntityHurt((Player) hurtEntity, server.getLevel(Level.OVERWORLD), damageAmount, hurtArmor);
    }
}