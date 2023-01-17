package com.tesselex.fiendCraft.storage;

import com.tesselex.fiendCraft.fiendCraft;
import mc.alessandroch.statsapi.Table;
import mc.alessandroch.statsapi.TableType;
import me.lokka30.microlib.files.YamlConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;



public class fiendData {

    private fiendCraft plugin;
    public fiendData(fiendCraft plugin) {

        this.plugin = plugin;

        playerHomes.addCustomType("uuid", "uuid");
        playerHomes.addTextType("name");
        playerHomes.addCustomType("location", "location");
        playerHomes.addCustomType("world", "world");
        playerHomes.inizializeTable(TableType.UUIDIncluded);

        playerWarps.addTextType("name");
        playerWarps.addCustomType("location", "location");
        playerWarps.addCustomType("world", "world");
        playerWarps.inizializeTable(TableType.STARTAsEmpty);
        
        ecoItemValues.addTextType("itemName");
        ecoItemValues.addFloatType("valueSingle");
        ecoItemValues.addFloatType("valueStack");
        ecoItemValues.inizializeTable(TableType.STARTAsEmpty);
        
        playerStats.addIntType("kills");
        playerStats.addIntType("deaths");
        playerStats.inizializeTable(TableType.UUIDIncluded);
        
        playerMoney.addFloatType("walletMoney");
        playerMoney.addFloatType("bankMoney");
        playerMoney.addFloatType("microCurrency");
        playerMoney.inizializeTable(TableType.UUIDIncluded);
        
        playerDatabase.addTextType("name");
        playerDatabase.addTextType("clientBrand");
        playerDatabase.addTextType("ip");
        playerDatabase.addTextType("protocol");
        playerDatabase.inizializeTable(TableType.UUIDIncluded);
    }

    Table playerHomes = new Table("playerHomes");
    Table playerWarps = new Table("playerWarps");
    Table ecoItemValues = new Table("ecoItemValues");
    Table playerStats = new Table("playerStats");
    Table playerMoney = new Table("playerMoney");
    Table playerDatabase = new Table("playerLog");

    //Homes | UUID - LOCATION
    public void setHomeByUUID(UUID id, Player player) {
        playerHomes.setObject(player, "uuid", player.getLocation());}
    public Location getHomeByUUID(UUID id) {return (Location) playerHomes.getObject(Bukkit.getPlayer(id), "location");}
    public boolean hasHomeByUUID(UUID id) {return playerHomes.rowExist(Bukkit.getPlayer(id));}

    //Warps | UUID - LOCATION
    public void setWarpByName(String warpName, Player player) {
        playerWarps.setText(warpName, "name", warpName);
        playerWarps.setObject(warpName, "location", player.getLocation());
        playerWarps.setObject(warpName, "world", player.getWorld());
    }
    public HashMap<Location, World> getWarpByName(String name) {
        HashMap<Location, World> warp = new HashMap<Location, World>();
        warp.put(playerWarps.);
        return warp;}

    //Global Item Values | Item Name(MC Format) - Item Value
    public void setEcoItemValues(String itemFormalName, Float value) {ecoItemValues.getConfig().set(itemFormalName, value);}
    public Float getEcoItemValues(String itemFormalName) {return Float.parseFloat(ecoItemValues.getConfig().getString(itemFormalName));}

    //Player Stats | UUID & StatName - Stat Value
    public void setPlayerStat(UUID id, String statFormalName, Float statValue) {playerStats.getConfig().set(statFormalName + "_" + id.toString(), statValue);}
    public Float getPlayerStatByUUID(UUID id, String statFormalName) {
        return (Float) playerStats.getConfig().get(statFormalName + "_" + id.toString());}

    //Player Money | UUID - Money
    public void setPlayerMoneyByUUID(UUID id, Float money) {playerMoney.getConfig().set("wallet_" + id.toString(), money);}
    public Float getPlayerMoneyByUUID(UUID id) {return (Float) playerMoney.getConfig().get("wallet_" + id.toString());}


    //Player Bank(Money) | UUID - Money
    public void setPlayerBankMoneyByUUID(UUID id, Float money) {playerMoney.getConfig().set("bank_" + id.toString(), money);}
    public Float getPlayerBankMoneyByUUID(UUID id) {return (Float) playerMoney.getConfig().get("bank_" + id.toString());}

    //Player XP | UUID - XP
    public void setPlayerXPByUUID(UUID id, Float xp) {playerXP.getConfig().set("wallet_" + id.toString(), xp);}
    public Float getPlayerXPByUUID(UUID id) {return (Float) playerXP.getConfig().get("wallet_" + id.toString());}

    //Player Bank(XP) | UUID - XP
    public void setPlayerBankXPByUUID(UUID id, Float xp) {playerXP.getConfig().set("bank_" + id.toString(), xp);}
    public Float getPlayerBankXPByUUID(UUID id) {return (Float) playerXP.getConfig().get("bank_" + id.toString());}

    //Player MicroCurrency | UUID - MicroCurrency
    public void setPlayerMicroCurrencyByUUID(UUID id, Float mcurrency) {playerMicroCurrency.getConfig().set(id.toString(), mcurrency);}
    public Float getPlayerMicroCurrencyByUUID(UUID id) {return (Float) playerMicroCurrency.getConfig().get(id.toString());}

    //Checks for New Player
    public boolean newPlayerCheck(UUID id) {return !playerMoney.getConfig().contains(id.toString());}

    //Adds new player to playerDatabase
    public void addToPlayerDB(Player player) {

        //Collect Data for Statistics
        playerDatabase.getConfig().set(player.getName(), player.getUniqueId().toString());//UUID
        playerDatabase.getConfig().set(player.getName(), player.getClientBrandName());//Client Brand(Xbox or Java?)
        playerDatabase.getConfig().set(player.getName(), player.getAddress().toString());//IP
        playerDatabase.getConfig().set(player.getName(), player.getProtocolVersion());//Protocol
    }

    /*
    public Json getValByUUID(UUID id) {
        Player player = (Player) sender;
        UUID id = player.getConfig().getUniqueId();
        homes.getConfig().set(id.toString(), );
        return homes;
    }*/

}
