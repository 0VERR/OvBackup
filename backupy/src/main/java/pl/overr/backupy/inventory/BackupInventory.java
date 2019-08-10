package pl.overr.backupy.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.overr.backupy.backup.Backup;
import pl.overr.backupy.data.BackupsData;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BackupInventory {

    private final BackupsData backupsData;

    public BackupInventory(BackupsData backupsData){
        this.backupsData = backupsData;
    }

    public Inventory createInventory(Player player){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'o' HH:mm:ss z");
        Inventory playerInventory = Bukkit.createInventory(null, 36, "Backup");
        for (Backup playerBackup : backupsData.getBackups(player.getUniqueId())){
            ItemStack itemStack = new ItemStack(Material.GRASS);
            Date date = new Date(playerBackup.getTime());
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(Arrays.asList(String.valueOf(playerBackup.getOwner()), String.valueOf(date)));
            itemMeta.setDisplayName(String.valueOf(playerBackup.getTime()));
            itemStack.setItemMeta(itemMeta);
            playerInventory.addItem(itemStack);
        }
        return playerInventory;

    }
}
