package com.tesselex.fiendCraft.listeners;

import com.tesselex.fiendCraft.economy.income;
import com.tesselex.fiendCraft.fiendCraft;
import com.tesselex.fiendCraft.storage.fiendData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.*;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class eventListener implements Listener {

    private fiendCraft plugin;
    public eventListener(fiendCraft plugin) {this.plugin = plugin;}

    private fiendData data = plugin.getFCData();
    private income incomeData = plugin.getIncome();
    private Map<Player, Map.Entry<Player, Float>> transactionMap = new HashMap<Player, Map.Entry<Player, Float>>();

    public eventListener() {

    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        playerTransaction(e.getPlayer(), e.getMessage());
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        //Checks for dailybonus
        incomeData.DailyBonus(player);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        getServer().broadcastMessage(ChatColor.GOLD + e.getPlayer().getName() + " has entered the server.");
        Player player = e.getPlayer();
        UUID id = player.getUniqueId();
        if(data.newPlayerCheck(id)) {
            newPlayerUtility(player, id);
        }
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent e) {
        //TODO
        //Add jobs!!!
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        getServer().broadcastMessage(ChatColor.GOLD + e.getPlayer().getName() + " has left the server.");
        removeTransaction(e.getPlayer());
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        //ToDo
    }

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent e) {
        //ToDo
    }



    public void newPlayerUtility(Player player, UUID id) {
        player.sendMessage(ChatColor.GOLD + "Welcome " + player.getName() + ", to FiendCraft!");
        data.setPlayerMoneyByUUID(id, 100.0F);
        data.setPlayerBankMoneyByUUID(id, 0F);
        data.setPlayerXPByUUID(id, 0F);
        data.setPlayerBankXPByUUID(id, 0F);
        data.setPlayerMicroCurrencyByUUID(id, 0F);
        data.addToPlayerDB(player);
    }

    public void playerTransaction(Player payer, String isAccepting) {
        if(transactionMap.containsKey(payer)) {
            if(isAccepting.toLowerCase(Locale.ROOT).contains("accept")) {
                payer.sendMessage(ChatColor.GOLD + "Transaction Accepted.");
                Map.Entry<Player, Float> transactionMapEntry = transactionMap.get(payer);
                incomeData.playerPayByUUID(payer, transactionMapEntry.getKey(), transactionMapEntry.getValue());
                payer.sendMessage(ChatColor.GOLD + "New Balance: " + transactionMapEntry.getValue());
                removeTransaction(payer);
            }
            removeTransaction(payer);
        }
        removeTransaction(payer);
    }

    public void addTransaction(Player payer, Player payee, Float transactionValue) {
        transactionMap.put(payer, new AbstractMap.SimpleEntry(payee, transactionValue));
    }

    public void removeTransaction(Player payer) {
        try {
            transactionMap.remove(payer);
        }
        catch (NullPointerException e) {
            //
        }
    }
}
