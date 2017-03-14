import implementations.ParallelPortScanner;
import models.Port;

import java.net.UnknownHostException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws UnknownHostException {
        long startTime = System.currentTimeMillis();

        List<Port> openPorts = new ParallelPortScanner() // new ConsistentPortScanner()
                .setHost("hudeemvmeste.kz")
                .setOptions(Port.TCP)
                .setStartPort(1)
                .setFinalPort(500)
                .setTimeout(100)
                .setLogMode(true)
                .scan();

        System.out.println("\nOpened ports:");
        openPorts.forEach(port -> System.out.println(port.toString()));

        System.out.println("Complete in: " + (System.currentTimeMillis() - startTime) / 1000 + "s.");
    }

}

