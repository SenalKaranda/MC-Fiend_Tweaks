package com.tesselex.fiendCraft.fiendTools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
@SuppressWarnings({"NullableProblems", "unused", "FieldMayBeFinal", "BooleanMethodIsAlwaysInverted", "RedundantSuppression", "unchecked", "null"})
public class Logging {
    private static Player player;
    private static ChatColor mColor;
    private static ChatColor msgColor;
    private static boolean success;
    private static String message;

    public static void msgSuccessFail(Player player, ChatColor mColor, String module, boolean success) {
        Logging.player = player;
        Logging.mColor = mColor;
        Logging.success = success;
        if (success) {
            player.sendMessage(mColor + module + ": " + ChatColor.GREEN + "Command Success!!");
        }
        else player.sendMessage(mColor + module + ": " + ChatColor.RED + "Command failed!");
    }

    public static boolean msgModuleMsg(Player player, ChatColor mColor, boolean success, String message) {
        Logging.player = player;
        Logging.mColor = mColor;
        Logging.success = success;
        Logging.message = message;

        player.sendMessage(msgColor + message);

        return Logging.success;
        
    }


}
