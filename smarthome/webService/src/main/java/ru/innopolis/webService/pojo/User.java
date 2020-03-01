package ru.innopolis.webService.pojo;

public class User {
    private String name;
    private String address;
    private String phone;
    private String telegram;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public User(){
        this.name = "name1";
        this.address = "address1";
        this.phone = "phone1";
        this.telegram = "telegram1";
    }

    public User(String name, String address, String phone, String telegram) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.telegram = telegram;
    }
}
