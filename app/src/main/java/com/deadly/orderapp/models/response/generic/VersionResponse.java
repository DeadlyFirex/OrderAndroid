package com.deadly.orderapp.models.response.generic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionResponse {
    @SerializedName("details")
    @Expose
    public VersionDetails details;

    public class VersionDetails {
        @SerializedName("link")
        @Expose
        public String link;
    }
}
