package chat;

import pojo.User;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: 牧心
 * @Date: 2020/09/07
 * @Description:
 */
public class ChatInfo {

    private static User self = null;

    private static List<User> list = new LinkedList<>();

    public static User getUser(){
        return self;
    }

    public static void setUser(User user){
        self = user;
    }
}
