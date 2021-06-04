package com.oop.backend.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignupRequest extends LoginRequest {
    private String email;
    private Set<String> roles;
}
