package com.boscan.psnnotifier.util;

public class PSNUtil {
    public static boolean isNewPrice(float oldValue, String newValue) {
        return newValue != null && oldValue > Float.parseFloat(newValue.replaceAll("\\$", "").trim());
    }

    public static float parsePrice(String price) {
        if (price == null) {
            return 0f;
        }
        return Float.parseFloat(price.replaceAll("\\$", "").trim());
    }
}
