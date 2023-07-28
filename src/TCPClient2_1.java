import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Kingthw
 * @date 2023/7/28 18:20
 */
public class TCPClient2_1 {
        public static void main(String[] args) {
            try {
                // 创建Socket对象，指定服务端的IP地址和端口号
                Socket socket = new Socket("localhost", 12000);
                System.out.println("已连接到服务端...");

                // 获取输入流和输出流
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);

                // 循环进行多次通信
                Scanner scanner = new Scanner(System.in);
                String line;
                while (true) {
                    System.out.print("请输入要发送的字符串（输入exit退出）：");
                    line = scanner.nextLine();

                    if ("exit".equalsIgnoreCase(line)) {
                        // 输入exit时退出循环，关闭连接
                        break;
                    } else {
                        // 发送消息给服务端
                        outputStream.println(line);

                        // 接收服务端的响应消息
                        String response = inputStream.readLine();
                        System.out.println("收到服务端响应：" + response);
                    }
                }

                // 关闭连接
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }