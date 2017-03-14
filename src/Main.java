import implementations.ParallelPortScanner;
import models.Port;

import java.net.UnknownHostException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        long startTime = System.currentTimeMillis();

        List<Port> openPorts = new ParallelPortScanner() // new ConsistentPortScanner()
                .setHost("ntp2.stratum1.ru")
                .setOptions(Port.TCP | Port.UDP)
                .setStartPort(50)
                .setFinalPort(130)
                .setTimeout(500)
                .setLogMode(true)
                .scan();

        System.out.println("\nOpened ports:");
        openPorts.forEach(port -> System.out.println(port.toString()));

        System.out.println("Complete in: " + (System.currentTimeMillis() - startTime - 3000) / 1000 + "s.");
    }

}

