package prototype;

import models.Port;

import java.util.List;

public abstract class PortScannerAdapter implements PortScanner
{
    protected String host;
    protected int startPort;
    protected int finalPort;
    protected int timeout;
    protected boolean logMode;

    public PortScannerAdapter() {

    }

    public PortScannerAdapter(String host, int port) {
        this.logMode = false;
        this.host = host;
        this.startPort = port;
        this.finalPort = port;
    }

    public PortScannerAdapter(String host, int startPort, int finalPort, int timeout){
        this.logMode = false;
        this.startPort = startPort;
        this.finalPort = finalPort;
        this.timeout = timeout;
    }

    public PortScanner setLogMode(boolean bool)
    {
        this.logMode = bool;
        return this;
    }

    public boolean logModeIsEnable() {
        return this.logMode;
    }

    public String getHost() {
        return host;
    }

    public PortScannerAdapter setHost(String host) {
        this.host = host;
        return this;
    }

    public int getStartPort() {
        return startPort;
    }

    public PortScannerAdapter setStartPort(int startPort) {
        this.startPort = startPort;
        return this;
    }

    public int getFinalPort() {
        return finalPort;
    }

    public PortScannerAdapter setFinalPort(int finalPort) {
        this.finalPort = finalPort;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public PortScannerAdapter setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public abstract List<Port> scan();

    protected abstract void portIsOpen(int port);
}
