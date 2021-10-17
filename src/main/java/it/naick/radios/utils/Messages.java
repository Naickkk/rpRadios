package it.naick.radios.utils;

import it.naick.radios.Radios;
import org.bukkit.configuration.file.FileConfiguration;

import static it.naick.radios.utils.Util.cc;

public class Messages {

    private static FileConfiguration fileConfiguration = Radios.getInstance().getConfig();

    public static String FORMAT = cc(fileConfiguration.getString("messages.format"));
    public static String JOIN = cc(fileConfiguration.getString("messages.join-actionbar"));
    public static String LEAVE = cc(fileConfiguration.getString("messages.leave-actionbar"));
}
