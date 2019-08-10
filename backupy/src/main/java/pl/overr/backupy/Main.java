package pl.overr.backupy;

import org.bukkit.plugin.java.JavaPlugin;
import pl.overr.backupy.commands.BackupCommand;
import pl.overr.backupy.data.BackupsData;
import pl.overr.backupy.inventory.BackupInventory;
import pl.overr.backupy.listeners.OnClickListener;
import pl.overr.backupy.listeners.PlayerDeathListener;

public class Main extends JavaPlugin {

    private BackupsData backupsData = new BackupsData();
    private BackupInventory backupInventory = new BackupInventory(backupsData);


    @Override
    public void onEnable() {
        getCommand("backup").setExecutor(new BackupCommand(backupInventory));
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(backupsData), this);
        getServer().getPluginManager().registerEvents(new OnClickListener(backupsData), this);
    }

}
