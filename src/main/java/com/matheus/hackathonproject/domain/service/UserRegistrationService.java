package com.matheus.hackathonproject.domain.service;

import com.matheus.hackathonproject.api.model.user.UserChangePasswordDto;
import com.matheus.hackathonproject.domain.exceptions.BusinessException;
import com.matheus.hackathonproject.domain.exceptions.EntityBeingUsedException;
import com.matheus.hackathonproject.domain.exceptions.UserNotFoundException;
import com.matheus.hackathonproject.domain.model.AccessLevel;
import com.matheus.hackathonproject.domain.model.User;
import com.matheus.hackathonproject.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserRegistrationService {

    private UserRepository userRepository;
    private AccessLevelRegistrationService accessLevelService;
    private PasswordEncoder passwordEncoder;

    public List<User> list() {
        return userRepository.findAll();
    }

    public List<User> searchAll() {
        return userRepository.findAll();
    }

    public User search(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public User insert(User user) {
        userRepository.detach(user);

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().equals(user)) {
            throw new BusinessException("Already exits a user with the email %s".formatted(user.getEmail()));
        }

        String encryptedPassword = encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    @Transactional
    public void exclude(Long userId) {
        try {
            userRepository.deleteById(userId);
            userRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityBeingUsedException("User", userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(userId);
        }
    }

    @Transactional
    public void alterPassword(Long userId, UserChangePasswordDto userChangePasswordDto) {
        User user = search(userId);

        if (!passwordEncoder.matches(userChangePasswordDto.getCurrentPassword(), user.getPassword())) {
            throw new BusinessException("The current password entered does not match the user's password");
        }

        String newPasswordEncrypted = encryptPassword(userChangePasswordDto.getNewPassword());
        user.setPassword(newPasswordEncrypted);
    }

    @Transactional
    public void associateAccessLevel(Long userId, Long accessLevelId) {
        User user = search(userId);
        AccessLevel accessLevel = accessLevelService.search(accessLevelId);

        user.addAccessLevel(accessLevel);
    }

    @Transactional
    public void disassociateAccessLevel(Long userId, Long accessLevelId) {
        User user = search(userId);
        AccessLevel accessLevel = accessLevelService.search(accessLevelId);

        user.removeAccessLevel(accessLevel);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
