package me.thestrikem.strikebroadcast.utils;

import me.thestrikem.strikebroadcast.Main;
import me.thestrikem.strikebroadcast.consts.ConfigConsts;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class ConfigUtil {
    private String[] potentialConfigNames;
    private static HashMap<String, FileConfiguration> configNames = new HashMap<>();

    public ConfigUtil(String[] potentialConfigNames) {
        this.potentialConfigNames = potentialConfigNames;
    }

    public void init() {
        ConfigUtil configUtil = new ConfigUtil(potentialConfigNames);
        Arrays.stream(potentialConfigNames).forEach(name -> {
            name = name+".yml";
            configNames.put(name, configUtil.create(name));
        });
    }

    public static FileConfiguration getConfig(String key) {
        return configNames.get(key);
    }

    private FileConfiguration create(String name) {
        File file = new File(Main.getInstance().getDataFolder(), name);
        if (Main.getInstance().getResource(name) == null)
            return new ConfigUtil(potentialConfigNames).save((FileConfiguration) YamlConfiguration.loadConfiguration(file), name);
        if (!file.exists()) {
            LoggerUtil.sendInfo("Create a file " + name + "...");
            Main.getInstance().saveResource(name, false);
        }
        return (FileConfiguration) YamlConfiguration.loadConfiguration(file);
    }

    private FileConfiguration save(FileConfiguration config, String name) {
        try {
            config.save(new File(Main.getInstance().getDataFolder(), name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
