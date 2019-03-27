package edu.eci.persistences.repositories;

import edu.eci.models.Car;
import org.springframework.stereotype.Repository;


@Repository
public interface ICarRepository extends DAO<Car, String> {
    Car getCarBylicencePlate(String licencePlate);
}