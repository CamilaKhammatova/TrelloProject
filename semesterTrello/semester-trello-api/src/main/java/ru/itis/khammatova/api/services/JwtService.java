package ru.itis.khammatova.api.services;

public interface JwtService {
    String generateJwtToken(String email);
    String nameFromToken(String token);
    boolean validate(String token);
}
