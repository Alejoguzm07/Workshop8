package edu.eci.persistences;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@Qualifier("CarMemoryRepository")
public class CarMemoryRepository implements ICarRepository{

    public static List<Car> carsContainer;
    public static List<Car> getContainer(){
        if(CarMemoryRepository.carsContainer == null)
            CarMemoryRepository.carsContainer = new ArrayList<>();
        return CarMemoryRepository.carsContainer;
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        return (CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> brand.equals(c.getbrand()))
                .collect(toList()));
    }

    @Override
    public List<Car> findAll() {
        return CarMemoryRepository.getContainer();
    }

    @Override
    public Car find(String licencePlate) {
        Optional<Car> answer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> licencePlate.equals(c.getlicencePlate()))
                .findFirst();
        return answer.isPresent() ? answer.get() : null;
    }

    @Override
    public String save(Car entity) {
        CarMemoryRepository.getContainer().add(entity);
        return entity.getlicencePlate();
    }

    @Override
    public void update(Car entity) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .map(c -> c.getlicencePlate().equals(entity.getlicencePlate()) ? entity : c)
                .collect(toList());
    }

    @Override
    public void delete(Car o) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> !c.getlicencePlate().equals(o.getlicencePlate()))
                .collect(toList());
    }

    @Override
    public void remove(Long licencePlate) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> !c.getlicencePlate().equals(licencePlate))
                .collect(toList());
    }
}
