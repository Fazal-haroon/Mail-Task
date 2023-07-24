package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

public class ListConverter {

    // Convert list to comma-separated string
    public static String listToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return String.join(",", list);
    }

    // Convert comma-separated string to list
    public static List<String> stringToList(String str) {
        List<String> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] items = str.split(",");
            for (String item : items) {
                list.add(item.trim());
            }
        }
        return list;
    }
}
