package edu.eci.persistences.repositories;

import edu.eci.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends DAO<User, UUID> {
    List<User> getUsersByUserName(String userName);
}
