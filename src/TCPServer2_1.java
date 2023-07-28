import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kingthw
 * @date 2023/7/28 18:23
 */
public class TCPServer2_1 {
    public static void main(String[] args) {
        try {
            // 创建ServerSocket对象，监听指定端口号
            ServerSocket serverSocket = new ServerSocket(12000);
            System.out.println("服务端已启动，等待客户端连接...");

            // 接受客户端连接请求
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接，开始处理请求...");

            // 获取输入流和输出流
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(clientSocket.getOutputStream(), true);

            String line;
            while ((line = inputStream.readLine()) != null) {
                System.out.println("收到客户端消息：" + line);

                // 将收到的消息转换为大写字母
                String upperCaseMsg = line.toUpperCase();

                // 发送大写字母消息给客户端
                outputStream.println(upperCaseMsg);
                System.out.println("发送大写字母消息给客户端：" + upperCaseMsg);
            }

            // 关闭连接
            inputStream.close();
            outputStream.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
