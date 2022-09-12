package com.deadly.orderapp.models.response.auth;

import com.deadly.orderapp.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyResponse {
    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("login")
    @Expose
    public VerifyDetails login;

    public class VerifyDetails {
        @SerializedName("uuid")
        @Expose
        public String uuid;

        @SerializedName("admin")
        @Expose
        public boolean admin;
    }
}
