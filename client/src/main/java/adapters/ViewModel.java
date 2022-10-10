package adapters;

// Layer: INTERFACE ADAPTERS

import java.util.concurrent.locks.ReentrantLock;

public class ViewModel {
    private ReentrantLock lock;

    public ViewModel () {
        lock = new ReentrantLock();
    }
}
