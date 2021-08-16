package redisServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RedisServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(6379);
        Socket accept = socket.accept();
        byte b[] = new byte[1024];
        accept.getInputStream().read(b);
        System.out.println(new String(b));
    }
}
