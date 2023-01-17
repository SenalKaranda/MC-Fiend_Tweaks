package com.tesselex.fiendCraft.inventory;

import com.tesselex.fiendCraft.fiendCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;

public class item implements CommandExecutor {

    private fiendCraft plugin = null;
    public item(fiendCraft plugin) {

        this.plugin = plugin;
        Bukkit.getPluginCommand("item").setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            String iInput = args[0].toUpperCase(Locale.ROOT);
            Boolean iBool = false;
            if(Material.getMaterial(iInput) == null) {
                return false;
            }
            ItemStack iStack = new ItemStack(Objects.requireNonNull(Material.getMaterial(iInput)));
            switch(args.length) {
                case 0:
                    player.sendMessage(ChatColor.RED + "Needs more arguments");
                    break;
                case 1:
                    player.getInventory().addItem(iStack.asQuantity(1));
                    return true;
                case 2:
                    player.getInventory().addItem(iStack.asQuantity(Integer.parseInt(args[1])));
                    return true;
            }
        }
        return false;
    }
}
