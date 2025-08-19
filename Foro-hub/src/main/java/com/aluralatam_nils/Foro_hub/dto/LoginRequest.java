package com.aluralatam_nils.Foro_hub.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}