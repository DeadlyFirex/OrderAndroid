package com.deadly.orderapp.models.response.generic;

import com.deadly.orderapp.models.Data;
import com.deadly.orderapp.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChangedResponse extends BaseResponse {
    @SerializedName("result")
    @Expose
    public List<Data> result;
}
