import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Kingthw
 * @date 2023/7/28 13:47
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        //UDP套接字
        DatagramSocket ds = new DatagramSocket(10086);
        InetAddress host = InetAddress.getByName("127.0.0.1");
        System.out.println("UDP服务器在端口：10086,准备就绪......");
        while (true) {
            //创建接收数据报包
            byte[] RecMessage = new byte[1024];
            DatagramPacket RecDp = new DatagramPacket(RecMessage, RecMessage.length);
            ds.receive(RecDp);
            byte[] data = RecDp.getData();
            String dataMessage = new String(data,0,RecDp.getLength());
            System.out.println("接收到一次数据传输:"
                    + RecDp.getAddress().getHostAddress() + ":"
                    + "数据:" +dataMessage);
            //创建发送数据报包
            byte[] sendMessage =dataMessage.toUpperCase().getBytes();
            DatagramPacket sendDp = new DatagramPacket(sendMessage, sendMessage.length, host, 10087);
            ds.send(sendDp);
        }
        //关闭套接字
//        ds.close();
    }

}
