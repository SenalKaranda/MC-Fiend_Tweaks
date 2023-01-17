package com.tesselex.fiendCraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class commandManagement implements CommandExecutor {

    private static ArrayList<Command> commands = new ArrayList();
    private final fiendCraft plugin;
    public commandManagement(fiendCraft plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    public static ArrayList<Command> getCommandsList() {
        return commands;
    }
}
