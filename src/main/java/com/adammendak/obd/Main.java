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
            System.out.println("Please input data[idn, idu, idp, ido, type of grade ('C' or 'S')]." +
                    " To end program please type 'END'.");
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
