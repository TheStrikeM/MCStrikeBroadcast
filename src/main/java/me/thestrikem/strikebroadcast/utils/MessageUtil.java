package me.thestrikem.strikebroadcast.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {
    public static void sendAll(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            MessageUtil.send(player, message);
        }
    }

    public static void send(Player player, String message) {
        GlobalUtil.replaceValues(message, (item) -> {
            player.sendMessage(translateColorCodes(item));
        });
    }

    public static String translateColorCodes(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
