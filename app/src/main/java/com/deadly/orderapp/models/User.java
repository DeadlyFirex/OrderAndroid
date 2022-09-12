package com.deadly.orderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone_number")
    @Expose
    public String phoneNumber;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("postal_code")
    @Expose
    public String postalCode;
    @SerializedName("created_at")
    @Expose
    public LocalDateTime createdAt;
    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("flags")
    @Expose
    public List<String> flags;
    @SerializedName("admin")
    @Expose
    public boolean admin;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("tags")
    @Expose
    public List<String> tags;

    @SerializedName("active")
    @Expose
    public boolean active;
    @SerializedName("last_action_at")
    @Expose
    public LocalDateTime lastActionAt;
    @SerializedName("last_action_ip")
    @Expose
    public String lastActionIp;
    @SerializedName("last_action")
    @Expose
    public String lastAction;
    @SerializedName("last_login_at")
    @Expose
    public LocalDateTime lastLoginAt;
    @SerializedName("last_login_ip")
    @Expose
    public String lastLoginIp;
    @SerializedName("login_count")
    @Expose
    public int loginCount;
}
