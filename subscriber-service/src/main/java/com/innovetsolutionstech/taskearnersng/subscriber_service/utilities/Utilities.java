package com.innovetsolutionstech.taskearnersng.subscriber_service.utilities;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Utilities {
    public static String hashPINSecret(String pinNumber) {
        return BCrypt.hashpw(pinNumber, BCrypt.gensalt());
    }

    public static boolean validateAccessCode(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
