package com.artur.orange_backend.service;

import com.artur.orange_backend.model.ConfirmationToken;
import com.artur.orange_backend.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void deleteByUserId(Long userId){
        confirmationTokenRepository.deleteByUserId(userId);
    }
}
