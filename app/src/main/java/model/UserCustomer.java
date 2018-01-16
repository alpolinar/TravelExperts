package model;

public class UserCustomer {
    private int userId;
    private int customerId;
    private String username;
    private String password;

    public UserCustomer(){}
    public UserCustomer(int userId, int customerId, String username, String password){
        this.userId = userId;
        this.customerId = customerId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
