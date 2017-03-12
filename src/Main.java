import implementations.ParallelPortScanner;
import models.Port;

import java.util.List;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        long startTime = System.currentTimeMillis();

        List<Port> openPorts = new ParallelPortScanner() // new ConsistentPortScanner()
                .setHost("46.101.165.166")
                .setOptions(Port.TCP)
                //.setStartPort(60)
                //.setFinalPort(400)
                //.setTimeout(50)
                .setLogMode(true)
                .scan();


        System.out.println("\nOpened ports:");
        openPorts.forEach(port -> System.out.println(port.toString()));

        System.out.println("Complete in: " + (System.currentTimeMillis() - startTime - 3000) / 1000 + "s.");
    }
}
