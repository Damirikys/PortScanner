import implementations.ConsistentPortScanner;
import implementations.ParallelPortScanner;
import models.Port;

import java.util.List;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        long startTime = System.currentTimeMillis();

        List<Port> openPorts = new ConsistentPortScanner() // new ConsistentPortScanner()
                .setHost("88.147.254.232")
                .setOptions(Port.UDP)
                .setStartPort(123)
                .setFinalPort(123)
                .setTimeout(5000)
                .setLogMode(true)
                .scan();


        System.out.println("\nOpened ports:");
        openPorts.forEach(port -> System.out.println(port.toString()));

        System.out.println("Complete in: " + (System.currentTimeMillis() - startTime - 3000) / 1000 + "s.");
    }
}

