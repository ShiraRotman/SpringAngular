package rotman.shira.cropx;

import javax.persistence.*;

@Entity @Table(name="users")
public class ElectionsUser
{
    private Integer userID;
    private String userName;
    private String password;
    private boolean enabled;

    @Id @GeneratedValue @Column(name="user_id")
    public Integer getUserID() { return userID; }

    private void setUserID(Integer userID)
    { this.userID=userID; }

    @Column(name="username",nullable=false)
    public String getUserName() { return userName; }

    private void setUserName(String userName)
    { this.userName=userName; }

    @Column(name="password",nullable=false)
    private String getPassword() { return password; }

    private void setPassword(String password)
    { this.password=password; }

    @Column(name="enabled",nullable=false)
    private boolean isEnabled() { return enabled; }

    private void setEnabled(boolean enabled)
    { this.enabled=enabled; }
}
