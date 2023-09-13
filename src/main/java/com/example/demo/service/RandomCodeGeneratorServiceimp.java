package com.example.demo.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class RandomCodeGeneratorServiceimp implements RandomCodeGeneratorService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final int CODE_LENGTH = 8;

    private static final SecureRandom secureRandom = new SecureRandom();

    public String generateRandomCode() {
        StringBuilder codeBuilder = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
