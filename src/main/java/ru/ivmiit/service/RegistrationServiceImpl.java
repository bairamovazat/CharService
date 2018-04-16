package ru.ivmiit.service;

import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;

import java.util.Optional;

public class RegistrationServiceImpl implements RegistrationService {
    private UsersDao usersRepository;

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
