package org.lushplugins.lushstorage.test;

import org.lushplugins.lushstorage.StorageManager;
import org.lushplugins.lushstorage.annotation.StorageMethod;

import java.util.UUID;

public class TestClass {

    public void test() {
        StorageManager manager = new StorageManager();
//        manager.loadUser(UUID.randomUUID());
    }

    @StorageMethod(storageClassName = "org.lushplugins.lushstorage.StorageManager")
    public String testMethod(UUID uuid) {
        return "";
    }
}
