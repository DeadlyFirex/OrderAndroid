package com.deadly.orderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Event {
    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("uuid")
    @Expose
    public String uuid;

    @SerializedName("active")
    @Expose
    public boolean active;

    @SerializedName("created_at")
    @Expose
    public LocalDateTime createdAt;

    @SerializedName("deadline")
    @Expose
    public LocalDateTime deadline;

    @SerializedName("until")
    @Expose
    public LocalDateTime until;

    @SerializedName("max_order_price")
    @Expose
    public LocalDateTime maxOrderPrice;
}
