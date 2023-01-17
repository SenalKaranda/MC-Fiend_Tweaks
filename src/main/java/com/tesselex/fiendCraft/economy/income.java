package com.tesselex.fiendCraft.economy;

import com.tesselex.fiendCraft.fiendCraft;
import com.tesselex.fiendCraft.storage.fiendData;
import com.tesselex.fiendCraft.fiendTools.itemGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.abs;

public class income implements CommandExecutor {


    private fiendCraft plugin;
    public income(fiendCraft plugin) {
        this.plugin = plugin;
    }
    private fiendData data = plugin.getFCData();
    private itemGenerator iGen = plugin.getItemGen();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    //Provides daily bonus for players
    public void DailyBonus(Player player) {
        if((player.getLastSeen()/3600000) > 23) {
            Item reward = iGen.generateItem();
            player.sendMessage(ChatColor.GOLD + "Congrats! You've been awarded a daily bonus of 1 " + ChatColor.GREEN + reward.getName() + "." + ChatColor.GOLD + "Enjoy!");
            player.getInventory().setItem(player.getInventory().firstEmpty(), reward.getItemStack().asQuantity(1));
        }
    }

    //Player to Player payment
    public void playerPayByUUID(Player payer, Player payee, Float transferValue) {
        Float payerMoney = data.getPlayerMoneyByUUID(payer.getUniqueId());
        Float payeeMoney = data.getPlayerMoneyByUUID(payee.getUniqueId());
        Float difference = (abs(payerMoney - transferValue));
        if(payerMoney >= transferValue) {
            data.setPlayerMoneyByUUID(payer.getUniqueId(), (payerMoney-transferValue));
            data.setPlayerMoneyByUUID(payee.getUniqueId(), (payeeMoney+transferValue));
        }
        else if(payerMoney < transferValue) {
            payer.sendMessage(ChatColor.RED + "You need an additional $" + difference + " to complete this transaction."); payer.sendMessage(ChatColor.BLUE + "Transfer money from your bank, or take out a bank loan at the New Bank of Fiendland.");
        }
    }

    //Player to Player billing
    public void playerBillSendByUUID(Player payer, Player payee, Float transferValue) {
        Float payerMoney = data.getPlayerMoneyByUUID(payer.getUniqueId());
        Float payeeMoney = data.getPlayerMoneyByUUID(payee.getUniqueId());
        if(payerMoney >= transferValue) {
            payer.sendMessage(ChatColor.BLUE + payee.getName() + ChatColor.GOLD + " is attempting to bill you " + ChatColor.RED + transferValue);
            payer.sendMessage(ChatColor.GOLD + "Type " + ChatColor.GREEN + "Accept" + " or " + ChatColor.RED + "Decline");
            plugin.getEvents().addTransaction(payer, payee, transferValue);
        }
        else if(payerMoney < transferValue) {
            payer.sendMessage(ChatColor.BLUE + payer.getName() + ChatColor.RED + " does not have enough money to pay.");
        }
    }
}
