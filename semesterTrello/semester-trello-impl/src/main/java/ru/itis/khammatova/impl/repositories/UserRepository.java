package ru.itis.khammatova.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.khammatova.api.dto.UserDto;
import ru.itis.khammatova.impl.entitys.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
