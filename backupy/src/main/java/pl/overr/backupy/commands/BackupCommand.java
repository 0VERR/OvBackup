package pl.overr.backupy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.overr.backupy.inventory.BackupInventory;

public class BackupCommand implements CommandExecutor {

    private final BackupInventory backupInventory;

    public BackupCommand(BackupInventory backupInventory){
        this.backupInventory = backupInventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!sender.isOp()){
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            sender.sendMessage(ChatColor.RED + "Blad! Nie ma takiego Gracza!");
            return true;
        }

        Player commandSender = (Player) sender;
        commandSender.openInventory(backupInventory.createInventory(target));
        return true;
    }
}
