package com.adammendak.obd;

import java.sql.*;

import static com.adammendak.obd.Constants.*;

class JDBCService {

    private static ResultSet rs;

    static void InsertGrading(String typeOfGrade, String idn, String ido, String idu, String idp) throws SQLException {
        System.out.println("####INSERTING GRADING INTO DATABASE WITH PARAMETERS " +
                "typeOfGrade = " + typeOfGrade + ", idn = " + idn + ", ido = " + ido + ", idu = " + idu + ", idp = " + idp);

        try (
                Statement statement = Main.connection.createStatement()
        ) {
            checkConstraints(idn, ido, idu, idp);
            String sql = String.format(INSERT_TEMPLATE, "OCENIANIE",
                    "'" + typeOfGrade + "', '" + idn + "', '" + ido + "', '" + idu + "', '" + idp + "'");
            statement.executeUpdate(sql);
            rs.close();
        } catch (SQLException ex) {
            System.out.println("####Failed to execute query");
            ex.printStackTrace();
            rs.close();
            return;
        } catch (ForeignKeySimulationException ex) {
            System.out.println("####Failed to execute query");
            System.out.println(ex.getMessage());
            System.out.println();
            rs.close();
            return;
        }

        System.out.println("####INSERT SUCCESSFULL\n");
    }

    private static void checkConstraints(String idn, String ido, String idu, String idp)
            throws ForeignKeySimulationException, SQLException {

        try (Statement statement = Main.connection.createStatement()) {
            rs = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDN", "NAUCZYCIEL", "IDN", idn));
            if (!rs.next()) {
                throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "(NAUCZYCIEL_FK)"));
            }
            rs.close();

            rs = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDO", "OCENA", "IDO", ido));
            if (!rs.next()) {
                throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "OCENA_FK"));
            }
            rs.close();

            rs = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDP", "PRZEDMIOT", "IDP", idp));
            if (!rs.next()) {
                throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "(PRZEDMIOT_FK)"));
            }
            rs.close();

            rs = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDU", "UCZEN", "IDU", idu));
            if (!rs.next()) {
                throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "(UCZEN_FK)"));
            }
            rs.close();
        }
    }
}
