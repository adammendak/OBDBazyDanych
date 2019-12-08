package com.adammendak.obd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.adammendak.obd.Constants.*;

class DMLBootstrapService {

    private static final String INSERT_TEMPLATE = "INSERT INTO %s VALUES(%s, null)";
    private static final String[] SUBJECTS = {"BIOLOGIA", "MATEMATYKA", "POLSKI", "ANGIELSKI"};

    static void insertDummyData() {
        try (
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                Statement statement = connection.createStatement()
        ) {
            System.out.println("####[2/2]Database Inserting Dummy Data Step");

            for(int i=1; i < 4; i++) {
                String sql =
                        String.format(INSERT_TEMPLATE, "PRZEDMIOT", i + ", '" + SUBJECTS[i] + "'");
                statement.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            System.out.println("####Failed to connect");
            ex.printStackTrace();
        }
        System.out.println("####[2/2]Database Inserting Dummy Data Step Finished");
        //todo tutaj walczymy
    }

//        String sql1 = "INSERT INTO DZIALY(NR_DZIALU, NAZWA_DZIALU, SIEDZIBA) " +
//                " VALUES (1, 'DYREKCJA', 'ul.1 Stycznia 23')";
//        try {
//            Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
//            System.out.println("AutoCommit = " + connection.getAutoCommit());
//            System.out.println("Connection success.");
//            Statement statement = connection.createStatement();
//            System.out.println("Execute = " + statement.execute(sql1));
////            System.out.println("Execute = " + statement.executeUpdate(sql1));
//            statement.close();
//            connection.close();
//            System.out.println("Success.");
//        } catch (SQLException ex) {
//            System.out.println("Failed to connect.");
//            ex.printStackTrace();
//        }
}
