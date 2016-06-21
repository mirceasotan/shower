package com.mircea.sotan.model;

import com.google.gson.Gson;

/**
 * Created by mircea
 */
public class GsonObject {
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static <T extends Object> T fromJson(String json, Class<T> gsonClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, gsonClass);
    }
}
