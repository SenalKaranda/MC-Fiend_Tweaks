package com.tesselex.fiendCraft;

import com.tesselex.fiendCraft.economy.banking;
import com.tesselex.fiendCraft.economy.ecoBase;
import com.tesselex.fiendCraft.economy.income;
import com.tesselex.fiendCraft.economy.pay;
import com.tesselex.fiendCraft.inventory.item;
import com.tesselex.fiendCraft.listeners.eventListener;
import com.tesselex.fiendCraft.stats.gameMode;
import com.tesselex.fiendCraft.stats.xp;
import com.tesselex.fiendCraft.stats.healthDamage;
import com.tesselex.fiendCraft.storage.fiendData;
import com.tesselex.fiendCraft.teleportation.*;
import com.tesselex.fiendCraft.fiendTools.itemGenerator;
import com.tesselex.fiendCraft.tools.portable;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

public final class fiendCraft extends JavaPlugin {

    private HashMap<UUID, Location> homes;
    private List<UUID> queueTP;
    public static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;
    private static fiendData data = null;
    private static itemGenerator itemGen = null;

    private static banking bankingModule =null;
    private static gameMode gamemodeModule = null;
    private static healthDamage healthDamageModule = null;
    private static home homeModule = null;
    private static income incomeModule = null;
    private static item itemModule = null;
    private static pay payModule = null;
    private static warp warpModule = null;
    private static xp xpModule = null;
    private static portable portableModule = null;

    private Economy eco = new ecoBase();
    //private static final MicroLib mlib = new MicroLib();

    private Object eventListener;


    //Enable
    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        log.info("FiendCraft is starting up...");
        this.queueTP = new ArrayList<>();
        this.data = new fiendData(this);
        this.itemGen = new itemGenerator(this);
        this.eventListener = eventListener;
        //SQL Initialization
        //log.info("SQL Initializing...");

        //Economy Init
        log.info("Economy Initializing...");
        if (!setupEconomy() ) {
            log.severe("[%s] - Disabled due to no Vault dependency found!" + getDescription().getName());
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        log.info("Economy hooked into Vault!");

        //Perms Init
        //log.info("Permissions Initializing...");
        //setupPermissions();

        //Chat Init
        //log.info("Chat Initializing...");
        //setupChat();

        //Data Folder Creation
        if(!getDataFolder().exists()) {getDataFolder().mkdir(); log.info("Creating Data Folder...");}

        //Commands
        log.info("Registering Commands...");

        bankingModule = new banking(this);
        gamemodeModule =new gameMode(this);
        healthDamageModule = new healthDamage(this);
        homeModule = new home(this);
        incomeModule = new income(this);
        itemModule = new item(this);
        payModule = new pay(this);
        //spawnModule = new spawn(this);
        warpModule = new warp(this);
        xpModule = new xp(this);
        portableModule = new portable(this);

        /*Bukkit.getPluginCommand("workbench").setExecutor(this);
        Bukkit.getPluginCommand("wb").setExecutor(this);
        Bukkit.getPluginCommand("furnace").setExecutor(this);
        Bukkit.getPluginCommand("smelt").setExecutor(this);*/

        log.info("Registering Events...");
        getServer().getPluginManager().registerEvents(new eventListener(), this);

        log.info("FiendCraft is enabled!");

    }

    //Disable
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
        log.info("FiendCraft is Shutting Down.");

        //log.info("Disconnecting SQL...");

        log.info("FiendCraft Succesfully Closed.");
    }

    //Economy, Perms, and Chat Setup
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null){log.info("Vault not Found"); return false;}

        econ = new ecoBase();
        getServer().getServicesManager().register(Economy.class, eco, getServer().getPluginManager().getPlugin("Vault"), ServicePriority.Normal);

        return econ != null;}

    private boolean setupChat() {RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;}

    private boolean setupPermissions() {RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;}

    //Public Vars
    public static Economy getEconomy() {return econ;}
    public static Permission getPermissions() {return perms;}
    public static Chat getChat() {return chat;}
    public static fiendData getFCData() {return data;}
    public static itemGenerator getItemGen() {return itemGen;}
    public static income getIncome() {return incomeModule;}
    public eventListener getEvents() {return (com.tesselex.fiendCraft.listeners.eventListener) eventListener;}
    //public static MicroLib getMlib() {return mlib;}

    //Home Commands
    public void setHome(Player player) {UUID id = player.getUniqueId();
        this.data.setHomeByUUID(id, player);}
    public void goHome(Player player) {UUID id = player.getUniqueId();
        player.teleportAsync(this.data.getHomeByUUID(id));}
    public boolean hasHome(UUID id) {return this.data.hasHomeByUUID(id);}


    //TP Queue
    public void addQ(UUID id){this.queueTP.add(id);}
    public void remQ(UUID id){this.queueTP.remove(id);}
    public boolean isInQ(UUID id){return this.queueTP.contains(id);}
}
