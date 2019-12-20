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
            System.out.println("Please input data[type of grade case sensitive('C' or 'S'), idn, ido, idu, idp]." +
                    " To end program please type 'END'.");
            System.out.println("Please input Type Of Grade[C=CZESCIOWA, S=SEMESTRALNA] =");
            String gradeInput = scanner.next();
            checkForEndInput(gradeInput);
            if (!PROGRAM_RUNNING) break;
            if (!gradeInput.equals("C") && !gradeInput.equals("S")) {
                System.out.println("Wrong input data. Try again");
                continue;
            }

            System.out.println("Please input IDN =");
            String idnInput = scanner.next();
            checkForEndInput(idnInput);
            if (!PROGRAM_RUNNING) break;
            try {
                Integer.parseInt(idnInput);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input data, only numbers. Try again");
                continue;
            }

            System.out.println("Please input IDO =");
            String idoInput = scanner.next();
            checkForEndInput(idoInput);
            if (!PROGRAM_RUNNING) break;
            try {
                Integer.parseInt(idoInput);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input data, only numbers. Try again");
                continue;
            }

            System.out.println("Please input IDU =");
            String iduInput = scanner.next();
            checkForEndInput(iduInput);
            if (!PROGRAM_RUNNING) break;
            try {
                Integer.parseInt(iduInput);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input data, only numbers. Try again");
                continue;
            }

            System.out.println("Please input IDP =");
            String idpInput = scanner.next();
            checkForEndInput(idpInput);
            if (!PROGRAM_RUNNING) break;
            try {
                Integer.parseInt(idpInput);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input data, only numbers. Try again");
                continue;
            }

            try {
                JDBCService.InsertGrading(gradeInput, idnInput, idoInput, iduInput, idpInput);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Finishing program");
                return;
            }
        }

        System.out.println("## PROGRAM ENDING ##");
    }

    private static String checkForEndInput(String next) {
        if (next.equals("END")) {
            PROGRAM_RUNNING = false;
        }
        return next;
    }

}
