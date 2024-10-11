package com.large_file_processing.demo.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException() {
        super("File upload failed");
    }
    public FileUploadException(String message) {
        super(message);
    }
    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
    public FileUploadException(Throwable cause) {
        super(cause);
    }
}