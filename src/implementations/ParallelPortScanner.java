package implementations;

import models.Port;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ParallelPortScanner extends ConsistentPortScanner
{
    @Override public List<Port> scan()
    {
        int[] ports = new int[finalPort - startPort + 1];
        for (int i = 0; i < ports.length ; i++)
            ports[i] = startPort + i;

        Arrays.stream(ports).parallel().forEach(port -> {
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
        });

        try {
            Thread.sleep(timeout * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return openedPorts;
    }
}
