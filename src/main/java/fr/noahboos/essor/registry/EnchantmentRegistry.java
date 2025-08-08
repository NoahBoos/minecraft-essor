package fr.noahboos.essor.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentRegistry {
    private static RegistryAccess registryAccess = Minecraft.getInstance().level.registryAccess();
    public static Map<String, Holder<Enchantment>> DEFAULT_ENCHANTMENTS= new HashMap<>();

    static {
        DEFAULT_ENCHANTMENTS.put("unbreaking", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.UNBREAKING));
        DEFAULT_ENCHANTMENTS.put("protection", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.PROTECTION));
        DEFAULT_ENCHANTMENTS.put("projectile_protection", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.PROJECTILE_PROTECTION));
        DEFAULT_ENCHANTMENTS.put("blast_protection", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.BLAST_PROTECTION));
        DEFAULT_ENCHANTMENTS.put("fire_protection", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FIRE_PROTECTION));
        DEFAULT_ENCHANTMENTS.put("respiration", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.RESPIRATION));
        DEFAULT_ENCHANTMENTS.put("thorns", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.THORNS));
        DEFAULT_ENCHANTMENTS.put("aqua_affinity", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.AQUA_AFFINITY));
        DEFAULT_ENCHANTMENTS.put("swift_sneak", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SWIFT_SNEAK));
        DEFAULT_ENCHANTMENTS.put("soul_speed", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SOUL_SPEED));
        DEFAULT_ENCHANTMENTS.put("feather_falling", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FEATHER_FALLING));
        DEFAULT_ENCHANTMENTS.put("depth_strider", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.DEPTH_STRIDER));
        DEFAULT_ENCHANTMENTS.put("efficiency", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.EFFICIENCY));
        DEFAULT_ENCHANTMENTS.put("fortune", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FORTUNE));
        DEFAULT_ENCHANTMENTS.put("power", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.POWER));
        DEFAULT_ENCHANTMENTS.put("punch", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.PUNCH));
        DEFAULT_ENCHANTMENTS.put("multishot", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.MULTISHOT));
        DEFAULT_ENCHANTMENTS.put("piercing", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.PIERCING));
        DEFAULT_ENCHANTMENTS.put("quick_charge", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.QUICK_CHARGE));
        DEFAULT_ENCHANTMENTS.put("density", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.DENSITY));
        DEFAULT_ENCHANTMENTS.put("breach", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.BREACH));
        DEFAULT_ENCHANTMENTS.put("wind_burst", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.WIND_BURST));
        DEFAULT_ENCHANTMENTS.put("smite", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SMITE));
        DEFAULT_ENCHANTMENTS.put("bane_of_arthropods", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.BANE_OF_ARTHROPODS));
        DEFAULT_ENCHANTMENTS.put("sharpness", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS));
        DEFAULT_ENCHANTMENTS.put("looting", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOOTING));
        DEFAULT_ENCHANTMENTS.put("loyalty", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOYALTY));
        DEFAULT_ENCHANTMENTS.put("riptide", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.RIPTIDE));
        DEFAULT_ENCHANTMENTS.put("impaling", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.IMPALING));
        DEFAULT_ENCHANTMENTS.put("CHANNELING", registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.CHANNELING));
    }
}
