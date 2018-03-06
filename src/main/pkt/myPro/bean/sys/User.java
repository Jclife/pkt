package myPro.bean.sys;

/**
 * @author liujun
 * @Title: UserTest
 * @Package com.neusoft.bean
 * @Description: 用户实体类
 * @date 2018/2/1 21:15
 */
public class User {

    private int user_id;
    private String user_phone;
    private String user_name;
    private String user_password;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_phone='" + user_phone + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_id() {
        if ("".equals(user_id)||String.valueOf(user_id)==null){
            user_id = 518122;
        }
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
