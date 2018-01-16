package model;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String postal;
    private String country;
    private String hPhone;
    private String bPhone;
    private String email;

    public Customer(){}

    public Customer(int customerId, String firstName, String lastName, String address, String city, String province, String postal, String country, String hPhone, String bPhone, String email){
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postal = postal;
        this.country = country;
        this.hPhone = hPhone;
        this.bPhone = bPhone;
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String gethPhone() {
        return hPhone;
    }

    public void sethPhone(String hPhone) {
        this.hPhone = hPhone;
    }

    public String getbPhone() {
        return bPhone;
    }

    public void setbPhone(String bPhone) {
        this.bPhone = bPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
