package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
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
import java.util.UUID;

@Component
@Qualifier("UserPostgresRepository")
public class UserPostgresRepository implements IUserRepository {

    private String dbUrl = "jdbc:postgresql://ec2-75-101-131-79.compute-1.amazonaws.com:5432/dcit1cb6m8r7e6?user=yclmwguuhbsmol&password=279a4bf38f44cb63e47a65e3f70f6c658c178ab76d089245e8635e0e1a383afd&sslmode=require\n";

    @Autowired
    private DataSource dataSource;

    @Override
    public List<User> getUsersByUserName(String userName) {
        String query = "SELECT * FROM users WHERE name = '" + userName+ "'";
        List<User> users=new ArrayList<>();
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = null;
            while (rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users=new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(UUID id) {
        String query = "SELECT * FROM users WHERE id = '" + id + "'";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = null;
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
            }             
            return user;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID save(User entity) {
        String query = "INSERT INTO users (id,name) VALUES ('" + entity.getId() + "','"+entity.getName()+"')";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = null;
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
            }
            return entity.getId();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE users SET id = '"+ entity.getId() +"',name = '" + entity.getName()+ "' WHERE id = '" + entity.getId() + "'";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = null;
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User o) {
        String query = "DELETE FROM users WHERE id = '"+ o.getId() +"'";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = null;
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Long id) {
        String query = "DELETE FROM users WHERE id = '"+ id +"'";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = null;
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
}
