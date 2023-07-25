import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author µ³
 * @version 1.0
 * 2022/4/18   11:07
 */
public class demo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
    }

}
