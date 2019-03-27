package edu.eci.services.contracts;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICarServices {
    List<User> list();
    User create(Car car);
    User get(String licencePlate);
    User get(String brand);
}
