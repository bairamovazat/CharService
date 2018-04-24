package ru.ivmiit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivmiit.forms.UserRegistrationForm;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.security.role.Role;
import ru.ivmiit.security.states.State;

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

    @Autowired
    private EmailService emailService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(UserRegistrationForm userForm) {
        UUID uuid = UUID.randomUUID();
        User newUser = User.builder()
                .login(userForm.getLogin())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .email(userForm.getEmail())
                .phone(userForm.getPhone())
                .name(userForm.getName())
                .uuid(uuid)
                .build();
        emailService.sendMail("Здравствуйте, чтобы продтвердить аккаунт перейдите по: http://localhost:8080/confirm/" + uuid,"Подтверждение аккаунта",userForm.getEmail());
        usersRepository.save(newUser);
    }
}
