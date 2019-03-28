package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements ICarRepository {

    private String dbUrl = "jdbc:postgresql://ec2-75-101-131-79.compute-1.amazonaws.com:5432/dcit1cb6m8r7e6?user=yclmwguuhbsmol&password=279a4bf38f44cb63e47a65e3f70f6c658c178ab76d089245e8635e0e1a383afd&sslmode=require\n";

    @Autowired
    private DataSource dataSource2;

    @Override
    public List<Car> getCarsByBrand(String brand) {
        String query = "SELECT * FROM cars WHERE brand = '" + brand + "'";
        List<Car> cars=new ArrayList<>();
        try(Connection connection = dataSource2.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Car car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setlicencePlate(rs.getString("licencePlate"));
                cars.add(car);
            }
            return cars;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> findAll() {
        String query = "SELECT * FROM cars";
        List<Car> cars=new ArrayList<>();

        try(Connection connection = dataSource2.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Car car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setlicencePlate(rs.getString("licencePlate"));
                cars.add(car);
            }
            return cars;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car find(String licencePlate) {
        String query = "SELECT * FROM cars WHERE licencePlate = '" + licencePlate + "'";
        try(Connection connection = dataSource2.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = null;
            if (rs.next()){
                car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setlicencePlate(rs.getString("licencePlate"));
            }
            return car;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String save(Car entity) {
        String query = "INSERT INTO cars (licencePlate,brand) VALUES ('" + entity.getlicencePlate() + "','"+ entity.getbrand() +"')";
        try(Connection connection = dataSource2.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = null;
            if (rs.next()){
                car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setlicencePlate(rs.getString("licencePlate"));
            }
            return entity.getlicencePlate();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car entity) {
        String query = "UPDATE cars SET licencePlate = '"+ entity.getlicencePlate() +"',brand = '" + entity.getbrand()+ "' WHERE licencePlate = '" + entity.getlicencePlate() + "'";
        try(Connection connection = dataSource2.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = null;
            if (rs.next()){
                car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setlicencePlate(rs.getString("licencePlate"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Car o) {
        String query = "DELETE FROM cars WHERE licencePlate = '"+ o.getlicencePlate() +"'";
        try(Connection connection = dataSource2.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = null;
            if (rs.next()){
                car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setlicencePlate(rs.getString("licencePlate"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Long id) {
        String query = "DELETE FROM cars WHERE licencePlate = '"+ id +"'";
        try(Connection connection = dataSource2.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = null;
            if (rs.next()){
                car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setlicencePlate(rs.getString("licencePlate"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DataSource dataSource2() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
}
