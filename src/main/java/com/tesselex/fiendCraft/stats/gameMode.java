package com.tesselex.fiendCraft.stats;

import com.tesselex.fiendCraft.fiendCraft;
import com.tesselex.fiendCraft.fiendTools.Logging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gameMode implements CommandExecutor {


    private fiendCraft plugin = null;
    public gameMode(fiendCraft plugin) {

        this.plugin = plugin;
        Bukkit.getPluginCommand("gm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            int gmTry = Integer.parseInt(args[0]);
            GameMode gmTryFinal = GameMode.SURVIVAL;
            if(gmTry > -1 && gmTry < 4) {
                switch(gmTry) {
                    case 0:
                        break;
                    case 1:
                        gmTryFinal = GameMode.CREATIVE;
                        break;
                    case 2:
                        gmTryFinal = GameMode.ADVENTURE;
                        break;
                    case 3:
                        gmTryFinal = GameMode.SPECTATOR;
                        break;
                }
                plugin.log.info(ChatColor.GOLD + "Gamemode set!");
                player.setGameMode(gmTryFinal); return true;
            }
            else plugin.log.info(ChatColor.GOLD + "Command failed: Missing arguments.");
        }
        return false;
    }
}
