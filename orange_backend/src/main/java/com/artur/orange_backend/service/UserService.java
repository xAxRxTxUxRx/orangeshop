package com.artur.orange_backend.service;

import com.artur.orange_backend.exception.EmailTakenException;
import com.artur.orange_backend.exception.EntityNotFoundByIdException;
import com.artur.orange_backend.exception.UserNotFoundByEmailException;
import com.artur.orange_backend.model.ConfirmationToken;
import com.artur.orange_backend.model.Order;
import com.artur.orange_backend.model.User;
import com.artur.orange_backend.model.utils.user.UserRole;
import com.artur.orange_backend.repository.UserRepository;
import com.artur.orange_backend.security.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private OrderService orderService;
    private BCryptPasswordEncoder passwordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundByEmailException(username));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.orElseThrow(() -> new EntityNotFoundByIdException("User", userId));
    }

    public ConfirmationToken addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new EmailTakenException(user.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.USER);

        userRepository.save(user);

        return generateToken(user);
    }

    public Optional<ConfirmationToken> updateUserById(Long userId, User user) {
        User userToUpdate = getUserById(userId);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        userToUpdate.setName(user.getName());
        userToUpdate.setPassword(encodedPassword);
        userToUpdate.setProfileImg(user.getProfileImg());

        boolean emailUpdated = false;
        if (!Objects.equals(user.getEmail(), userToUpdate.getEmail())){
            if (userRepository.existsByEmail(user.getEmail())){
                throw new EmailTakenException(user.getEmail());
            }

            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setEnabled(false);
            emailUpdated = true;
        }

        userRepository.save(userToUpdate);

        if (emailUpdated){
            ConfirmationToken confirmationToken = generateToken(userToUpdate);
            return Optional.of(confirmationToken);
        }else{
            return Optional.empty();
        }
    }

    public void addOrderToUserById(Long orderId, Long userId) {
        User user = getUserById(userId);
        Order order = orderService.getOrderById(orderId);

        user.addOrder(order);

        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new EntityNotFoundByIdException("User", userId);
        }

        confirmationTokenService.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }

    public void enableUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundByEmailException(email));

        user.setEnabled(true);
        userRepository.save(user);
    }

    private ConfirmationToken generateToken(User user){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setToken(token);
        confirmationToken.setUser(user);

        return confirmationToken;
    }
}
