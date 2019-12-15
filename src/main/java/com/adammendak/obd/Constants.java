package com.adammendak.obd;

public class Constants {
    static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    static final String URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    static final String LOGIN = "amendak";
    static final String PASSWORD = "amendak";

    static final String INSERT_TEMPLATE = "INSERT INTO %s VALUES(%s)";
    static final String TRUNCATE_TEMPLATE = "TRUNCATE TABLE %s";
    static final String SELECT_TEMPLATE = "SELECT %s FROM %s WHERE %s = %s";

    static final String[] SUBJECTS = {"BIOLOGIA", "MATEMATYKA", "POLSKI", "ANGIELSKI"};
    static final String[] TEACHER_NAMES = {"ADAM", "MACIEK", "RENATA", "ALICJA"};
    static final String[] TEACHER_SURNAMES = {"ABACKI", "BABACKI", "CABACKA", "DABACKA"};
    static final String[] GRADE_DESCRIPTIVE = {"NIEDOSTATECZNY", "NIEDOSTATECZNY", "MIERNY", "MIERNY", "DOSTATECZNY",
            "DOSTATECZNY", "DOBRY", "DOBRY", "BARDZO DOBRY", "BARDZO DOBRY", "CELUJACY"};
    static final String[] GRADE_FLOAT = {"1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0", "4.5", "5.0", "5.5", "6.0"};
    static final String[] STUDENT_SURNAME = {"EBACKI", "FABACKI", "GABACKA", "HABACKA"};
    static final String[] STUDENT_NAME = {"HUBERT", "MARIAN", "JOLA", "EDYTA"};

    static final String CREATE_SUBJECT_SQL =
            " CREATE TABLE PRZEDMIOT( " +
                    " IDP NUMBER NOT NULL, " +
                    " NAZWA_PRZEDMIOTU CHAR(20) NOT NULL)";

    static final String CREATE_STUDENT_SQL =
            " CREATE TABLE UCZEN( " +
                    " IDU NUMBER NOT NULL, " +
                    " NAZWISKO_UCZNIA CHAR(30) NOT NULL , " +
                    " IMIE_UCZNIA CHAR(20) NOT NULL)";

    static final String CREATE_GRADE_SQL =
            " CREATE TABLE OCENA ( " +
                    " IDO NUMBER NOT NULL, " +
                    " WARTOSCI_OPISOWA CHAR(20) NOT NULL, " +
                    " WARTOSC_NUMERYCZNA FLOAT NOT NULL)";

    static final String CREATE_TEACHER_SQL =
            " CREATE TABLE NAUCZYCIEL ( " +
                    " IDN NUMBER NOT NULL, " +
                    " NAZWISKO_NAUCZYCIELA CHAR(30) NOT NULL, " +
                    " IMIE_NAUCZYCIELA CHAR(20) NOT NULL)";

    static final String CREATE_GRADING_SQL =
            " CREATE TABLE OCENIANIE ( " +
                    " RODZAJ_OCENY CHAR(1) NOT NULL, " +
                    " IDN NUMBER NOT NULL, " +
                    " IDO NUMBER NOT NULL, " +
                    " IDU NUMBER NOT NULL, " +
                    " IDP NUMBER NOT NULL)";
}
