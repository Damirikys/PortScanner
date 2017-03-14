package models;

public class Port
{
    public static final int TCP = 1;
    public static final int UDP = 2;

    private final int VALUE;
    private int type;
    private String service;

    public Port(int value)
    {
        this.VALUE = value;
    }

    public Port setType(int protocol) {
        this.type = protocol;
        return this;
    }

    public int getType()
    {
        return type;
    }

    public String getTypeName()
    {
        if (type == 1)
            return "TCP";
        else if (type == 2)
            return "UDP";
        return "Unknown";
    }

    public Port setService(String name) {
        this.service = name;
        return this;
    }

    public String getService() {
        return service;
    }

    @Override
    public String toString()
    {
        return getTypeName() + " | " + VALUE + " | Service: " + service;
    }
}
