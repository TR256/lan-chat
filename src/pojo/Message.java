package pojo;

import utils.DateUtils;

import java.io.Serializable;

/**
 * @author: 牧心
 * @Date: 2020/09/07
 * @Description:
 */
public class Message implements Serializable {

    private String username;

    private String message;

    private String datestamp;

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
        this.datestamp = DateUtils.getDateStamp();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(String datestamp) {
        this.datestamp = datestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", datestamp='" + datestamp + '\'' +
                '}';
    }
}
