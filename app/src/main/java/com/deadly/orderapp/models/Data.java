package com.deadly.orderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Data {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("uuid")
    @Expose
    public String uuid;

    @SerializedName("events_hash")
    @Expose
    public String eventsHash;
    @SerializedName("events_last_changed")
    @Expose
    public LocalDateTime eventsLastChanged;

    @SerializedName("orders_hash")
    @Expose
    public String ordersHash;
    @SerializedName("orders_last_changed")
    @Expose
    public LocalDateTime ordersLastChanged;

    @SerializedName("products_hash")
    @Expose
    public String productsHash;
    @SerializedName("products_last_changed")
    @Expose
    public LocalDateTime productsLastChanged;

    @SerializedName("users_hash")
    @Expose
    public String usersHash;
    @SerializedName("users_last_changed")
    @Expose
    public LocalDateTime usersLastChanged;
}
