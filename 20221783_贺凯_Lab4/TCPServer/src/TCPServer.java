
import java.io.*;
import java.net.*;

class TCPServer {

    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(2525);//创建serverSocket
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();//等待请求, 创建connSocket
            new Thread(new ClientHandler(connectionSocket)).start(); // 创建新线程处理客户端连接
        }
    }
}



