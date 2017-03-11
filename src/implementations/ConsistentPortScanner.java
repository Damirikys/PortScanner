package implementations;

import models.Port;
import models.PortListener;
import prototype.PortScannerAdapter;
import prototype.Protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConsistentPortScanner extends PortScannerAdapter
{
    List<Port> openedPorts = new ArrayList<>();

    public List<Port> scan()
    {
        if (logMode) System.out.println("Process... ");
        for (int port = startPort; port <= finalPort ; port++) {
            try {
                InetSocketAddress isa = new InetSocketAddress(InetAddress.getByName(host), port);
                Socket clientSocket = new Socket();
                clientSocket.connect(isa,timeout);
                if (logMode) System.out.println(port +" is opened");
                clientSocket.close();
                portIsOpen(port);
            } catch (IOException ioe) {
                if (logMode) System.out.println(port + " ...");
            }
        }

        try {
            Thread.sleep(timeout * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return openedPorts;
    }

    protected void portIsOpen(int port) {
        try {
            new PortListener(InetAddress.getByName(getHost()), port, timeout).send(new byte[10], response ->
                    openedPorts.add(new Port(port).setProtocol(determineProtocol(response))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Protocol determineProtocol(String response)
    {
        response = response.toLowerCase();
        for (Protocol protocol: Protocol.values())
            if (response.contains(protocol.name()))
                return protocol;

        return Protocol.unknown;
    }
}
