package org.lushplugins.lushstorage.test;

import org.lushplugins.lushstorage.StorageManager;
import org.lushplugins.lushstorage.annotation.StorageClass;
import org.lushplugins.lushstorage.annotation.StorageMethod;

import java.util.UUID;

@StorageClass
public interface Storage {

    @StorageMethod(storageClassName = "org.lushplugins.lushstorage.StorageManager")
    String loadUser(UUID uuid);

    @StorageMethod(storageClassName = "org.lushplugins.lushstorage.StorageManager")
    void saveUser(UUID uuid, String username);
}
