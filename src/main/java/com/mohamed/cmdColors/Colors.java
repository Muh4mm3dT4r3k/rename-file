package com.mohamed.cmdColors;

public enum Colors {
    RED("\u001B[91m"),
    GREEN("\u001B[92m"),
    RESET("\u001B[0m");
    final String code;
    Colors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
