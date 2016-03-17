package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mircea
 */
public class PrivateUser extends PublicUser {
    @SerializedName("birthdate")
    private String birthDate;
    @SerializedName("country")
    private String country;
    @SerializedName("email")
    private String email;
    @SerializedName("product")
    private String product;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
