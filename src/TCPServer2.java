import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kingthw
 * @date 2023/7/28 16:49
 */
public class TCPServer2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12000);
        Socket socket = serverSocket.accept();
        System.out.println("连接成功！等待数据传输");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        StringBuffer sb = new StringBuffer();
        int b;
        while (true) {
            //接收数据
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            System.out.println("接收到一次数据传输：");
            //发送转换后的数据
            OutputStreamWriter outSW = new OutputStreamWriter(socket.getOutputStream());

            String UpData = data.toUpperCase();
            outSW.write(UpData);
            socket.shutdownOutput();
            outSW.flush();
        }
    }
}
