package edu.eci.services.contracts;

import edu.eci.models.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICarServices {
    List<Car> list();
    Car create(Car car);
    Car get(String licencePlate);
    List<Car> getCarByBrand(String brand);
    Car update(Car car);
    String delete(String licencePlate);
}
