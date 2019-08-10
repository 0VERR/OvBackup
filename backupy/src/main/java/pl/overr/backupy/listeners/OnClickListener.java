package pl.overr.backupy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.overr.backupy.backup.Backup;
import pl.overr.backupy.data.BackupsData;
import java.util.UUID;

public class OnClickListener implements Listener {
    private final BackupsData backupsData;

    public OnClickListener(BackupsData backupsData) {
        this.backupsData = backupsData;
    }

    @EventHandler
    public void onClickListner(InventoryClickEvent event){
        if (!event.getInventory().getName().equalsIgnoreCase("Backup")){
            return;
        }

        if (event.getCurrentItem() == null){
            return;
        }

        event.setCancelled(true);

        ItemStack clickItemStack = event.getCurrentItem();
        if (clickItemStack.getType() != Material.GRASS){
            return;
        }
        String utilUUID = clickItemStack.getItemMeta().getLore().toString().substring(1,37);
        UUID uuid = UUID.fromString(utilUUID);
        Player backupTarget = Bukkit.getPlayer(uuid);
        PlayerInventory playerInventory = backupTarget.getInventory();
        String time = clickItemStack.getItemMeta().getDisplayName();


        for (Backup backup : backupsData.getBackups(uuid)){
            if (String.valueOf(backup.getTime()).equalsIgnoreCase(time)) {
                ItemStack[] itemStacks = backup.getItemStack();
                ItemStack[] armor = backup.getArmor();

                playerInventory.addItem(itemStacks);
                playerInventory.setArmorContents(armor);
            }
        }
    }
}
