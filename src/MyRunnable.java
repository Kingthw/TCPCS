import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * @author Kingthw
 * @date 2023/7/29 15:11
 */
public class MyRunnable implements Runnable {
    private Socket socket;
    public MyRunnable(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
