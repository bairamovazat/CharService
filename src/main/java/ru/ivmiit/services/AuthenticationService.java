package ru.ivmiit.services;

import org.springframework.security.core.Authentication;
import ru.ivmiit.models.User;

/**
 * 10.11.2017
 * AuthenticationService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface AuthenticationService {
    User getUserByAuthentication(Authentication authentication);
}
