package ru.ivmiit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.security.details.UserDetailsImpl;

/**
 * 10.11.2017
 * AuthenticationServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User getUserByAuthentication(Authentication authentication) {
        UserDetailsImpl currentUserDetails = (UserDetailsImpl)authentication.getPrincipal();
        User currentUserModel = currentUserDetails.getUser();
        Long currentUserId = currentUserModel.getId();
        return usersRepository.findOneById(currentUserId).get();
    }
}
