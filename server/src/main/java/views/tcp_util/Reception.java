package views.tcp_util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reception implements Runnable {

    private final Socket clientSocket;
    private final BufferedReader incomingDataReader;
    private LinkedList<String> receivedData;
    // prevent data retrieval when the receive thread is assigning a new value.
    // For thread safety.
    private final ReentrantLock lockQ;

    private boolean isReceiving;

    /**
     * @param clientSocket provide socket associated with a connected client
     * @throws IOException when given socket is closed or not connected
     */
    public Reception (Socket clientSocket) throws IOException {
        lockQ = new ReentrantLock();
        isReceiving = true;
        receivedData = new LinkedList<>();
        this.clientSocket = clientSocket;

        // Buffered reader allows you to grab data from the stream in defined sized chunks
        this.incomingDataReader = new BufferedReader(
                // Reads byte stream and turns it to a character stream
                new InputStreamReader(
                        // Byte stream of incoming data
                        this.clientSocket.getInputStream()
                )
        );

        // Starts reception thread.
        (new Thread(this)).start();
    }

    /**
     * Get and remove the next chunk in receivedData queue
     * @return the removed chunk, or empty string if queue is empty
     */
    public String getNextChunk () {
        if (hasReceivedData()) {
            String dataChunk;

            // Waits until lock is free, then locks it to make edit
            lockQ.lock();
            try {
                // Pop the next Datachunk in queeu
                dataChunk = receivedData.remove();
            } finally {
                lockQ.unlock();
            }

            return dataChunk;

        } return "";
    }

    /**
     *
     * @return if there is any data received waiting to be read
     */
    public boolean hasReceivedData () {
        boolean empty;

        // Waits until lock is free, then locks it to make edit
        lockQ.lock();
        try {
            // Add newly received chunk to the queue
            empty = receivedData.isEmpty();
        } finally {
            lockQ.unlock();
        }

        return empty;
    }

    public boolean isReceiving() {
        return isReceiving;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Read until \n or \r character and add chunk to received data.
                String dataChunk = incomingDataReader.readLine();

                // Waits until lock is free, then locks it to make edit
                lockQ.lock();
                try {
                    // Add newly received chunk to the queue
                    receivedData.add(dataChunk);

                    // Print the content.
                    System.out.println("R: " + clientSocket.getInetAddress() + " has sent: " + dataChunk);
                } finally {
                    lockQ.unlock();
                }

            }

            // If some error occurs, socket likely closed so terminate reception
            catch (IOException ignored) {
                break;
            }
        }
        // Unlocks just in case
        lockQ.unlock();

        // Socket has closed so no more reception is needed
        isReceiving = false;

        // Debug message that this connection has been closed
        System.out.println("Reception from " + clientSocket.getInetAddress() + " has closed");
    }
}
