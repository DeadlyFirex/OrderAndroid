package com.deadly.orderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("user")
    @Expose
    public String user;

    @SerializedName("products")
    @Expose
    public List<String> products;
    @SerializedName("total_price")
    @Expose
    public double totalPrice;
    @SerializedName("notes")
    @Expose
    public String notes;
    @SerializedName("employee_notes")
    @Expose
    public String employeeNotes;

    @SerializedName("event")
    @Expose
    public String event;
    @SerializedName("created_at")
    @Expose
    public LocalDateTime created_at;
    @SerializedName("last_changed_at")
    @Expose
    public LocalDateTime last_changed_at;
    @SerializedName("expired")
    @Expose
    public boolean expired;
    @SerializedName("completed")
    @Expose
    public boolean completed;
}
