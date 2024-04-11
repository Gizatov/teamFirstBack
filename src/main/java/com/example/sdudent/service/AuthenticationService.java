package com.example.sdudent.service;


import com.example.sdudent.config.JwtService;
import com.example.sdudent.dto.AuthenticationRequest;
import com.example.sdudent.dto.AuthenticationResponse;
import com.example.sdudent.dto.RegisterRequest;
import com.example.sdudent.entity.Role;
import com.example.sdudent.entity.User;
import com.example.sdudent.repository.RoleRepository;
import com.example.sdudent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        // Создайте объект пользователя на основе запроса
        var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())  // Предполагается, что у вас есть поле role в RegisterRequest
                .gender(request.getGender()) // Добавьте обработку поля gender
                .course(request.getCourse()) // Добавьте обработку поля course
                .faculty(request.getFaculty()) // Добавьте обработку поля faculty
                .student_id(request.getStudentId()) // Добавьте обработку поля studentId
                .build();

        // Сохраните пользователя в репозитории
        repository.save(user);

        // Создайте JWT токен для нового пользователя
        var jwtToken = jwtService.generateToken(user);

        // Верните ответ с токеном
        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().getRole_name())
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            // Проверяем, существует ли пользователь с указанным email
            var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Проверяем, совпадает ли введенный пароль с хэшированным паролем пользователя
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }

            // Если все проверки пройдены успешно, генерируем токен
            var jwtToken = jwtService.generateToken(user);

            // Возвращаем успешный ответ с JWT-токеном
            return AuthenticationResponse.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole().getRole_name())
                    .token(jwtToken)
                    .build();
        } catch (AuthenticationException e) {
            // Обработка ошибок аутентификации
            throw new BadCredentialsException("Invalid email or password", e);
        }
    }



    public List<User> getAll(){
        return repository.findAll();
    }
}
