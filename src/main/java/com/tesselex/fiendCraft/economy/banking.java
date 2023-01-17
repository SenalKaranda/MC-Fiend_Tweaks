package com.tesselex.fiendCraft.economy;

import com.tesselex.fiendCraft.fiendCraft;
import com.tesselex.fiendCraft.storage.fiendData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.UUID;

public class banking implements CommandExecutor {

    private fiendCraft plugin = null;
    public banking(fiendCraft plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginCommand("bank").setExecutor(this);
    }
    private fiendData bank = plugin.getFCData();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID id = player.getUniqueId();

            //Switch on command
            switch(label) {
                case "bank":
                    //Switch on bank features
                    switch(args[0].toLowerCase(Locale.ROOT) + "|" + args[1].toLowerCase(Locale.ROOT)) {
                        case "withdraw|money":
                        case "w|m":
                            if(bank.getPlayerBankMoneyByUUID(id) > Float.parseFloat(args[2])) {
                                bank.setPlayerBankMoneyByUUID(id, bank.getPlayerBankMoneyByUUID(id) - Float.parseFloat(args[2]));
                                bank.setPlayerMoneyByUUID(id, bank.getPlayerMoneyByUUID(id) + Float.parseFloat(args[2]));
                            }
                            else break;
                        case "withdraw|xp":
                        case "w|xp":
                            if(bank.getPlayerBankXPByUUID(id) > Float.parseFloat(args[2])) {
                                bank.setPlayerBankXPByUUID(id, bank.getPlayerBankXPByUUID(id) - Float.parseFloat(args[2]));
                                bank.setPlayerXPByUUID(id, bank.getPlayerXPByUUID(id) + Float.parseFloat(args[2]));
                            }
                            else break;
                        case "deposit|money":
                        case "d|m":
                            if(bank.getPlayerMoneyByUUID(id) > Float.parseFloat(args[2])) {
                                bank.setPlayerMoneyByUUID(id, bank.getPlayerMoneyByUUID(id) - Float.parseFloat(args[2]));
                                bank.setPlayerBankMoneyByUUID(id, bank.getPlayerBankMoneyByUUID(id) + Float.parseFloat(args[2]));
                            }
                            else break;
                        case "deposit|xp":
                        case "d|xp":
                            if(bank.getPlayerXPByUUID(id) > Float.parseFloat(args[2])) {
                                bank.setPlayerXPByUUID(id, bank.getPlayerXPByUUID(id) - Float.parseFloat(args[2]));
                                bank.setPlayerBankXPByUUID(id, bank.getPlayerBankXPByUUID(id) + Float.parseFloat(args[2]));
                            }
                        case "balance|":
                        case "b|":
                            player.sendMessage(ChatColor.GOLD + "New Bank of Fiendland");
                            player.sendMessage(ChatColor.BLACK + "---------------");
                            player.sendMessage(ChatColor.GOLD + "|Accounts| ");
                            player.sendMessage(ChatColor.GOLD + "Money: " + ChatColor.GREEN + bank.getPlayerBankMoneyByUUID(id));
                            player.sendMessage(ChatColor.GOLD + "XP: " + ChatColor.YELLOW + bank.getPlayerBankXPByUUID(id));
                            break;
                    }
                    break;
                case "balance":
                    player.sendMessage(ChatColor.GOLD + player.getName() + "'s Wallet");
                    player.sendMessage(ChatColor.BLACK + "---------------");
                    player.sendMessage(ChatColor.GOLD + "|Accounts| ");
                    player.sendMessage(ChatColor.GOLD + "Money: " + ChatColor.GREEN + bank.getPlayerMoneyByUUID(id));
                    player.sendMessage(ChatColor.GOLD + "XP: " + ChatColor.YELLOW + bank.getPlayerXPByUUID(id));
                    player.sendMessage(ChatColor.GOLD + "MicroCurrency: " + ChatColor.LIGHT_PURPLE + bank.getPlayerMicroCurrencyByUUID(id));
                    break;

                case "pay":

                    break;
                case "bill":

                    break;
            }
        }
        return false;
    }
}
