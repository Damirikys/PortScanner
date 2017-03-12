package models;

import prototype.Protocol;

public class Port
{
    public static final int TCP = 1;
    public static final int UDP = 2;

    private int option;
    private final int VALUE;
    private Protocol protocol;

    public Port(int value)
    {
        this.VALUE = value;
        this.protocol = Protocol.unknown;
        this.option = TCP;
    }

    public Port setProtocol(Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public Port setOption(int type)
    {
        this.option = type;
        return this;
    }

    public String getOption()
    {
        if (option == 1)
            return "TCP";
        else if (option == 2)
            return "UDP";
        else if (option == 3)
            return  "TCP or UDP";

        return "Unknown";
    }

    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public String toString()
    {
        return getOption() + " | " + VALUE + " | Service: " + protocol.name();
    }
}
