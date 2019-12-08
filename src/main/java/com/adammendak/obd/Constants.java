package com.adammendak.obd;

public class Constants {
    static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    //    private static final String URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String LOGIN = "amendak";
    static final String PASSWORD = "Warahi1986";

    static final String CREATE_SUBJECT_SQL =
            " CREATE TABLE PRZEDMIOT( " +
                    " IDP NUMBER NOT NULL, " +
                    " NAZWA_PRZEDMIOTU VARCHAR2(20 CHAR) NOT NULL, " +
                    " ID_OCENIANIE NUMBER)";

    static final String CREATE_STUDENT_SQL =
            " CREATE TABLE UCZEN( " +
                    " IDU NUMBER NOT NULL, " +
                    " NAZWISKO_UCZNIA VARCHAR2(30 CHAR) NOT NULL , " +
                    " IMIE_UCZNIA VARCHAR2(20 CHAR) NOT NULL, " +
                    " ID_OCENIANIE NUMBER)";

    static final String CREATE_GRADE_SQL =
            " CREATE TABLE OCENA ( " +
                    " IDO NUMBER NOT NULL, " +
                    " WARTOSCI_OPISOWA VARCHAR2(20 CHAR) NOT NULL, " +
                    " WARTOSC_NUMERYCZNA FLOAT NOT NULL," +
                    " ID_OCENIANIE NUMBER)";

    static final String CREATE_TEACHER_SQL =
            " CREATE TABLE NAUCZYCIEL ( " +
                    " IDN NUMBER NOT NULL, " +
                    " NAZWISKO_NAUCZYCIELA VARCHAR2(30 CHAR) NOT NULL, " +
                    " IMIE_NAUCZYCIELA VARCHAR2(20 CHAR) NOT NULL, " +
                    " ID_OCENIANIE NUMBER)";

    static final String CREATE_GRADING_SQL =
            " CREATE TABLE OCENIANIE ( " +
                    " RODZAJ_OCENY CHAR(1) NOT NULL, " +
                    " IDN NUMBER NOT NULL, " +
                    " IDO NUMBER NOT NULL, " +
                    " IDU NUMBER NOT NULL, " +
                    " IDP NUMBER NOT NULL)";
}
