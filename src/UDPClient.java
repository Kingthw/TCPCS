import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author Kingthw
 * @date 2023/7/28 13:46
 */
public class UDPClient{
    //1创建对象
    public static void main(String[] args) throws IOException {
        //UDP套接字
        DatagramSocket ds = new DatagramSocket(10087);

        Scanner sc = new Scanner(System.in);
        InetAddress host = InetAddress.getByName("127.0.0.1");
        while (true) {
            System.out.println("输入要转换的字符串：");
            String s = sc.nextLine();
            if ("exit".equals(s)) {
                break;
            }
            //创建发送数据报包
            byte[] sendMessage = s.getBytes();
            DatagramPacket sendDp = new DatagramPacket(sendMessage, sendMessage.length, host, 10086);
            ds.send(sendDp);

            //创建接收数据报包
            byte[] RecMessage = new byte[sendMessage.length + 1];
            DatagramPacket RecDp = new DatagramPacket(RecMessage, RecMessage.length);
            ds.receive(RecDp);
            byte[] data = RecDp.getData();
            System.out.println("From UDPServer:"
                    + RecDp.getAddress().getHostAddress() + ":"
                    + "修改后的数据:" + new String(data,0,RecDp.getLength()));
        }
        //关闭套接字
        ds.close();
    }
}
