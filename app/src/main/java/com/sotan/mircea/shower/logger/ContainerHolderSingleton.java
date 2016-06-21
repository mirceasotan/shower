package com.sotan.mircea.shower.logger;

import com.google.android.gms.tagmanager.ContainerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirceasotan
 */
public class ContainerHolderSingleton {
    private static List<ContainerHolder> containerHolderList = new ArrayList<>();

    /**
     * Utility class; don't instantiate.
     */
    private ContainerHolderSingleton() {
    }

    public static List<ContainerHolder> getContainerHolderList() {
        return containerHolderList;
    }

    public static void add(ContainerHolder h) {
        containerHolderList.add(h);
    }

    public static ContainerHolder getContainerWithId(String id) {
        if (id == null) {
            return null;
        }

        for (ContainerHolder containerHolder : containerHolderList) {
            if (containerHolder.getContainer() != null && id.equals(containerHolder.getContainer().getContainerId())) {
                return containerHolder;
            }
        }

        return null;
    }
}
