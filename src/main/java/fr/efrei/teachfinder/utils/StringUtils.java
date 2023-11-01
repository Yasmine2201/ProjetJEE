package fr.efrei.teachfinder.utils;

public final class StringUtils {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.isBlank();
    }

    public static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty() && !str.isBlank();
    }
}
