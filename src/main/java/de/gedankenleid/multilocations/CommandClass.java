package de.gedankenleid.multilocations;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class CommandClass implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3)
    {
        sender.sendMessage("§3MultiLocations von §eGedankenleid§8: §3Version: §e" + Main.instance.getDescription().getVersion());
        return true;
    }

}
