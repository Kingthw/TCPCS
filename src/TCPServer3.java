import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author Kingthw
 * @date 2023/7/29 13:54
 */
public class TCPServer3 {
    public static void main(String[] args) throws IOException {
        //创建服务端对象
        ServerSocket serverSocket = new ServerSocket(12000);
        //创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 9,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        while (true) {
            //等待客户端连接
            Socket socket = serverSocket.accept();
/*            //开启一条线程
            //一个客户端对应一条线程
            new Thread(new MyRunnable(socket)).start();*/
            //线程池优化
            //一个客户端对应于线程池中的一条线程
            pool.submit(new MyRunnable(socket));
        }
//        serverSocket.close();
    }
}
