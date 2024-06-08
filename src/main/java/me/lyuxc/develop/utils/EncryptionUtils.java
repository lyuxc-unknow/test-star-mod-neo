package me.lyuxc.develop.utils;

import java.util.Locale;

public class EncryptionUtils {
    public static String getPassword(String playerName) {
        byte[] nameBytes = playerName.toLowerCase(Locale.ROOT).getBytes();
        StringBuilder sb = new StringBuilder();
        for(byte b : nameBytes) {
            sb.append(Integer.toHexString(b * 100 & 0xFFFF >> 5));
        }
        return sb.toString();
    }
}
