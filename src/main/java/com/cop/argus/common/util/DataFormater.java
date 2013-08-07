package com.cop.argus.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author chris.liu
 */
public class DataFormater {
    private static Gson GSON = null;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        GSON = gsonBuilder.create();
    }

    public static String format(Object obj) {
        return GSON.toJson(obj);
    }
}
