package ru.ivmiit.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;

import java.util.Optional;

@Service
@NoArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UsersDao usersRepository;

    @Deprecated
    public RegistrationServiceImpl(UsersDao usersRepository){
        this.usersRepository = usersRepository;
    }
    @Override
    public void registerUser(User user) throws IllegalArgumentException {
        if(userIsRegistered(user.getName())){
           throw new IllegalArgumentException("User is registered");
        }
        usersRepository.save(user);
    }

    @Override
    public boolean userIsRegistered(String userName) {
        Optional<User> user = usersRepository.getUserByName(userName);
        return user.isPresent();
    }
}
