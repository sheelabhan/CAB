package model;

public class UserRegModel {
    private String username;
    private String password;
    private String address;
    private String contact;
    private String gender;
    private String userType;

    public UserRegModel(String username, String password, String address, String contact, String gender, String userType) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.userType = userType;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
