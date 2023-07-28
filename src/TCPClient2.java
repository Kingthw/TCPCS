import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Kingthw
 * @date 2023/7/28 16:50
 */
public class TCPClient2 {
    public static void main(String[] args) throws IOException {
        /*
        * 客户端发送消息，服务端接收后将小写字母转换为大写字母
        * */
        String Host = "172.29.223.111";
        int port = 12000;
        Socket socket = new Socket(Host, port);
        OutputStreamWriter outSW = new OutputStreamWriter(socket.getOutputStream());
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        StringBuffer sb = new StringBuffer();
        int b;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("输入要转换的字符串：");
            String str = sc.nextLine();
            if ("exit".equals(str)) {
                break;
            }
            //发送数据
            outSW.write(str);
            socket.shutdownOutput();
            outSW.flush();

            while ((b = isr.read()) != -1) {
                sb.append((char)b);
            }
            String UpData = sb.toString();
            System.out.println("修改后的数据为："+UpData);
        }
        isr.close();
        outSW.close();
        socket.close();
    }
}
