package curdesign.immutability;

// immutability 模式
// 路由信息
public class Router {
    private final String ip;
    private final String port;
    private final String iface;

    public Router(String ip, String port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }
    public String getIface() {
        return this.iface;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
