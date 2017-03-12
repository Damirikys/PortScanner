package prototype;

import models.Port;

import java.util.List;

import static models.Port.TCP;
import static models.Port.UDP;

public abstract class PortScannerAdapter implements PortScanner
{
    protected String host = "localhost";
    protected int startPort = 1;
    protected int finalPort = 65535;
    protected int timeout = 200;
    protected boolean logMode = false;

    protected int options = TCP;

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

    public PortScannerAdapter setOptions(int type)
    {
        this.options = type;
        return this;
    }

    public int getOptions()
    {
        return this.options;
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
