package views;

import adapters.ViewModel;

public abstract class View {
    private volatile boolean stopped;
    private ViewModel viewM;

    public View(ViewModel viewM) {
        this.stopped = false;
        this.viewM = viewM;
    }

    protected abstract void start();
    protected abstract void end();
    protected abstract void runLoop();

    public void runView () {
        this.start();

        while (!stopped) {
            this.runLoop();
        }

        this.end();
    }

    public void stop () {
        this.stopped = true;
    }
}
