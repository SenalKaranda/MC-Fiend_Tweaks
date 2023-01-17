package com.tesselex.fiendCraft.teleportation;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class spawn implements CommandExecutor {

    private fiendCraft plugin = null;
    public spawn(fiendCraft plugin) {
        this.plugin = plugin;
        Bukkit.getPluginCommand("spawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }
}
