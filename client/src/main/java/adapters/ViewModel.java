package adapters;

// Layer: INTERFACE ADAPTERS

public class ViewModel {
    private boolean lock;

    public ViewModel () {
        lock = false;
    }

    public void lockViewModel () {
        lock = true;
    }

    public void unlockViewModel () {
        lock = false;
    }
}
