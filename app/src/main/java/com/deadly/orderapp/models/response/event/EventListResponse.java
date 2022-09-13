package com.deadly.orderapp.models.response.event;

import com.deadly.orderapp.models.Event;
import com.deadly.orderapp.models.response.BaseResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventListResponse extends BaseResponse {
    @SerializedName("result")
    @Expose
    public List<Event> result;
}