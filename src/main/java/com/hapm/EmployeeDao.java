package com.hapm;

import com.hapm.dto.EmployeeDto;
import com.hapm.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {

    private static final EmployeeDao instance = new EmployeeDao();
    private Connection connection;

    private EmployeeDao() {
        this.connection = DbUtil.getInstance().getConnection();
    }

    public static EmployeeDao getInstance() {
        return instance;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM test.employee WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            connection.commit();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean update(EmployeeDto dto) {
        String sql = "UPDATE test.employee SET name=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getName());
            statement.setInt(2, dto.getId());
            int rowsUpdated = statement.executeUpdate();
            connection.commit();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void create(EmployeeDto dto) {
        String sql = "INSERT INTO test.employee (id,name) VALUES (?,?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public EmployeeDto read(Integer id) {
        EmployeeDto dto = null;
        String sql = "SELECT id, name FROM test.employee where id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dto = new EmployeeDto();
                dto.setId(id);
                dto.setName(resultSet.getString("name"));
            }
            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
