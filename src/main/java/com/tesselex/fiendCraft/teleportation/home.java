package com.tesselex.fiendCraft.teleportation;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class home  implements CommandExecutor {

    private final fiendCraft plugin;

    public home(fiendCraft plugin) {
        this.plugin = plugin;
        Bukkit.getPluginCommand("home").setExecutor(this);
        Bukkit.getPluginCommand("sethome").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            UUID id = player.getUniqueId();
            switch (command.getName()) {
                case "home":
                case "h":

                    return true;
                case "sethome":
                case "sh":
                    plugin.setHome(player);
                    return true;
                /*case "remhome":
                case "rhome":
                    plugin.setHome(player);
                    return true;*/
            }
        }
        return false;
    }
}


