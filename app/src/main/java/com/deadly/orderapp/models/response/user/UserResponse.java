package com.deadly.orderapp.models.response.user;

import com.deadly.orderapp.models.User;
import com.deadly.orderapp.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse extends BaseResponse {
    @SerializedName("result")
    @Expose
    public User result;
}
