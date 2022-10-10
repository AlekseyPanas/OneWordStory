package drivers.views;

public interface View {
    /**
     * Starts and runs the view taking up the main thread until the view quits.
     * Calls use cases and interacts with view model, etc
     */
    void runView ();
}
