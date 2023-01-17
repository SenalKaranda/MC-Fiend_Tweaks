package com.tesselex.fiendCraft.stats;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

import static com.tesselex.fiendCraft.effects.spawnFireworks.spawnFireworks;

public class healthDamage implements CommandExecutor {


    private final fiendCraft plugin;
    public healthDamage(fiendCraft plugin) {

        this.plugin = plugin;
        Bukkit.getPluginCommand("feed").setExecutor(this);
        Bukkit.getPluginCommand("heal").setExecutor(this);
        Bukkit.getPluginCommand("damage").setExecutor(this);
        Bukkit.getPluginCommand("clapcheeks").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            switch (command.getName()) {
                case "feed":
                    player.setFoodLevel(20);
                    return true;
                case "heal":
                    player.setHealth(20.0);
                    return true;
                case "clapcheeks":
                    if (args.length == 1 && Bukkit.getPlayer(args[0]) instanceof Damageable) {
                        spawnFireworks(Bukkit.getPlayer(args[0]).getLocation(), Color.RED, 3);
                        ((Damageable) Bukkit.getPlayer(args[0])).damage(5);
                        return true;
                    } else sender.sendMessage("Who do you want to target?! /clapcheeks <afuckingname>");
                    break;
            }
        }
        return false;
    }
}


