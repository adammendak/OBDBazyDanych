package com.adammendak.obd;

import java.sql.*;

import static com.adammendak.obd.Constants.*;

class JDBCService {

    private static final String CONSTRAINT_VIOLATION = "ORA-02291: integrity constraint violated - %s parent key not found";
    private static Boolean result = false;

    static void InsertGrading(String typeOfGrade, String idn, String ido, String idu, String idp) {
        System.out.println("####INSERTING GRADING INTO DATABASE WITH PARAMETERS " +
                "typeOfGrade = " + typeOfGrade + ", idn = " + idn + ", ido = " + ido + ", idu = " + idu + ", idp = " + idp);

        try (
                Statement statement = Main.connection.createStatement()
        ) {
            if (!checkConstraints(idn, ido, idu, idp)) {
                return;
            }

            String sql = String.format(INSERT_TEMPLATE, "OCENIANIE",
                    "'" + typeOfGrade + "', '" + idn + "', '" + ido + "', '" + idu + "', '" + idp + "'");
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("####Failed to execute query");
            ex.printStackTrace();
            return;
        } catch (ForeignKeySimulationException ex) {
            System.out.println("####Failed to execute query");
            System.out.println(ex.getMessage());
            System.out.println();
            return;
        }

        System.out.println("####INSERT SUCCESSFULL\n");
    }

    private static Boolean checkConstraints(String idn, String ido, String idu, String idp)
            throws ForeignKeySimulationException, SQLException {

        Statement statement = Main.connection.createStatement();

        ResultSet rs1 = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDN", "NAUCZYCIEL", "IDN", idn));
        result = rs1.next();
        if (!result) {
            throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "(NAUCZYCIEL_FK)"));
        }
        rs1.close();

        ResultSet rs2 = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDO", "OCENA", "IDO", ido));
        result = rs2.next();
        if (!result) {
            throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "OCENA_FK"));
        }
        rs2.close();

        ResultSet rs3 = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDP", "PRZEDMIOT", "IDP", idp));
        result = rs3.next();
        if (!result) {
            throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "(PRZEDMIOT_FK)"));
        }
        rs3.close();

        ResultSet rs4 = statement.executeQuery(String.format(SELECT_TEMPLATE, "IDU", "UCZEN", "IDU", idu));
        result = rs4.next();
        if (!result) {
            throw new ForeignKeySimulationException(String.format(CONSTRAINT_VIOLATION, "(UCZEN_FK)"));
        }
        rs4.close();

        statement.close();
        return result;
    }
}
