package com.tesselex.fiendCraft.economy;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class pay implements CommandExecutor {

    private final fiendCraft plugin;
    public pay(fiendCraft plugin) {
        this.plugin = plugin;
        Bukkit.getPluginCommand("pay").setExecutor(this);
        Bukkit.getPluginCommand("bill").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }
}
