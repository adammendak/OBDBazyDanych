package com.adammendak.obd;

import java.sql.SQLException;
import java.sql.Statement;

import static com.adammendak.obd.Constants.*;

class DMLBootstrapService {

    static void insertDummyData() throws SQLException {
        try (
                Statement statement = Main.connection.createStatement()
        ) {
            System.out.println("####[2/2]Database Inserting Dummy Data Step");
            System.out.println("####[2/2]Database Inserting Subject Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "OCENIANIE"));

            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "PRZEDMIOT"));
            for (int i = 1; i < 5; i++) {
                String sql = String.format(INSERT_TEMPLATE, "PRZEDMIOT",
                        i + ", '" + SUBJECTS[i - 1] + "'");
                statement.executeUpdate(sql);
            }

            System.out.println("####[2/2]Database Inserting Teacher Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "NAUCZYCIEL"));
            for (int i = 1; i < 5; i++) {
                String sql = String.format(INSERT_TEMPLATE, "NAUCZYCIEL",
                        i + ", '" + TEACHER_NAMES[i - 1] + "', '" + TEACHER_SURNAMES[i - 1] + "'");
                statement.executeUpdate(sql);
            }

            System.out.println("####[2/2]Database Inserting Grade Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "OCENA"));
            for (int i = 1; i < 7; i++) {
                String sql = String.format(INSERT_TEMPLATE, "OCENA",
                        i + ", '" + GRADE_DESCRIPTIVE[i - 1] + "', '" + GRADE_FLOAT[i - 1] + "'");
                statement.executeUpdate(sql);
            }

            System.out.println("####[2/2]Database Inserting Student Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "UCZEN"));
            for (int i = 1; i < 5; i++) {
                String sql = String.format(INSERT_TEMPLATE, "UCZEN",
                        i + ", '" + STUDENT_SURNAME[i - 1] + "', '" + STUDENT_NAME[i - 1] + "'");
                statement.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            System.out.println("####Failed to insert data into tables");
            ex.printStackTrace();
            throw ex;
        }
        System.out.println("####[2/2]Database Inserting Dummy Data Step Finished");
    }

}
