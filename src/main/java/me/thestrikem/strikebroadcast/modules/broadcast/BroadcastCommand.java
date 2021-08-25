package me.thestrikem.strikebroadcast.modules.broadcast;

import me.thestrikem.strikebroadcast.consts.ConfigConsts;
import me.thestrikem.strikebroadcast.interfaces.AbstractCommand;
import me.thestrikem.strikebroadcast.utils.ConfigUtil;
import me.thestrikem.strikebroadcast.utils.LoggerUtil;
import me.thestrikem.strikebroadcast.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand extends AbstractCommand implements CommandExecutor {
    @Override
    public String getDescription() {
        return "Send broadcast to all players";
    }

    @Override
    public String getPermission() {
        return "strikebroadcast.bc";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"bc", "broadcast"};
    }

    @Override
    public String getUsage() {
        return "/<command> <message>";
    }

    private static String getAndReplace(String[] args, String displayName) {
        return ConfigUtil.getConfig(ConfigConsts.BROADCAST_CONFIG).getString("message")
                .replace("%message%", String.join(" ", args))
                .replace("%player%", displayName);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String paramString, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(getPermission())) {
                LoggerUtil.sendInfo(MessageUtil.translateColorCodes(BroadcastCommand.getAndReplace(args, player.getDisplayName())));
                MessageUtil.sendAll(BroadcastCommand.getAndReplace(args, player.getDisplayName()));
            } else MessageUtil.send(player, ConfigUtil.getConfig(ConfigConsts.MAIN_CONFIG).getString("no-permission"));
        } else {
            LoggerUtil.sendInfo(MessageUtil.translateColorCodes(BroadcastCommand.getAndReplace(args, "Консоль")));
            MessageUtil.sendAll(BroadcastCommand.getAndReplace(args, "Консоль"));
        }
        return true;
    }
}
