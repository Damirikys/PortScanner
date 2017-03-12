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
        if (logMode) System.out.println("Process... ");

        int[] ports = new int[finalPort - startPort + 1];
        for (int i = 0; i < ports.length ; i++)
            ports[i] = startPort + i;

        Arrays.stream(ports)
                .parallel()
                .forEach(this::portIdentify);

        return openedPorts;
    }
}
