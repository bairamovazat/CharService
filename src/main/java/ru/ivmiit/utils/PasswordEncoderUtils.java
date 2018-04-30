package ru.ivmiit.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtils {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static PasswordEncoder getBCryptPasswordEncoder(){
        return passwordEncoder;
    }
}
