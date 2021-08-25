package me.thestrikem.strikebroadcast.utils;

import me.thestrikem.strikebroadcast.Main;
import me.thestrikem.strikebroadcast.consts.LoggerConsts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;

public class LoggerUtil {
    private static void sendMessage(ChatColor color, String loggerType, String message) {
        PluginDescriptionFile desc = Main.getInstance().getDescription();
        Bukkit.getConsoleSender().sendMessage(color + String.format("[%s/%s] %s", desc.getName()+":"+desc.getVersion(), loggerType, message));
    }

    public static <T> void sendSuccess(T message) {
        GlobalUtil.replaceValues(message, item -> {
            LoggerUtil.sendMessage(ChatColor.GREEN, LoggerConsts.LOGGER_SUCCESS, item);
        });
    }

    public static <T> void sendInfo(T message) {
        GlobalUtil.replaceValues(message, item -> {
            LoggerUtil.sendMessage(ChatColor.WHITE, LoggerConsts.LOGGER_INFO, item);
        });
    }

    public static <T> void sendWarn(T message) {
        GlobalUtil.replaceValues(message, item -> {
            LoggerUtil.sendMessage(ChatColor.YELLOW, LoggerConsts.LOGGER_WARN, item);
        });
    }

    public static <T> void sendError(T message) {
        GlobalUtil.replaceValues(message, item -> {
            LoggerUtil.sendMessage(ChatColor.RED, LoggerConsts.LOGGER_ERROR, item);
        });
    }
}
