package fr.efrei.teachfinder.utils;

import jakarta.ejb.Stateless;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class SHA256HashStrategy implements IHashStrategy {

    private MessageDigest digest;

    {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String hashString(String str) {
        byte[] hasedBytes = digest.digest(str.getBytes(StandardCharsets.ISO_8859_1));
        StringBuilder hasedStringBuilder = new StringBuilder(2 * hasedBytes.length);
        for (byte b : hasedBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hasedStringBuilder.append('0');
            }
            hasedStringBuilder.append(hex);
        }
        return hasedStringBuilder.toString();
    }
}
