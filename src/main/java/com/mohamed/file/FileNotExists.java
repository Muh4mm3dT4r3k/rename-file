package com.mohamed.file;

public class FileNotExists extends RuntimeException {
    public FileNotExists(String message) {
        super(message);
    }
}
