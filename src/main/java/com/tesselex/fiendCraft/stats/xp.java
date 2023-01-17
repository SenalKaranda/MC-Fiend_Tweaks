package com.tesselex.fiendCraft.stats;

import com.tesselex.fiendCraft.fiendCraft;
import com.tesselex.fiendCraft.fiendTools.Logging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class xp implements CommandExecutor {

    private final fiendCraft plugin;
    public xp(fiendCraft plugin) {

        this.plugin = plugin;
        Bukkit.getPluginCommand("xp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Checks if sender is player.
        Player player = (Player) sender;
        switch(args.length) {
            case 0:
                player.sendMessage(ChatColor.RED + "Needs more arguments");
                return false;
            case 1:
                player.giveExpLevels(Integer.parseInt(args[0]));
                return true;
            case 2:
                player = Bukkit.getServer().getPlayer(args[0]);
                if(player != null) player.giveExpLevels(Integer.parseInt(args[1]));
                return true;
        }
        return false;
    }
}
