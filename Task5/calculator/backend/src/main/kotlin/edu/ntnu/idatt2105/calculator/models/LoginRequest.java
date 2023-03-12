package edu.ntnu.idatt2105.calculator.models;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginRequest {

    private String username;
    private String password;


}
