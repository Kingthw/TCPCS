import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * @author Kingthw
 * @date 2023/7/29 13:54
 */
public class TCPServer3 {
    public static void main(String[] args) throws IOException {
        //创建服务端对象
        ServerSocket serverSocket = new ServerSocket(12000);

        //等待客户端连接
        Socket socket = serverSocket.accept();

        //读取数据保存到本地文件中
        String name = UUID.randomUUID().toString().replace("-", "");
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("E:\\javaspace\\java基础\\day16-code\\ServerImg\\"+name+".jpg"));
        int len;
        byte[] bytes = new byte[1024];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        //反馈给客户端
        PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
        pw.println("上传成功");

        socket.close();
//        serverSocket.close();
    }
}
