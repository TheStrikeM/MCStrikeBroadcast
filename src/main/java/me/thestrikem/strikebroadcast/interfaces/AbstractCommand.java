package me.thestrikem.strikebroadcast.interfaces;

public abstract class AbstractCommand {
    public abstract String getDescription();
    public abstract String getUsage();
    public abstract String getPermission();
    public abstract String[] getAliases();
}
