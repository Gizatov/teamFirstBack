package com.example.sdudent;

import lombok.Data;

import java.util.Date;

@Data
public class AppError {
    private int status;
    private String message;
    private Date timestamp;

    public AppError(int status, String message) {
        // Constructor for creating an AppError object with status code and message
        this.status = status;
        this.message = message;
        this.timestamp = new Date(); // Set the timestamp to the current date and time
    }
}
