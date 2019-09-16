package com.freytes;

public class inputVerification {
    public boolean inputVerification(String ln1) {
        return (!(ln1 == null) || "".equals(ln1.trim()) || ln1.matches(("\\d+")));
    }

}
