package com.adammendak.obd;

import java.util.Scanner;

public class Main {

    private static Boolean PROGRAM_RUNNING = true;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DDLBootstrapService.checkDriver();
        DDLBootstrapService.createDBTables();
        DMLBootstrapService.insertDummyData();

        System.out.println("## STARTING PROGRAM ##");

        while (PROGRAM_RUNNING) {
            System.out.println("Please input data[type of grade ('C' or 'S'), idn, ido, idu, idp]." +
                    " To end program please type 'END'.");
            System.out.println("Please input Type Of Grade[C=CZESCIOWA, S=SEMESTRALNA] =");
            String gradeType = checkForIndexInput(scanner.next());
            if(!PROGRAM_RUNNING) break;

            System.out.println("Please input IDN =");
            String idn = checkForIndexInput(scanner.next());
            if(!PROGRAM_RUNNING) break;

            System.out.println("Please input IDO =");
            String ido = checkForIndexInput(scanner.next());
            if(!PROGRAM_RUNNING) break;

            System.out.println("Please input IDU =");
            String idu = checkForIndexInput(scanner.next());
            if(!PROGRAM_RUNNING) break;

            System.out.println("Please input IDP =");
            String idp = checkForIndexInput(scanner.next());
            if(!PROGRAM_RUNNING) break;

            JDBCService.InsertGrading(gradeType, idn, ido, idu, idp);
        }

        System.out.println("## PROGRAM ENDING ##");
    }

    private static String checkForIndexInput(String next) {
        if (next.equals("END")) {
            PROGRAM_RUNNING = false;
        }
        return next;
    }

}
