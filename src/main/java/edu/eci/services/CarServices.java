package edu.eci.services;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarServices implements ICarServices {

    @Autowired
    @Qualifier("CarMemoryRepository")
    private ICarRepository carRepository;

    @Override
    public List<Car> list() {
        return carRepository.findAll();
    }

    @Override
    public Car create(Car car) {
        if(null == car.getlicencePlate())
            throw new RuntimeException("licencePlate invalid");
        else if(carRepository.find(car.getlicencePlate()) != null)
            throw new RuntimeException("The car exists");
        else
            carRepository.save(car);
        return car;
    }

    @Override
    public Car get(String licencePlate) {
        return carRepository.find(licencePlate);
    }

    @Override
    public List<Car> getCarByBrand(String brand) {
        return carRepository.getCarsByBrand(brand);
    }

    @Override
    public Car update(Car car) {
        if(null == car.getlicencePlate())
            throw new RuntimeException("licence Plate invalid");
        else if(carRepository.find(car.getlicencePlate()) == null)
            throw new RuntimeException("The car does not exists");
        else
            carRepository.update(car);
        return car;
    }

    @Override
    public String delete(String licencePlate) {
        if(null == licencePlate)
            throw new RuntimeException("licence Plate invalid");
        else if(carRepository.find(licencePlate) == null)
            throw new RuntimeException("The car does not exists");
        else
            carRepository.delete(carRepository.find(licencePlate));
        return licencePlate;
    }
}
