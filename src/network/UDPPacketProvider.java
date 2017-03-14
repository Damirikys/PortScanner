package network;

import models.UDPStack;

import java.io.*;

public class UDPPacketProvider
{
    private static final String path = "packets/";
    private static final String extension = ".bin";

    public static void getData(UDPPacketProvider.Callback callback)
    {
        for (UDPStack service : UDPStack.values())
        {
            File file = new File(path + service + extension);
            try(BufferedInputStream is = new BufferedInputStream(new FileInputStream(file)))
            {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int data;
                while ((data = is.read()) != -1)
                    outputStream.write(data);

                outputStream.close();
                if (callback.yieldBinary(outputStream.toByteArray(), service.name()))
                    break;
            }
            catch (IOException ignored) {}
        }
    }

    public interface Callback
    {
        boolean yieldBinary(byte[] bytes, String service);
    }
}
