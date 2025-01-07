package model;

import java.util.UUID;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class User {
    private String username;
    private UUID uid;
    private String password;
    private Date registrationDate;
    private Date lastLogin;
    private int poin;


    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        UUID uid = UUID.randomUUID();
        this.setUid(uid);
        this.poin = 0;
    }

    public User(String username, String password, UUID uid){
        this.setPassword(password);
        this.setUsername(username);
        this.setUid(uid);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

}