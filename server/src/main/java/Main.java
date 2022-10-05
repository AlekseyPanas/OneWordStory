import views.TcpView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        TcpView server = new TcpView(42069);
        server.runView();
    }
}
