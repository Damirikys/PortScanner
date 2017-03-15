import implementations.ParallelPortScanner;
import models.Port;

import java.net.UnknownHostException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws UnknownHostException
    {
        long startTime = System.currentTimeMillis();

        List<Port> openPorts = new ParallelPortScanner() // new ConsistentPortScanner()
                .setThreadCount(15)
                .setHost("google.com")
//                .setOptions(Port.TCP | Port.UDP)
//                .setStartPort(1)
//                .setFinalPort(123)
//                .setTimeout(400)
                .setLogMode(true)
                .scan();

        System.out.println("\nOpened ports:");
        openPorts.forEach(port -> System.out.println(port.toString()));

        System.out.println("Complete in: " + (System.currentTimeMillis() - startTime) / 1000 + "s.");
    }
}

