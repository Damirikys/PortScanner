package implementations;

import models.Port;
import network.TCPPacketSender;
import network.UDPPacketProvider;
import prototype.PortScannerAdapter;
import models.TCPStack;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import static models.Port.TCP;
import static models.Port.UDP;

public class ConsistentPortScanner extends PortScannerAdapter
{
    List<Port> openedPorts = new ArrayList<>();

    public List<Port> scan()
    {
        if (logMode) System.out.println("Process... ");
        for (int port = startPort; port <= finalPort ; port++)
            portIdentify(port);

        return openedPorts;
    }

    protected void portIdentify(int port)
    {
        if (options == 1)
            provideTCP(port);
        else if (options == 2)
            provideUDP(port);
        else if (options == 3)
        {
            provideTCP(port);
            provideUDP(port);
        }
    }

    protected void provideTCP(int port)
    {
        try {
            checkTCP(port);
            if (logMode) System.out.println("TCP | " + port +" is open");
            addPort(
                    new Port(port)
                        .setType(TCP)
                        .setService(
                                determineTCPService(
                                        new TCPPacketSender(inetAddress, port, timeout)
                                                .send(new byte[128])
                                )
                        )
            );
        } catch (IOException e) {
            if (logMode) System.out.println("TCP | " + port +" ...");
        }
    }

    protected void provideUDP(int port)
    {
        UDPPacketProvider.getData((bytes, service) -> {
            try {
                checkUDP(port, bytes);
                if (logMode) System.out.println("UDP | " + port +" is open");
                addPort(new Port(port).setService(service).setType(UDP));
                return true;
            } catch (IOException e) {
                if (logMode) System.out.println("UDP | " + port +" ... ("+ service +")");
                return false;
            }
        });
    }

    protected void checkTCP(int port) throws IOException
    {
        InetSocketAddress isa = new InetSocketAddress(inetAddress, port);
        Socket clientSocket = new Socket();
        clientSocket.connect(isa,timeout);
        clientSocket.close();
    }

    protected void checkUDP(int port, byte[] bytes) throws IOException
    {
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(
                bytes, bytes.length, inetAddress, port);
        DatagramPacket received = new DatagramPacket(bytes, bytes.length);
        socket.setSoTimeout(timeout);
        socket.connect(inetAddress, port);

        socket.send(packet);
        socket.receive(received);
        socket.close();
    }

    protected void addPort(Port port)
    {
        openedPorts.add(port);
    }

    private String determineTCPService(String response)
    {
        response = response.toLowerCase();
        for (TCPStack type : TCPStack.values())
        {
            if (response.contains(type.name()))
                return type.name();

            String[] stopWords = TCPStack.wordMap.get(type);
            if (stopWords == null) continue;
            for (String stopWord : stopWords)
                if (response.contains(stopWord))
                    return type.name();
        }

        return "Unknown";
    }
}
