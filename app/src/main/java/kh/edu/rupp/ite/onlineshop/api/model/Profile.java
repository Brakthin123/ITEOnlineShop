package kh.edu.rupp.ite.onlineshop.api.model;

import com.google.gson.annotations.SerializedName;

public class Profile {
    private String first_name;
    private String last_name;
    private String email;
    @SerializedName("phone-number")
    private String phone_number;
    private String gender;
    @SerializedName("image-url")
    private String image_url;
    private String birthday;

    private String address = "Robert Robertson, 1234 NW Bobcat Lane, St. Robert, MO 65584-5678";


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}