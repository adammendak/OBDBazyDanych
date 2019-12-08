package com.adammendak.obd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.adammendak.obd.Constants.*;

class DMLBootstrapService {

    private static final String INSERT_TEMPLATE = "INSERT INTO %s VALUES(%s)";
    private static final String TRUNCATE_TEMPLATE = "TRUNCATE TABLE %s";

    private static final String[] SUBJECTS = {"BIOLOGIA", "MATEMATYKA", "POLSKI", "ANGIELSKI"};
    private static final String[] TEACHER_NAMES = {"ADAM", "MACIEK", "RENATA", "ALICJA"};
    private static final String[] TEACHER_SURNAMES = {"ABACKI", "BABACKI", "CABACKA", "DABACKA"};
    private static final String[] GRADE_DESCRIPTIVE = {"NIEDOSTATECZNY", "MIERNY", "DOSTATECZNY", "DOBRY", "BARDZO DOBRY", "CELUJACY"};
    private static final String[] GRADE_FLOAT = {"1.0", "2.0", "3.0", "4.0", "5.0", "6.0"};
    private static final String[] STUDENT_SURNAME = {"EBACKI", "FABACKI", "GABACKA", "HABACKA"};
    private static final String[] STUDENT_NAME = {"HUBERT", "MARIAN", "JOLA", "EDYTA"};

    static void insertDummyData() {
        try (
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                Statement statement = connection.createStatement()
        ) {
            System.out.println("####[2/2]Database Inserting Dummy Data Step");
            System.out.println("####[2/2]Database Inserting Subject Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "PRZEDMIOT"));
            for(int i=1; i < 5; i++) {
                String sql = String.format(INSERT_TEMPLATE, "PRZEDMIOT",
                        i + ", '" + SUBJECTS[i-1] + "'");
                statement.executeUpdate(sql);
            }

            System.out.println("####[2/2]Database Inserting Teacher Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "NAUCZYCIEL"));
            for(int i=1; i < 5; i++) {
                String sql = String.format(INSERT_TEMPLATE, "NAUCZYCIEL",
                        i + ", '" + TEACHER_NAMES[i-1] + "', '" + TEACHER_SURNAMES[i-1] + "'");
                statement.executeUpdate(sql);
            }

            System.out.println("####[2/2]Database Inserting Grade Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "OCENA"));
            for(int i=1; i < 7; i++) {
                String sql = String.format(INSERT_TEMPLATE, "OCENA",
                        i + ", '" + GRADE_DESCRIPTIVE[i-1] + "', '" + GRADE_FLOAT[i-1] + "'");
                statement.executeUpdate(sql);
            }

            System.out.println("####[2/2]Database Inserting Student Data");
            statement.executeUpdate(String.format(TRUNCATE_TEMPLATE, "UCZEN"));
            for(int i=1; i < 5; i++) {
                String sql = String.format(INSERT_TEMPLATE, "UCZEN",
                        i + ", '" + STUDENT_SURNAME[i-1] + "', '" + STUDENT_NAME[i-1] + "'");
                statement.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            System.out.println("####Failed to connect");
            ex.printStackTrace();
        }
        System.out.println("####[2/2]Database Inserting Dummy Data Step Finished");
    }

}
