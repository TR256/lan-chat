package window;

import chat.ChatInfo;
import paramters.UIParamters;
import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author: 牧心
 * @Date: 2020/09/07
 * @Description:
 */
public class LoginWindow{
    private static LoginWindow loginWindow;
    private JFrame loginFrame;
    private JPanel loginPanel;
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JTextField usernameInput;
    private JButton loginBtn;

    private LoginWindow(){
        init();
    }

    /**
     * 窗口初始化
     */
    private void init(){
        // 窗口标题
        loginFrame = new JFrame("登录");
        // 不可放缩
        loginFrame.setResizable(false);
        //
        Container container = loginFrame.getContentPane();
        /**********************/
        //获取屏幕的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //任务栏高度
//        int missionBoardHeight = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration()).bottom;
        int SCREEN_WIDHTH = (int) screenSize.getWidth();
        int SCREEN_HEIGHT = (int)screenSize.getHeight();
        /**********************/

        // 窗口居中
        loginFrame.setSize(UIParamters.LOGIN_WINDOW_WIDTH,
                UIParamters.LOGIN_WINDOW_HEIGHT);
        loginFrame.setLocation((SCREEN_WIDHTH - UIParamters.LOGIN_WINDOW_WIDTH)/2,
                (SCREEN_HEIGHT - UIParamters.LOGIN_WINDOW_HEIGHT)/2);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建面板容器
        loginPanel = new JPanel();
        //
        container.add(loginPanel);
        // loginFrame.setContentPane(loginPanel);

        // null布局
        loginPanel.setLayout(null);

        // 登录标题
        titleLabel = new JLabel("登录");
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        titleLabel.setBounds(new Rectangle(190, 40, 120, 60));
        loginPanel.add(titleLabel);

        // 用户名输入标签
        usernameLabel = new JLabel("用户名:");
        usernameLabel.setVerticalTextPosition(JLabel.CENTER);
        usernameLabel.setHorizontalTextPosition(JLabel.CENTER);
        usernameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        usernameLabel.setBounds(new Rectangle(80, 140, 80, 40));
        loginPanel.add(usernameLabel);

        // 用户名输入框
        usernameInput = new JTextField();
        usernameInput.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        usernameInput.setBounds(160, 140, 260, 40);
        loginPanel.add(usernameInput);

        // 登录按钮
        loginBtn = new JButton("登录");
        loginBtn.setVerticalTextPosition(JButton.CENTER);
        loginBtn.setHorizontalTextPosition(JButton.CENTER);
        // 去除点击按钮时文字周围的线框
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        loginBtn.setBounds(80, 220, 340, 40);
        // 绑定登录事件
        loginBtn.addActionListener(e -> loginAction(e));
        loginPanel.add(loginBtn);

        //
        // loginFrame.pack();
        // 监听框架关闭按钮事件
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //
        usernameInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    loginBtn.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //
        loginFrame.setVisible(true);


    }

    /**
     * 获取登录窗口
     * @return
     */
    public static LoginWindow getInstance(){
        if (loginWindow == null){
            loginWindow = new LoginWindow();
        }
        return loginWindow;
    }

    /**
     * 登录按钮事件
     * @param e
     */
    private void loginAction(ActionEvent e){
        String username = usernameInput.getText().trim();
        if ("".equals(username)){
            return;
        }
        // 销毁登录窗口
        loginWindow.loginFrame.dispose();
        loginWindow = null;
        //
        User user = new User(username);
        ChatInfo.setUser(user);
        // 进入群聊界面
        ChatWindow.getInstance();
    }



}
