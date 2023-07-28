import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Kingthw
 * @date 2023/7/28 15:26
 */
public class TCPClient1 {
    /*
    *客户端多次发送数据
    * 服务端多次接收数据
    * */


    public static void main(String[] args) throws IOException {
        String Host = "127.0.0.1";
        int port = 12000;
        Socket socket = new Socket(Host, port);
        OutputStreamWriter outSW = new OutputStreamWriter(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入你要发送的信息：");
            String str = sc.nextLine();
            if ("exit".equals(str)) {
                break;
            }
            outSW.write(str);
            outSW.flush();
        }
      /*  InputStreamReader inSR = new InputStreamReader(socket.getInputStream());
        int b;
        System.out.println("---------------------");
        while ((b = inSR.read()) != -1) {
            System.out.print((char)b);
        }
        inSR.close();*/
        outSW.close();
        socket.close();
    }
}
