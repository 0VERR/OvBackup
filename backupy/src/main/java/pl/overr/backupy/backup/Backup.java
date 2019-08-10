package pl.overr.backupy.backup;

import org.bukkit.inventory.ItemStack;

import java.util.UUID;


public class Backup implements Comparable<Backup>{
    private ItemStack[] itemStack;

    public ItemStack[] getItemStack() {
        return itemStack;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public Long getTime() {
        return time;
    }

    private ItemStack[] armor;
    private Long time;

    public UUID getOwner() {
        return owner;
    }

    private UUID owner;

    public Backup(ItemStack[] itemStack, ItemStack[] armor, Long time, UUID owner){
        this.itemStack = itemStack;
        this.armor = armor;
        this.time = time;
        this.owner = owner;
    }

    @Override
    public int compareTo(Backup o) {
        return -(getTime().compareTo(o.time));
    }
}
