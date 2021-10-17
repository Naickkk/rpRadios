package it.naick.radios.commands;

import com.google.common.collect.Lists;
import it.naick.radios.Radios;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RadioCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§4| §cSintassi errata, utilizza: §7/" + label + " [radio]");
            return false;
        }

        String radio = args[0];

        if (!Radios.getRadios().contains(radio)) {
            player.sendMessage("§4| §cRadio inesistente, radio disponibili: §7" + formatList(Radios.getRadios()));
            return false;
        }

        ItemStack itemStack = Radios.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("%radio%", StringUtils.capitalize(radio)));

        List<String> replacedLore = Lists.newArrayList();
        for (String s : itemMeta.getLore()) {
            replacedLore.add(s.replaceAll("%radio%", StringUtils.capitalize(radio)));
        }

        itemMeta.setLocalizedName("#radio-" + radio);

        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
        player.updateInventory();

        player.sendMessage("§2|§a Hai ricevuto la radio §l" + StringUtils.capitalize(radio));


        return false;
    }

    public String formatList(List<String> stringList) {
        if (stringList.isEmpty()) return "Nessuna";
        return String.join(", ", stringList);
    }
}
