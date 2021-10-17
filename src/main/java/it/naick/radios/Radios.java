package it.naick.radios;

import com.google.common.collect.Lists;
import it.naick.radios.commands.RadioCommand;
import it.naick.radios.license.AdvancedLicense;
import it.naick.radios.listeners.onChat;
import it.naick.radios.listeners.onJoin;
import it.naick.radios.listeners.onSwap;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static it.naick.radios.utils.Util.cc;

public final class Radios extends JavaPlugin {

    @Getter
    private static Radios instance;
    @Getter
    private static List<String> radios = Lists.newArrayList();
    @Getter
    private static ItemStack itemStack;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        saveDefaultConfig();

        if (!new AdvancedLicense(this.getConfig().getString("license-key"), "https://unfathomed-foods.000webhostapp.com/verify.php", this).register()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        registerListeners();
        registerCommands();
        loadRadios();

        ItemStack itemStack = new ItemStack(Material.valueOf(this.getConfig().getString("item.material")));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(cc(this.getConfig().getString("item.displayname")));
        itemMeta.setCustomModelData(this.getConfig().getInt("item.model"));

        List<String> lore = Lists.newArrayList();

        for (String string : this.getConfig().getStringList("item.lore")) {
            lore.add(cc(string));
        }

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        Radios.itemStack = itemStack;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new onChat(), this);
        pluginManager.registerEvents(new onJoin(), this);
        pluginManager.registerEvents(new onSwap(), this);

    }

    private void registerCommands() {

        getCommand("radio").setExecutor(new RadioCommand());

    }

    private void loadRadios() {

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            radios.addAll(this.getConfig().getStringList("radios"));
        });

    }
}
