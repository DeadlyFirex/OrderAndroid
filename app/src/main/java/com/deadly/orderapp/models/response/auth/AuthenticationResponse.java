package com.deadly.orderapp.models.response.auth;

import com.deadly.orderapp.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse extends BaseResponse {

    @SerializedName("login")
    @Expose
    public LoginDetails login;
    public class LoginDetails {
        @SerializedName("lifetime")
        @Expose
        public double lifetime;

        @SerializedName("token")
        @Expose
        public String token;

        @SerializedName("uuid")
        @Expose
        public String uuid;
    }
}


