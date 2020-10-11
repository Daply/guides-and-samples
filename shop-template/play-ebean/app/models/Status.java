package models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Status implements Serializable {

    private String message;

    private StatusCode statusCode;

    public Status(String message, StatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
