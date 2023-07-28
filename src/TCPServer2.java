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

        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        OutputStreamWriter outSW = new OutputStreamWriter(socket.getOutputStream());
        StringBuffer sb = new StringBuffer();
        int b;
        while (true) {
            //接收数据
            while ((b = isr.read()) != -1) {
                sb.append((char) b);
            }
            String Data = sb.toString();
            System.out.println("接收到一次数据传输：");
            //发送转换后的数据
            String UpData = Data.toUpperCase();
            outSW.write(UpData);
            socket.shutdownOutput();
            outSW.flush();
        }
    }
}
