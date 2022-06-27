package com.hapm;

import com.hapm.dto.EmployeeDto;
import com.hapm.util.DbUtil;
import com.hapm.util.JsonUtil;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            DbUtil.getInstance().initDatabaseConnection();
            doSomething();
        } finally {
            DbUtil.getInstance().closeDatabaseConnection();
        }
    }

    public static void doSomething() {
        testInsert();
        testRead(4);
        //testUpdate();
        //testDelete(4);
    }

    private static void testDelete(Integer id) {
        try {
            boolean result = EmployeeDao.getInstance().delete(id);
            System.out.println(result ? "User deleted." : "User was not deleted.");
        } catch (RuntimeException e) {
            System.out.println("User not deleted: " + e.getMessage());
        }
    }

    private static void testUpdate() {
        String input = "{\"id\":14,\"name\":\"Gris Michigan\"}";
        EmployeeDto dto = JsonUtil.getInstance().mapToDto(input);
        try {
            boolean result = EmployeeDao.getInstance().update(dto);
            System.out.println(result ? "User updated." : "User was not updated.");
        } catch (RuntimeException e) {
            System.out.println("User not updated: " + e.getMessage());
        }
    }

    private static void testInsert() {
        String input = "{\"id\":4,\"name\":\"Gris\"}";
        EmployeeDto dto = JsonUtil.getInstance().mapToDto(input);
        try {
            EmployeeDao.getInstance().create(dto);
            System.out.println("User inserted.");
        } catch (RuntimeException e) {
            System.out.println("User not inserted: " + e.getMessage());
        }
    }

    private static void testRead(Integer id) {
        EmployeeDto dto = EmployeeDao.getInstance().read(id);
        if (dto != null) {
            String json = JsonUtil.getInstance().mapToString(dto);
            System.out.println("User found: " + json);
        } else {
            System.out.println("User not found, please try again.");
        }
    }

}