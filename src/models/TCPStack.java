package models;

import java.util.HashMap;

public enum TCPStack
{
    http,
    ftp,
    ssh,
    smtp,
    pop3,
    pptp,
    echo,
    whois,
    sql,
    bgp;

    public static final HashMap<TCPStack, String[]> wordMap
            = new HashMap<TCPStack, String[]>()
    {{
        put(http, new String[] { "html" });
        put(smtp, new String[] { "220" });
        put(pop3, new String[] { "pop" });
    }};
}
