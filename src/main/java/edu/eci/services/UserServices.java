package edu.eci.services;

import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import edu.eci.services.contracts.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserServices implements IUserServices{

    @Autowired
    @Qualifier("UserPostgresRepository")
    private IUserRepository userRepository;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        if(null == user.getId())
            throw new RuntimeException("Id invalid");
        else if(userRepository.find(user.getId()) != null)
            throw new RuntimeException("The user exists");
        else
            userRepository.save(user);
        return user;
    }

    @Override
    public User get(UUID id) {
        return userRepository.find(id);
    }

    @Override
    public List<User> get(String name) {
        return userRepository.getUsersByUserName(name);
    }

    @Override
    public User update(User user) {
        if(null == user.getId())
            throw new RuntimeException("Id invalid");
        else if(userRepository.find(user.getId()) == null)
            throw new RuntimeException("The user does not exists");
        else
            userRepository.update(user);
        return user;
    }

    @Override
    public UUID delete(UUID id) {
        if(null == id)
            throw new RuntimeException("Id invalid");
        else if(userRepository.find(id) == null)
            throw new RuntimeException("The user does not exists");
        else
            userRepository.delete(userRepository.find(id));
        return id;
    }
}
