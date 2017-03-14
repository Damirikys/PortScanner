package implementations;

import models.Port;
import models.PortListener;
import prototype.PortScannerAdapter;
import prototype.Protocol;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;
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
            checkTCP(port);
        else if (options == 2)
            checkUDP(port);
        else if (options == 3)
        {
            checkTCP(port);
            checkUDP(port);
        }
    }

    protected void checkTCP(int port)
    {
        try {
            InetSocketAddress isa = new InetSocketAddress(InetAddress.getByName(host), port);
            Socket clientSocket = new Socket();
            clientSocket.connect(isa,timeout);
            if (logMode) System.out.println("TCP | " + port +" is open");
            clientSocket.close();
            portIsOpen(port);
        } catch (IOException ioe) {
            if (logMode) System.out.println("TCP | " + port + " ...");
        }
    }

    protected void checkUDP(int port)
    {
        byte [] bytes = new byte[2400];

        try {
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);
            socket.setSoTimeout(timeout);
            socket.connect(InetAddress.getByName(host), port);
            socket.send(packet);

            packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);

            //socket.receive(received);
            socket.close();
            if (logMode) System.out.println("UDP | " + port + " is open");
            openedPorts.add(new Port(port).setOption(UDP));
        } catch (PortUnreachableException e) {
            if (logMode) System.out.println("UDP | " + port + " is closed");
        } catch (SocketTimeoutException e) {
            if (logMode) System.out.println("UDP | " + port + " is open|filtered");
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    protected void portIsOpen(int port) {
        try {
            new PortListener(InetAddress.getByName(getHost()), port, timeout).send(new byte[10], response ->
                    openedPorts.add(
                            new Port(port)
                                    .setProtocol(determineProtocol(response))
                                    .setOption(TCP)
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Protocol determineProtocol(String response)
    {
        response = response.toLowerCase();
        System.out.println(response);
        for (Protocol protocol: Protocol.values())
            if (response.contains(protocol.name()))
                return protocol;

        return Protocol.unknown;
    }
}
