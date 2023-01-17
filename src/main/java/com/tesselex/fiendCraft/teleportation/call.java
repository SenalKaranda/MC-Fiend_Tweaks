package com.tesselex.fiendCraft.teleportation;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class call implements CommandExecutor {

    private final fiendCraft plugin;
    public call(fiendCraft plugin) {
        this.plugin = plugin;
        Bukkit.getPluginCommand("call").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if((args.length == 1) && (Bukkit.getPlayer(args[0]) != null) && (sender instanceof Player)) {
            Player playerA = (Player) sender;
            Player playerB = Bukkit.getPlayer(args[0]);
            playerA.teleport(playerB);
            return true;
        }
        else
        return false;
    }
}
