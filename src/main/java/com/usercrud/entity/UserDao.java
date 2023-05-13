package com.usercrud.entity;

import com.usercrud.exception.UserCannotBeCreated;
import com.usercrud.exception.UserNotFoundException;
import com.usercrud.utils.DbUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String CREATE_QUERY = "INSERT INTO users(email, username, password) VALUES (?, ?, ?);";
    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?;";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?;";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, username = ? , password = ? WHERE id = ?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE email = ?;";
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users;";

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User createUser(User user) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            String hashed = hashPassword(user.getPassword());
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashed);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(1));
                    return user;
                } else {
                    throw new UserCannotBeCreated(user.getEmail(), user.getUserName(), hashed);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User read(String email) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User()
                            .setIdUser(resultSet.getLong("id"))
                            .setEmailUser(resultSet.getString("email"))
                            .setUserNameUser(resultSet.getString("username"))
                            .setPasswordUser(resultSet.getString("password"));
                } else {
                    throw new UserNotFoundException(email);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User read(long id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User()
                            .setIdUser(resultSet.getLong("id"))
                            .setEmailUser(resultSet.getString("email"))
                            .setUserNameUser(resultSet.getString("username"))
                            .setPasswordUser(resultSet.getString("password"));
                } else {
                    throw new UserNotFoundException(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setLong(4, user.getId());
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String email) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY)
        ) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserNotFoundException(email);
        }
    }

    public List<User> findAll() {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_QUERY)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User()
                            .setIdUser(resultSet.getLong("id"))
                            .setEmailUser(resultSet.getString("email"))
                            .setUserNameUser(resultSet.getString("username"))
                            .setPasswordUser(resultSet.getString("password"));
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
