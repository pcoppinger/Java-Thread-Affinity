package net.openhft.affinity.test;

import net.openhft.affinity.AffinityLock;

public class BindingTest {

    public static void main(String[] args) {
        String cpuToBindTo = args.length > 0 ? args[0] : "last";
        System.out.println("Binding to " + cpuToBindTo);
        try (AffinityLock affinityLock = AffinityLock.acquireLock(cpuToBindTo)) {
            while (!Thread.currentThread().isInterrupted()) {
                // Do nothing, just busy wait
            }
        }
    }
}
