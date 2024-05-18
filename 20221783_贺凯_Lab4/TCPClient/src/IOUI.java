import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
public class IOUI extends JFrame{
    //上部组件
    private JPanel jp1=new JPanel();		//定义面板
    private JTextArea jta= new JTextArea();	//定义文本域
    private JScrollPane jspane=new JScrollPane(jta);	//定义滚动窗格
    //下部组件
    private JPanel jp2=new JPanel();
    private JButton jb1=new JButton("发送");	//定义按钮
    private JTextField text=new JTextField();
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    public IOUI(Socket clientSocket,DataOutputStream outToServer,BufferedReader inFromServer) {
        this.clientSocket=clientSocket;
        this.outToServer=outToServer;
        this.inFromServer=inFromServer;
        setTitle("Client");
        // 设计窗体大小
        setBounds(600, 300, 640, 400);
        // 添加一块桌布
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 初始化窗口
        init();
        // 设计窗口可见
        setVisible(true);
    }
    public void init(){
        setLayout(new BorderLayout());//设置布局
        //CENTER
        jta.setLineWrap(true);//设置多行文本框自动换行
        jta.setEditable(false);
        jta.setFont(new Font("宋体", Font.BOLD, 16)); // 设置字体和大小
        jta.append("连接成功！Congratulation！！"+ '\n');
        //NORTH
        text.setPreferredSize(new Dimension(300, 30));
        //设置布局管理
        jp1.setLayout(new BorderLayout());	//设置面板布局
        jp2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //添加组件
        jp1.add(jspane);
        jp2.add(text);
        jp2.add(jb1);

        this.add(jp1,BorderLayout.CENTER);
        this.add(jp2,BorderLayout.SOUTH);

        listerner();
    }
    private String sentence1,sentence2;

    private void listerner() {
        jb1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sentence1 = text.getText();
                        jta.append("Client: " + sentence1 + '\n');
                        try {
                            outToServer.writeBytes(sentence1 + '\n');
                        } catch (IOException ex) {
                            System.out.println("发送失败！");
                            jta.append("发送失败！");
                        }
                        try {
                            sentence2 = inFromServer.readLine();
                            jta.append("Server: " + sentence2 + '\n');
                        } catch (IOException ex) {
                            System.out.println("接收失败！");
                            jta.append("接收失败！");
                        }
                        if(sentence2.equals("BYE")){
                            jta.append("连接已断开，如需再次访问，请重启程序！");
                        }
                        text.setText("");
                    }
                });
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jb1.doClick(); // 模拟按钮点击事件
            }
        });
    }
}
