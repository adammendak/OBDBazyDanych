package com.adammendak.obd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String LOGIN_PASSWORD = "amendak";

    public static void main(String[] args) {
        checkDriver();
        connection();
    }

    private static void checkDriver() {
        try {
            Class c = Class.forName(DRIVER_NAME);
            System.out.println("Driver Added.");
            System.out.println("Package = " + c.getPackage());
            System.out.println("Name = " + c.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception = " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void connection() {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN_PASSWORD, LOGIN_PASSWORD);
            System.out.println("AutoCommit = " + connection.getAutoCommit());
            System.out.println("Connection success.");
        } catch (SQLException ex) {
            System.out.println("Failed to connect.");
            ex.printStackTrace();
        }
    }
}
