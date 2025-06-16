package com.FABEE.app;

public class SecurityContext {
    private static String currentRole = "USER";

    public static void setRole(String role) {
        currentRole = role;
    }

    public static String getRole() {
        return currentRole;
    }
}