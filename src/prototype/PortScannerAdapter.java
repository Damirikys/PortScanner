package prototype;

import models.Port;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public abstract class PortScannerAdapter implements PortScanner
{
    protected InetAddress inetAddress;
    protected String host = "localhost";
    protected int startPort = 1;
    protected int finalPort = 65535;
    protected int timeout = 200;
    protected boolean logMode = false;

    protected int options = Port.TCP | Port.UDP;

    public PortScannerAdapter() {

    }

    public PortScannerAdapter(String host, int port) throws UnknownHostException {
        this.logMode = false;
        this.host = host;
        this.startPort = port;
        this.finalPort = port;
        this.inetAddress = InetAddress.getByName(host);
    }

    public PortScannerAdapter(String host, int startPort, int finalPort, int timeout) throws UnknownHostException {
        this.logMode = false;
        this.host = host;
        this.startPort = startPort;
        this.finalPort = finalPort;
        this.timeout = timeout;
        this.inetAddress = InetAddress.getByName(host);
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

    public PortScannerAdapter setHost(String host) throws UnknownHostException {
        this.host = host;
        this.inetAddress = InetAddress.getByName(host);
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
}
