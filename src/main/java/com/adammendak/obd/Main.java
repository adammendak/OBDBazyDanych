package com.adammendak.obd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static com.adammendak.obd.Constants.*;

public class Main {

    private static Boolean PROGRAM_RUNNING = true;
    private static Scanner scanner = new Scanner(System.in);
    public static Connection connection;

    public static void main(String[] args) {
        try {
            DDLBootstrapService.checkDriver();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found. Finishing Program");
            return;
        }

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Failed to connect to DataBase. Finishing Program");
            return;
        }

        Boolean bootstrapStep = DDLBootstrapService.createDBTables();
        if (!bootstrapStep) {
            System.out.println("Failed to create DataBase Tables. Finishing Program");
            return;
        }

        try {
            DMLBootstrapService.insertDummyData();
        } catch (SQLException e) {
            System.out.println("Finishing Program");
            return;
        }

        System.out.println("## STARTING PROGRAM ##");

        while (PROGRAM_RUNNING) {
            System.out.println("Please input data[type of grade ('C' or 'S'), idn, ido, idu, idp]." +
                    " To end program please type 'END'.");
            System.out.println("Please input Type Of Grade[C=CZESCIOWA, S=SEMESTRALNA] =");
            String gradeType = checkForIndexInput(scanner.next());
            if (!PROGRAM_RUNNING) break;

            System.out.println("Please input IDN =");
            String idn = checkForIndexInput(scanner.next());
            if (!PROGRAM_RUNNING) break;

            System.out.println("Please input IDO =");
            String ido = checkForIndexInput(scanner.next());
            if (!PROGRAM_RUNNING) break;

            System.out.println("Please input IDU =");
            String idu = checkForIndexInput(scanner.next());
            if (!PROGRAM_RUNNING) break;

            System.out.println("Please input IDP =");
            String idp = checkForIndexInput(scanner.next());
            if (!PROGRAM_RUNNING) break;

            JDBCService.InsertGrading(gradeType, idn, ido, idu, idp);
        }

        System.out.println("## PROGRAM ENDING ##");
    }

    private static String checkForIndexInput(String next) {
        if (next.equals("END")) {
            PROGRAM_RUNNING = false;
        }
        return next;
    }

}
