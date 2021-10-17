package it.naick.radios.utils;

import it.naick.radios.Radios;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class Util {

    public static String cc(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static boolean isRadio(ItemStack itemStack) {
        return itemStack != null
                && itemStack.hasItemMeta()
                && itemStack.getItemMeta().hasDisplayName()
                && itemStack.getItemMeta().getDisplayName().equals(Radios.getItemStack().getItemMeta().getDisplayName())
                && itemStack.getItemMeta().getCustomModelData() == Radios.getItemStack().getItemMeta().getCustomModelData();
    }

    public static boolean isRadio(ItemStack itemStack, String radio) {
        return itemStack != null
                && itemStack.hasItemMeta()
                && itemStack.getItemMeta().hasDisplayName()
                && itemStack.getItemMeta().getDisplayName().equals(Radios.getItemStack().getItemMeta().getDisplayName())
                && itemStack.getItemMeta().getCustomModelData() == Radios.getItemStack().getItemMeta().getCustomModelData()
                && itemStack.getItemMeta().getLocalizedName().contains(radio);
    }

    public static String getRadio(ItemStack itemStack) {
        return itemStack.getItemMeta().getLocalizedName().replace("#radio-", "").toLowerCase();
    }
}
