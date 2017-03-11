package models;

import prototype.Protocol;

public class Port
{
    private final int VALUE;
    private Protocol protocol;

    public Port(int value)
    {
        this.VALUE = value;
        this.protocol = Protocol.unknown;
    }

    public Port setProtocol(Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public String toString()
    {
        return "models.Port: " + VALUE + " | Service: " + protocol.name();
    }
}
