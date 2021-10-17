package it.naick.radios.listeners;

import it.naick.radios.utils.Messages;
import it.naick.radios.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class onSwap implements Listener {

    @EventHandler
    public void onSwap(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        ItemStack newSlot = player.getInventory().getItem(e.getNewSlot());
        ItemStack previousSlot = player.getInventory().getItem(e.getPreviousSlot());

        if (Util.isRadio(newSlot)) {

            player.sendActionBar(Messages.JOIN.replaceAll("%radio%", Util.getRadio(newSlot)));

            return;
        }

        if (Util.isRadio(previousSlot)) {

            player.sendActionBar(Messages.LEAVE);
        }
    }
}
