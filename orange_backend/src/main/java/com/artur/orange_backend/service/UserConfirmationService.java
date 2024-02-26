package com.artur.orange_backend.service;

import com.artur.orange_backend.exception.ConfirmationTokenConfirmedException;
import com.artur.orange_backend.exception.ConfirmationTokenExpiredException;
import com.artur.orange_backend.exception.ConfirmationTokenNotFoundException;
import com.artur.orange_backend.model.ConfirmationToken;
import com.artur.orange_backend.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserConfirmationService {
    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;
    private EmailService emailService;

    public void updateUserById(Long userId, User user) {
        Optional<ConfirmationToken> confirmationTokenOptional = userService.updateUserById(userId, user);
        if (confirmationTokenOptional.isPresent()){
            ConfirmationToken confirmationToken = confirmationTokenOptional.get();
            confirmationTokenService.saveConfirmationToken(confirmationToken);

            String token = confirmationToken.getToken();
            String link = "http://localhost:8080/api/registration/confirm?token=" + token;
            emailService.sendConfirmationEmail(
                    user.getEmail(),
                    user.getName(),
                    link
            );
        }
    }

    public void addUser(User user) {
        ConfirmationToken confirmationToken = userService.addUser(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String token = confirmationToken.getToken();
        String link = "http://localhost:8080/api/registration/confirm?token=" + token;
        emailService.sendConfirmationEmail(
                user.getEmail(),
                user.getName(),
                link
        );
    }

    @Transactional
    public void confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new ConfirmationTokenNotFoundException(token));

        if (confirmationToken.getConfirmedAt() != null){
            throw new ConfirmationTokenConfirmedException(token);
        }

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new ConfirmationTokenExpiredException(token);
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        userService.enableUser(confirmationToken.getUser().getEmail());
    }
}
