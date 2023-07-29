import java.io.*;
import java.net.Socket;

/**
 * @author Kingthw
 * @date 2023/7/29 13:55
 */
public class TCPClient3 {
    public static void main(String[] args) throws IOException {
        String host = "172.29.223.111";
        int port = 12000;
        Socket socket = new Socket(host, port);
        File file = new File("day16-code\\img\\preview.jpg");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes))!=-1) {
            bos.write(bytes, 0, len);
        }
        socket.shutdownOutput();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = br.readLine();
        System.out.println("收到服务端消息："+response);
        socket.close();
    }
}
