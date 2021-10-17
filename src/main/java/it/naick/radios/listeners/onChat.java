package it.naick.radios.listeners;

import it.naick.radios.utils.Messages;
import it.naick.radios.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class onChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();

        if (!Util.isRadio(mainHand) && !Util.isRadio(offHand)) return;

        String radio = Util.isRadio(mainHand) ? Util.getRadio(mainHand) : Util.getRadio(offHand);

        if (!player.hasPermission("radios." + radio) && !player.isOp()) return;

        e.setCancelled(true);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ItemStack mainHand1 = onlinePlayer.getInventory().getItemInMainHand();
            ItemStack offHand1 = onlinePlayer.getInventory().getItemInMainHand();

            if (onlinePlayer.hasPermission("radios." + radio) || Util.isRadio(mainHand, radio) || Util.isRadio(offHand, radio)) {
                onlinePlayer.sendMessage(Messages.FORMAT
                        .replaceAll("%radio%", StringUtils.capitalize(radio))
                        .replaceAll("%name%", player.getName())
                        .replaceAll("%message%", message));
            }
        }

    }
}
