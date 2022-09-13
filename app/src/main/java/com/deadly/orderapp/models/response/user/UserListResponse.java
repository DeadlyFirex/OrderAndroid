package com.deadly.orderapp.models.response.user;

import com.deadly.orderapp.models.User;
import com.deadly.orderapp.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListResponse extends BaseResponse {
    @SerializedName("result")
    @Expose
    public List<User> result;
}
