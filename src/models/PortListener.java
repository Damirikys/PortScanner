package models;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PortListener
{
    InetAddress host;
    int port;
    int timeout;

    public PortListener(InetAddress host, int port, int timeout) throws IOException
    {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public void send(byte[] array, Callback callback) throws IOException {
        new Thread(() -> {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), timeout);
                DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                PushbackInputStream bis = new PushbackInputStream(socket.getInputStream(), 1);

                outToServer.write(array);
                outToServer.flush();

                Thread.sleep(500);

                StringBuffer buffer = new StringBuffer();
                while (bis.available() > 0)
                    buffer.append((char) bis.read());

                socket.close();
                callback.onSuccess(buffer.toString());
            } catch (IOException | InterruptedException ignored) {}
        }).start();
    }

    public interface Callback
    {
        void onSuccess(String response);
    }
}
