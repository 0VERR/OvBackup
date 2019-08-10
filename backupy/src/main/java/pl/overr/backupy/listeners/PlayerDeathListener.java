package pl.overr.backupy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import pl.overr.backupy.backup.Backup;
import pl.overr.backupy.data.BackupsData;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


public class PlayerDeathListener implements Listener {

    private final BackupsData backupsData;

    public PlayerDeathListener(BackupsData backupsData){
        this.backupsData = backupsData;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        UUID playerUUID = event.getEntity().getUniqueId();
        ItemStack[] playerEqipment = event.getEntity().getInventory().getContents();
        ItemStack[] playerGive = Arrays.stream(playerEqipment)
                .filter(Objects::nonNull)
                .toArray(ItemStack[]::new);

        ItemStack[] playerArmor = event.getEntity().getInventory().getArmorContents();
        ItemStack[] playerArmorGive = Arrays.stream(playerArmor)
                .filter(Objects::nonNull)
                .toArray(ItemStack[]::new);

        long time = System.currentTimeMillis();
        backupsData.addBackup(playerUUID, new Backup( playerGive, playerArmorGive, time, playerUUID));

    }
}
