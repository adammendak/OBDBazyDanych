package com.adammendak.obd;

import static com.adammendak.obd.DBConstants.*;

public class Main {

    public static void main(String[] args) {
        checkDriver();
        BootstrapService.createDBTables();
//        createTable();
    }

    private static void checkDriver() {
        try {
            Class c = Class.forName(DRIVER_NAME);
            System.out.println("Driver:");
            System.out.println("Package = " + c.getPackage());
            System.out.println("Name = " + c.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception = " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTable() {
//        String sql1 = "CREATE TABLE DZIALY (NR_DZIALU INTEGER NOT NULL, " +
//                " NAZWA_DZIALU VARCHAR2(30) NOT NULL, " +
//                " SIEDZIBA VARCHAR2(50) NOT NULL)";
//        String sql1 = "INSERT INTO DZIALY(NR_DZIALU, NAZWA_DZIALU, SIEDZIBA) " +
//                " VALUES (1, 'DYREKCJA', 'ul.1 Stycznia 23')";
//        String sql1 = "UPDATE DZIALY SET NR_DZIALU = 10 WHERE NR_DZIALU = 1";
//        String sql1 = "DELETE FROM DZIALY WHERE NR_DZIALU = 1";
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
}
