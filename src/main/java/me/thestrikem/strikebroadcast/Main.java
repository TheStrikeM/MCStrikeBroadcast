package me.thestrikem.strikebroadcast;

import me.thestrikem.strikebroadcast.consts.ConfigConsts;
import me.thestrikem.strikebroadcast.modules.broadcast.BroadcastCommand;
import me.thestrikem.strikebroadcast.utils.ConfigUtil;
import me.thestrikem.strikebroadcast.utils.LoggerUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main main;

    @Override
    public void onEnable() {
        this.main = this;
        new ConfigUtil(new String[]{"main" ,"broadcast", "chat"}).init();
        LoggerUtil.sendInfo(ConfigUtil.getConfig(ConfigConsts.MAIN_CONFIG).getString("prefix"));
        LoggerUtil.sendInfo("Plugin success enabled;Author is TheStrikeM;Very nice!");

        getCommand("bc").setExecutor(new BroadcastCommand());
    }

    public void onDisable() {
        LoggerUtil.sendInfo("Plugin success disabled;Author is TheStrikeM;Very nice!");
    }

    public static Main getInstance() { return main; };
}
