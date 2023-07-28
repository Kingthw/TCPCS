import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kingthw
 * @date 2023/7/28 15:26
 */
public class TCPServer1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12000);
        Socket socket = serverSocket.accept();


        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        StringBuffer sb = new StringBuffer();
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char)b);
            sb.append((char)b);
        }
        String str = sb.toString();
        System.out.println(str);
      /*  OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
        osw.write("更改后的数据：" + str);
        osw.close();*/


        isr.close();
        socket.close();
        serverSocket.close();
    }
}
