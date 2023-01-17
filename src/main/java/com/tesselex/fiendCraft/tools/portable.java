package com.tesselex.fiendCraft.tools;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class portable implements CommandExecutor {

    private fiendCraft plugin = null;
    public portable(fiendCraft fiendCraft) {
        this.plugin = plugin;
        Bukkit.getPluginCommand("workbench").setExecutor(this);
        Bukkit.getPluginCommand("furnace").setExecutor(this);
    }

    public void setPlugin(fiendCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            switch(command.getName()) {
                case "workbench":
                case "wb":
                    player.openWorkbench(null, true);
                    return true;
                case "furnace":
                case "smelt":

                    return true;
            }

        }
        return false;
    }
}
