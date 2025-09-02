package fr.noahboos.essor.utils;

import net.minecraft.world.item.*;

import java.util.Set;

public class Constants {
    // Un set regroupant l'entièreté des classes correspondant à une pièce d'équipement améliorable.
    public static final Set<Class<?>> UPGRADABLE_ITEM_CLASSES  = Set.of(
            SwordItem.class, PickaxeItem.class, AxeItem.class, ShovelItem.class, HoeItem.class,
            ArmorItem.class, BowItem.class, CrossbowItem.class, TridentItem.class, ShieldItem.class,
            FishingRodItem.class, FlintAndSteelItem.class, MaceItem.class, ShearsItem.class
    );
}
