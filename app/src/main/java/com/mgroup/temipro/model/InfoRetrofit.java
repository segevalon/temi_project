package com.mgroup.temipro.model;

import com.google.gson.annotations.SerializedName;

class InfoRetrofit {

    @SerializedName("type")
    public String type;
    @SerializedName("key")
    public String key;

    public InfoRetrofit(String type, String key) {
        this.type = type;
        this.key = key;
    }
}
