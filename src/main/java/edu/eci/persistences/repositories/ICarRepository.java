package edu.eci.persistences.repositories;

import edu.eci.models.Car;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICarRepository extends DAO<Car, String> {
    List<Car> getCarsByBrand(String brand);
}