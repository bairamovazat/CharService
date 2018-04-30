package ru.ivmiit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivmiit.forms.UserRegistrationForm;
import ru.ivmiit.models.Role;
import ru.ivmiit.models.State;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.UsersRepository;

import java.util.UUID;

/**
 * 10.11.2017
 * RegistrationServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(UserRegistrationForm userForm) {
        UUID uuid = UUID.randomUUID();
        User newUser = User.builder()
                .login(userForm.getLogin())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .role(Role.USER)
                .state(State.ACTIVATED)
                .email(userForm.getEmail())
                .name(userForm.getName())
                .build();
        usersRepository.save(newUser);
    }
}
