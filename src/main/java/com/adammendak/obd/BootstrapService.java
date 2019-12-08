package com.adammendak.obd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.adammendak.obd.DBConstants.*;

public class BootstrapService {

    private static final String createSubjectSql =
            " CREATE TABLE PRZEDMIOT( " +
            " IDP NUMBER NOT NULL, " +
            " NAZWA_PRZEDMIOTU VARCHAR2(20 CHAR) NOT NULL, " +
            " ID_OCENIANIE NUMBER NOT NULL)";

    private static final String createStudentSql =
            " CREATE TABLE UCZEN( " +
            " IDU NUMBER NOT NULL, " +
            " NAZWISKO_UCZNIA VARCHAR2(30 CHAR) NOT NULL , " +
            " IMIE_UCZNIA VARCHAR2(20 CHAR) NOT NULL, " +
            " ID_OCENIANIE NUMBER NOT NULL)";

    private static final String createGradeSql =
            " CREATE TABLE OCENA ( " +
            " IDO NUMBER NOT NULL, " +
            " WARTOSCI_OPISOWA VARCHAR2(20 CHAR) NOT NULL, " +
            " WARTOSC_NUMERYCZNA FLOAT NOT NULL," +
            " ID_OCENIANIE NUMBER NOT NULL)";

    private static final String createTeacherSql =
            " CREATE TABLE TEACHER ( " +
            " IDN NUMBER NOT NULL, " +
            " NAZWISKO_NAUCZYCIELA VARCHAR2(30 CHAR) NOT NULL, " +
            " IMIE_NAUCZYCIELA VARCHAR2(20 CHAR) NOT NULL, " +
            " ID_OCENIANIE NUMBER NOT NULL)";

    private static final String createGradingSql =
            " CREATE TABLE OCENIANIE ( " +
            " RODZAJ_OCENY CHAR(1) NOT NULL, " +
            " IDN NUMBER NOT NULL, " +
            " IDO NUMBER NOT NULL, " +
            " IDU NUMBER NOT NULL, " +
            " IDP NUMBER NOT NULL)";

    public static void createDBTables() {
        try (
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            Statement statement = connection.createStatement();
        ) {
            System.out.println("####[1/2]Creating Database Tables.");
            connection.setAutoCommit(true);
            statement.execute(createSubjectSql);
            statement.execute(createStudentSql);
            statement.execute(createGradeSql);
            statement.execute(createTeacherSql);
            statement.execute(createGradingSql);
            System.out.println("####[1/2]Database Tables Created.");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("ORA-00955")) {
                System.out.println("####[1/2]Tables already exist.");
            } else {
                System.out.println("Failed to connect.");
                ex.printStackTrace();
            }
        }
    }
}
