package com.adammendak.obd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static com.adammendak.obd.Constants.*;

class DDLBootstrapService {

    static void checkDriver() {
        try {
            Class c = Class.forName(DRIVER_NAME);
            System.out.println("####[0/2]Driver:");
            System.out.println("####[0/2]Package = " + c.getPackage());
            System.out.println("####[0/2]Name = " + c.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("####[0/2]Exception = " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void createDBTables() {
        HashMap<String, String> tableNames = getTableNamesMap();

        // try with resources block, Connection and Statement implement AutoCloseable interface.
        try (
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                Statement statement = connection.createStatement()
        ) {
            System.out.println("####[1/2]Creating Database Tables Step");
            connection.setAutoCommit(true);

            tableNames.forEach((key, value) -> {
                try {
                    statement.execute(value);
                    System.out.println("####[1/2]Table " + key + " Created");
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("ORA-00955")) {
                        System.out.println("####[1/2]Table " + key + " already exist");
                    } else {
                        System.out.println("####Failed to execute");
                        ex.printStackTrace();
                    }
                }
            });

        } catch (SQLException ex) {
            System.out.println("####Failed to connect");
            ex.printStackTrace();
        }
        System.out.println("####[1/2]Database Creation Step Finished");
    }

    private static HashMap<String, String> getTableNamesMap() {
        HashMap<String, String> tableNames = new HashMap<>();
        tableNames.put("Przedmiot", CREATE_SUBJECT_SQL);
        tableNames.put("Uczen", CREATE_STUDENT_SQL);
        tableNames.put("Ocena", CREATE_GRADE_SQL);
        tableNames.put("Nauczyciel", CREATE_TEACHER_SQL);
        tableNames.put("Ocenianie", CREATE_GRADING_SQL);
        return tableNames;
    }
}
