package com.boscan.psnnotifier.util;

public class PSNUtil {
    public static boolean isBetterPrice(float oldValue, String newValue) {
        return newValue != null && newValue.toUpperCase().contains("FREE") && oldValue != 0f || newValue != null && oldValue > Float.parseFloat(newValue.replaceAll("\\$", "").trim());
    }

    public static float parsePrice(String price) {
        if (price == null) {
            return 0f;
        }
        return Float.parseFloat(price.replaceAll("\\$", "").trim());
    }

    public static boolean validateFree(String value) {
        return value != null && value.toUpperCase().contains("FREE");
    }
}
