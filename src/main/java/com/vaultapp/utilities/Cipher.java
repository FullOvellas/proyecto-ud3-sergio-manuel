package com.vaultapp.utilities;


import org.jasypt.util.text.StrongTextEncryptor;

public class Cipher {
    private StrongTextEncryptor cipher;
    private static final Cipher instance;

    static {
        instance = new Cipher();
    }

    public Cipher() {
        cipher = new StrongTextEncryptor();
        cipher.setPassword("seed");
    }

    public static Cipher getInstance() {
        return instance;
    }

    public String encrypt(String str) {
        return cipher.encrypt(str);
    }

    public String decrypt(String str) {
        return cipher.decrypt(str);
    }

}
