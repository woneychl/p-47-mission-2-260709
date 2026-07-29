package com.back.global;

public class AppConfig {
    public static String mode = "dev";

    public static void setTestMode() {
        mode = "test";
    }

    public static void setDevMode() {
        mode = "dev";
    }

    public static String getMode() {
        return mode;
    }
}
