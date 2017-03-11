import implementations.ParallelPortScanner;
import models.Port;

import java.util.List;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        long startTime = System.currentTimeMillis();

        List<Port> openPorts = new ParallelPortScanner()
                .setHost("193.109.246.6")
                .setStartPort(1)
                .setFinalPort(500)
                .setTimeout(200)
                .setLogMode(true)
                .scan();

        System.out.println("\nOpened ports:");
        openPorts.forEach(port -> System.out.println(port.toString()));

        System.out.println("Complete in: " + (System.currentTimeMillis() - startTime - 3000) / 1000 + "s.");
    }
}
