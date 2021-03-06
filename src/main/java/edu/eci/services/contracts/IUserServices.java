package edu.eci.services.contracts;

import edu.eci.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IUserServices {
    List<User> list();
    User create(User user);
    User get(UUID id);
    List<User> get(String name);
    User update(User user);
    UUID delete(UUID id);
}
