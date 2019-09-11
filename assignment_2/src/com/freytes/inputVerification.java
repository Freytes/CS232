package com.freytes;

public class inputVerification {
    public boolean specialCharacters(String individualChar) {
        return (individualChar == null) || "".equals(individualChar.trim()) || individualChar.matches(("\\d+"));
    }
}
