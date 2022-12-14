package com.deadly.orderapp.models.response.event;

import com.deadly.orderapp.models.Event;
import com.deadly.orderapp.models.response.BaseResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventResponse extends BaseResponse {
    @SerializedName("result")
    @Expose
    public Event result;
}