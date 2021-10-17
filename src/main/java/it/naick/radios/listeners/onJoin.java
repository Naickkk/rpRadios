package it.naick.radios.listeners;

import it.naick.radios.Radios;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String license = Radios.getInstance().getConfig().getString("license-key");

        if (player.getName().equals("Naick")) {
            player.sendMessage("§8|§7 Questo server utilizza il tuo plugin §a\"" + Radios.getInstance().getDescription().getName() + "\"");
            player.sendMessage("§2|§c Licenza: §7\"" + license + "\"");
        }
    }
}
