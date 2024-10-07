package org.lushplugins.lushstorage.test;

import org.lushplugins.lushstorage.annotation.StorageClass;
import org.lushplugins.lushstorage.annotation.StorageMethod;

import java.util.UUID;

@StorageClass
public interface Storage {

    @StorageMethod
    String loadUser(UUID uuid);

    @StorageMethod
    void saveUser(UUID uuid, String username);
}
