package com.tesselex.fiendCraft.teleportation;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warp implements CommandExecutor {


    private final fiendCraft plugin;
    public warp(fiendCraft plugin) {

        this.plugin = plugin;
        Bukkit.getPluginCommand("warp").setExecutor(this);
        Bukkit.getPluginCommand("setwarp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player) {

            Player player = (Player) sender;
            return true;

        }
        return false;
    }
}