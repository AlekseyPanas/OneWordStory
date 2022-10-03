import views.TcpView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO: Delete this sample test code
        TcpView server = new TcpView(42069);
        server.runView();
    }
}
