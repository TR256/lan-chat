package window;

import chat.ChatHandler;
import chat.ChatInfo;
import chat.ChatReceiver;
import paramters.UIParamters;
import pojo.Message;
import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;


/**
 * @author: 牧心
 * @Date: 2020/09/07
 * @Description:
 */
public class ChatWindow {
    //
    private User user;
    private static ChatWindow chatWindow;
    private JFrame chatFrame;
    private JPanel chatPanel;

    private JTextField msgInput;
    private JButton sendBtn;

    private JTextArea msgShowArea;
    private JScrollPane scrollPane;

    private JPopupMenu popupMenu;

    private ChatWindow() {
        this.user = ChatInfo.getUser();
        init();
        // 开启一个消息接收端线程
        new Thread(new ChatReceiver()).start();
    }

    /**
     *
     */
    private void init() {
        // 窗口标题
        chatFrame = new JFrame(user.getUsername());
        // 不可放缩
        chatFrame.setResizable(false);
        //
        Container container = chatFrame.getContentPane();
        /**********************/
        //获取屏幕的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int SCREEN_WIDHTH = (int) screenSize.getWidth();
        int SCREEN_HEIGHT = (int) screenSize.getHeight();
        /**********************/

        // 窗口居中
        chatFrame.setSize(UIParamters.CHAT_WINDOW_WIDTH,
                UIParamters.CHAT_WINDOW_HEIGHT);
        chatFrame.setLocation((SCREEN_WIDHTH - UIParamters.CHAT_WINDOW_WIDTH) / 2,
                (SCREEN_HEIGHT - UIParamters.CHAT_WINDOW_HEIGHT) / 2);
        chatFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 创建面板容器
        chatPanel = new JPanel();
        //
        container.add(chatPanel);
        // loginFrame.setContentPane(loginPanel);

        // null布局
        chatPanel.setLayout(null);


        // 消息输入框
        msgInput = new JTextField();
        msgInput.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        msgInput.setBounds(20, 520, 620, 40);
        //
        msgInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendBtn.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        chatPanel.add(msgInput);

        // 发送按钮
        sendBtn = new JButton("发送");
        sendBtn.setVerticalTextPosition(JButton.CENTER);
        sendBtn.setHorizontalTextPosition(JButton.CENTER);
        // 去除点击按钮时文字周围的线框
        sendBtn.setFocusPainted(false);
        sendBtn.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        sendBtn.setBounds(660, 520, 120, 40);
        //
        sendBtn.addActionListener(e -> {
            try {
                sendMessage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        //
        chatPanel.add(sendBtn);

        // 消息输入框
        msgShowArea = new JTextArea();
        msgShowArea.setFont(new Font(Font.SERIF, Font.BOLD, 20));
//        msgShowArea.setBounds(20, 20, 760, 470);
        // 自动换行
        msgShowArea.setLineWrap(true);
        //
        scrollPane = new JScrollPane(msgShowArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 20, 760, 470);
//        chatPanel.add(msgShowArea);
        chatPanel.add(scrollPane);
        msgShowArea.setEditable(false);
        //
        popupMenu = new JPopupMenu();
        JMenuItem copy = new JMenuItem("复制");
        copy.setFont(new Font(Font.SERIF, Font.TRUETYPE_FONT, 18));
        copy.setFocusPainted(false);
        copy.setSize(160, 30);
        copy.addActionListener(e -> {
            String selectText = msgShowArea.getSelectedText().trim();
            if (!"".equals(selectText)){
                StringSelection selection = new StringSelection(selectText);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            }
        });
        popupMenu.add(copy);
        msgShowArea.setComponentPopupMenu(popupMenu);
        //
        msgShowArea.setSelectedTextColor(Color.orange);
        // 监听框架关闭按钮事件
        chatFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 退出程序
                System.exit(0);
            }
        });
        //
        chatFrame.setVisible(true);
    }

    /**
     * 发送消息
     */
    private void sendMessage() throws Exception {
        String msg = msgInput.getText().trim();
        if (!"".equals(msg)) {
            // 清空消息输入框
            msgInput.setText("");
            // 将消息追加到消息列表
            Message message = new Message(user.getUsername(), msg);
            // 将消息展示在消息区
//        showMsg(message);
            ChatHandler.sendMessage(message);
        }
    }


    /**
     * 单例模式
     *
     * @return
     */
    public static ChatWindow getInstance() {
        if (chatWindow == null) {
            chatWindow = new ChatWindow();
        }
        return chatWindow;
    }

    /**
     * 像文本域追加消息记录
     *
     * @param message
     */
    private void showMsg(Message message) {
        if (!"".equals(message.getMessage())) {
            msgShowArea.append("@");
            msgShowArea.append(message.getUsername());
            msgShowArea.append("\t");
            msgShowArea.append(message.getDatestamp());
            msgShowArea.append("\n  ");
            msgShowArea.append(message.getMessage());
            msgShowArea.append("\n");
        }
    }

    /**
     * @param message
     */
    public void displayMessage(Message message) {
        showMsg(message);
    }
}
