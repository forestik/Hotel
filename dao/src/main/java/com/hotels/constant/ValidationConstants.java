package com.hotels.constant;

public final class ValidationConstants {
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 30;
    public static final String INVALID_EMAIL = "The email is invalid";
    public static final String INVALID_USERNAME =
        "Username must not contains a dot at the beginning, few dots in a row, special characters, Cyrillic letters";
    public static final String INVALID_PASSWORD =
        "Password has contain at least one character of Uppercase letter (A-Z), Lowercase letter (a-z), Digit (0-9), " +
            "pecial character (~`!@#$%^&*()+=_-{}[]|:;\\\\�\\\\�?/<>,.)";

    private ValidationConstants() {
    }
}
