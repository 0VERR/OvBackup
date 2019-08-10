package pl.overr.backupy.data;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import pl.overr.backupy.backup.Backup;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class BackupsData {
    private TreeMultimap<UUID, Backup> backups = TreeMultimap.create();


    public void addBackup(UUID uuid, Backup backup){
        backups.put(uuid,backup);
    }

    public Collection<Backup> getBackups(UUID uuid){
        return Collections.unmodifiableCollection(this.backups.get(uuid));
    }
}
