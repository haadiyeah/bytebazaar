package bytebazaar;

public abstract class User {
    private String email; //also the primary key in the db
    private String password;
    private String phoneNum;
    private String name;
    private int ID;
    
    
    public User(String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
    }
    public User(int id, String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        this.ID=id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public void setDetails() {
    }

}
