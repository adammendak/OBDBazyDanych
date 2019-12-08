package com.adammendak.obd;

import java.sql.*;

import static com.adammendak.obd.Constants.*;

class JDBCService {

    static void InsertGrading(String typeOfGrade, String idn, String ido, String idu, String idp) {
        System.out.println("####INSERTING GRADING INTO DATABASE WITH PARAMETERS " +
                "typeOfGrade = " + typeOfGrade + ", idn = " + idn + ", ido = " + ido + ", idu = " + idu + ", idp = " + idp);

        try (
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                Statement statement = connection.createStatement()
        ) {

            Boolean result = checkConstraints(typeOfGrade, idn, ido, idu, idp);
            if (!result) {
                return;
            }

            String sql = String.format(INSERT_TEMPLATE, "OCENIANIE",
                    "'" + typeOfGrade + "', '" + idn + "', '" + ido + "', '" + idu + "', '" + idp + "'");
            statement.executeUpdate(sql);

        } catch (
                SQLException ex) {
            System.out.println("####Failed to execute query");
            ex.printStackTrace();
        }

        System.out.println("####INSERT SUCCESSFULL\n");
    }

    private static Boolean checkConstraints(String typeOfGrade, String idn, String ido, String idu, String idp) {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs1 = statement.executeQuery(String.format(SELECT_TEMPLATE, "NAUCZYCIEL", "IDN", idn));
            rs1.next();
            if (rs1.getString("COUNT(*)").equals("0")) {
                System.out.println("THERE IS NO THEACHER WITH IDN = " + idn +
                        ", FOREIGN KEY VIOLATION, COULD NOT EXECUTE QUERY");
                return false;
            }

            ResultSet rs2 = statement.executeQuery(String.format(SELECT_TEMPLATE, "OCENA", "IDO", ido));
            rs2.next();
            if (rs2.getString("COUNT(*)").equals("0")) {
                System.out.println("THERE IS NO GRADE WITH IDO = " + ido +
                        ", FOREIGN KEY VIOLATION, COULD NOT EXECUTE QUERY");
                return false;
            }

            ResultSet rs3 = statement.executeQuery(String.format(SELECT_TEMPLATE, "PRZEDMIOT", "IDP", idp));
            rs3.next();
            if (rs3.getString("COUNT(*)").equals("0")) {
                System.out.println("THERE IS NO SUBJECT WITH IDP = " + idp +
                        ", FOREIGN KEY VIOLATION, COULD NOT EXECUTE QUERY");
                return false;
            }

            ResultSet rs4 = statement.executeQuery(String.format(SELECT_TEMPLATE, "UCZEN", "IDU", idu));
            rs4.next();
            if (rs4.getString("COUNT(*)").equals("0")) {
                System.out.println("THERE IS NO STUDENT WITH IDU = " + idu +
                        ", FOREIGN KEY VIOLATION, COULD NOT EXECUTE QUERY");
                return false;
            }

        } catch (
                SQLException ex) {
            System.out.println("####Failed to execute query");
            ex.printStackTrace();
        }
        return true;
    }
}
