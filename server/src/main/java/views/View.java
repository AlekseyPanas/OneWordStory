package views;

public abstract class View {
    private volatile boolean stopped;

    public View() {
        stopped = false;
    }

    protected abstract void start();
    protected abstract void end();
    protected abstract void runLoop();

    public void runView () {
        start();

        while (!stopped) {
            runLoop();
        }

        end();
    }

    public void stop () {
        this.stopped = true;
    }
}
