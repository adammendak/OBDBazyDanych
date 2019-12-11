package com.adammendak.obd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static com.adammendak.obd.Constants.*;

class DDLBootstrapService {

    public static Boolean bootstrapSuccess = true;

    @SuppressWarnings("unchecked")
    static void checkDriver() throws ClassNotFoundException {
        Class<OracleDriver> c = (Class<OracleDriver>) Class.forName(DRIVER_NAME);
        System.out.println("####[0/2]Driver:");
        System.out.println("####[0/2]Package = " + c.getPackage());
        System.out.println("####[0/2]Name = " + c.getName());
    }

    static Boolean createDBTables() {
        HashMap<String, String> tableNames = getTableNamesMap();

        System.out.println("####[1/2]Creating Database Tables Step");

        tableNames.forEach((key, value) -> {
            try (
                    Statement statement = Main.connection.createStatement()
            ) {
                statement.execute(value);
                System.out.println("####[1/2]Table " + key + " Created");
            } catch (SQLException ex) {
                if (ex.getMessage().contains("ORA-00955")) {
                    System.out.println("####[1/2]Table " + key + " already exist");
                } else {
                    System.out.println("####Failed to execute");
                    ex.printStackTrace();
                    bootstrapSuccess = false;
                }
            }
        });

        if (bootstrapSuccess) {
            System.out.println("####[1/2]Database Creation Step Finished");
        } else {
            System.out.println("####[1/2]Database Creation Step Failed");
        }
        return bootstrapSuccess;
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
