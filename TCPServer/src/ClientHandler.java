import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ClientHandler implements Runnable {
    private Socket connectionSocket;
    private Connection con;
    private String uname;
    public static User user;
    private boolean isLogin=false;
    private ScheduledExecutorService executor;
    private boolean timerExpired = false; // 添加一个标志来指示计时器是否已触发
    public ClientHandler(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
        this.executor = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            // 启动定时器，在 15 秒后没有收到消息则关闭连接
            executor.schedule(() -> {
                try {
                    timerExpired = true; // 设置计时器已触发
                    connectionSocket.close();
                    executor.shutdown(); // 关闭定时器
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, 60, TimeUnit.SECONDS);

            String clientSentence;
            while ((clientSentence = inFromClient.readLine()) != null) {
                // 如果计时器已触发，则返回 "401 ERROR!"
                if (timerExpired) {
                    outToClient.writeBytes("999 TIMEOUT!\n");
                    continue;
                }
                // 每次收到消息时重置定时器
                if (executor != null) {
                    executor.shutdownNow(); // 立即取消旧的定时任务
                }
                executor = Executors.newScheduledThreadPool(1); // 创建一个新的定时器
                executor.schedule(() -> {
                    try {
                        connectionSocket.close();
                        executor.shutdown(); // 关闭定时器
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }, 60, TimeUnit.SECONDS);

                // 处理收到的消息
                String[] parts = clientSentence.split(" ");
                String command = parts[0];
                String response = "";

                switch (command) {
                    case "HELO":
                        if (parts.length>1){
                            this.uname=parts[1];
                            con=new Connection(uname);
                            response = "500 AUTH REQUIRED!";
                        }else {
                            response="401 ERROR!";
                        }
                        break;
                    case "PASS":
                        int uid=con.validate();
                        if(parts.length>1&&parts[1].equals(con.getUpass())){
                            user=new User(uid,this.uname,con.getSum());
                            isLogin=true;
                            response="525 OK!";
                        }else {
                            response="401 ERROR!";
                        }
                        break;
                    case "BALA":
                        if(isLogin) {
                            response = "AMNT:" + user.getSum();
                        }
                        break;
                    case "WDRA":
                        if (isLogin&&parts.length>1){
                            int wdr=0;
                            try {
                                wdr = Integer.parseInt(parts[1]);
                            } catch (NumberFormatException ex) {
                                System.out.println("输入的不是有效的整数：" + parts[1]);
                            }
                            if (wdr>0&&wdr<user.getSum()){
                                Withdraw add=new Withdraw(user.getUserId(), -wdr);
                                if (add.complete) {
                                    response = "525 OK!";
                                }else response="401 ERROR!";
                            }else response="401 ERROR!";
                        }
                        break;
                    case "BYE":
                        response = "BYE";
                        outToClient.writeBytes(response + "\n"); // 发送 "BYE" 响应
                        connectionSocket.close(); // 关闭连接
                        break;
                    default:
                        response = "401 ERROR!";
                        break;
                }
                outToClient.writeBytes(response + "\n");//向serverSocket写数据流
            }
            //connectionSocket.close(); // 关闭连接
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
