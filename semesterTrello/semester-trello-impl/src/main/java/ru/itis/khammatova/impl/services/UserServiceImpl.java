package ru.itis.khammatova.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.khammatova.api.dto.JwtSignUpForm;
import ru.itis.khammatova.api.dto.SignUpForm;
import ru.itis.khammatova.api.dto.UserDto;
import ru.itis.khammatova.api.dto.UserRestForm;
import ru.itis.khammatova.api.services.UserService;
import ru.itis.khammatova.impl.aspect.Log;
import ru.itis.khammatova.impl.entitys.User;
import ru.itis.khammatova.impl.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Log
    public boolean signUp(SignUpForm signUpForm) {
        User user = User.builder()
                .email(signUpForm.getEmail())
                .firstname(signUpForm.getFirstname())
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();

        if (signUpForm.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        }


        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            return false;
        } else {
            userRepository.save(user);
        }

        return true;
    }

    @Override
    public Optional<UserDto> signUp(UserRestForm userRestForm) {
        User user = User.builder()
                .email(userRestForm.getEmail())
                .firstname(userRestForm.getFirstname())
                .role(User.Role.USER)
                .password(passwordEncoder.encode(userRestForm.getPassword()))
                .state(User.State.ACTIVE)
                .build();


        if (userRepository.findByEmail(userRestForm.getEmail()).isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(userRepository.save(user))
                    .map(userDb -> modelMapper.map(userDb, UserDto.class));
        }
    }

    @Override
    public Optional<UserDto> userByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<UserDto> userById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<UserDto> update(UserRestForm userRestForm, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return Optional.empty();
        }

        User user = optionalUser.get();

        user.setFirstname(userRestForm.getFirstname());
        user.setEmail(userRestForm.getEmail());
        user.setPassword(passwordEncoder.encode(userRestForm.getPassword()));

        return  Optional.of(userRepository.save(user))
                    .map(userDb -> modelMapper.map(userDb, UserDto.class));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean signUp(JwtSignUpForm jwtSignUpForm) {
        User user = User.builder()
                .email(jwtSignUpForm.getEmail())
                .firstname(jwtSignUpForm.getFirstname())
                .role(User.Role.USER)
                .password(passwordEncoder.encode(jwtSignUpForm.getPassword()))
                .state(User.State.ACTIVE)
                .build();


        if (userRepository.findByEmail(jwtSignUpForm.getEmail()).isPresent()) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public Optional<UserDto> getUserByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.map(u -> modelMapper.map(u, UserDto.class));
        }

        return Optional.empty();
    }

    @Override
    public Integer usersCount() {
        return userRepository.findAll().size();
    }
}
