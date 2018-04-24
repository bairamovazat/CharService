package ru.ivmiit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.security.role.Role;
import ru.ivmiit.utils.PasswordGenerator;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 17.11.2017
 * AdminServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAllByRole(Role.USER);
    }

    @Transactional
    @Override
    public void createTempPassword(Long userId) {
        Optional<User> existedUser = usersRepository.findById(userId);

        // никому не говорите
        User admin = usersRepository.findOneById(1L).get();

        if (!existedUser.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = existedUser.get();

        String tempPassword = passwordGenerator.generate();

        user.setHashTempPassword(encoder.encode(tempPassword));

        usersRepository.save(user);

        executorService.submit(() -> {
            emailService.sendMail("<h1>" + tempPassword + "</h1>",
                    "Временный пароль для пользователя " + user.getLogin(),
                    admin.getEmail());
        });

    }
}
