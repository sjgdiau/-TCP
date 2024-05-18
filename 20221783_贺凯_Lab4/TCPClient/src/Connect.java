import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connect extends JFrame {
    private static final long serialVersionUID = 5475179439752076273L;
    private Container container = getContentPane();
    private JLabel hostLabel = new JLabel("host:");
    private JTextField hostField = new JTextField();
    private JLabel portLabel = new JLabel("port:");
    private JTextField portField = new JTextField();
    private JButton okBtn = new JButton("确定");
    private JButton RegBtn = new JButton("清空");
    public String host;
    public int port;
    public boolean complete = false;
    public Connect() {
        setTitle("Connect");
        setBounds(600, 200, 400, 200);// 设计窗体大小
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();// 初始化窗口
        setVisible(true);// 设计窗口可见
    }
    private void init() {
        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        hostLabel.setBounds(50, 20, 50, 20);
        portLabel.setBounds(50, 60, 50, 20);
        fieldPanel.add(hostLabel);
        fieldPanel.add(portLabel);
        hostField.setBounds(110, 20, 160, 20);
        portField.setBounds(110, 60, 160, 20);
        portField.setText("2525");
        fieldPanel.add(hostField);
        fieldPanel.add(portField);
        container.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okBtn);
        buttonPanel.add(RegBtn);
        container.add(buttonPanel, "South");
        listerner();
    }
    public void listerner() {
        okBtn.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    host = hostField.getText();
                    String portS = portField.getText();
                    if (null == host
                            || portS == null
                            || host.trim().length() == 0
                            || portS.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "host或port不能为空");
                    }
                    try {
                        port = Integer.parseInt(portS);
                    } catch (NumberFormatException ex) {
                        System.out.println("输入的不是有效的整数：" + portS);
                    }
                    complete=true;
                    Connect.this.dispose();
                }
            });
        hostField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okBtn.doClick(); // 模拟按钮点击事件
            }
        });

        portField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okBtn.doClick(); // 模拟按钮点击事件
            }
        });

        RegBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        hostField.setText("");
                        portField.setText("");
                    }

                });}
    public String getHost(){
        return host;
    }
    public int getPort(){
        return  port;
    }

}
