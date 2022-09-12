package com.deadly.orderapp.models.response.product;

import com.deadly.orderapp.models.Product;
import com.deadly.orderapp.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse extends BaseResponse {
    @SerializedName("result")
    @Expose
    public List<Product> result;
}
