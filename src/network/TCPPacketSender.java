package network;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPPacketSender
{
    InetAddress host;
    int port;
    int timeout;

    public TCPPacketSender(InetAddress host, int port, int timeout)
    {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public String send(byte[] array)
    {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream(), 1);

            outToServer.write(array);
            outToServer.flush();

            Thread.sleep(timeout);

            StringBuffer buffer = new StringBuffer();
            while (bis.available() > 0)
                buffer.append((char) bis.read());

            socket.close();
            return buffer.toString();
        } catch (IOException | InterruptedException ignored) {
            return "";
        }
    }
}
