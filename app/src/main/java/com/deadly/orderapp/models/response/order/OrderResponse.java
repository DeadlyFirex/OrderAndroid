package com.deadly.orderapp.models.response.order;

import com.deadly.orderapp.models.Order;
import com.deadly.orderapp.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse extends BaseResponse {
    @SerializedName("result")
    @Expose
    public Order result;
}
