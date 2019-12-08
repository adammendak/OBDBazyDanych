package com.adammendak.obd;

public class Main {

    public static void main(String[] args) {
        DDLBootstrapService.checkDriver();
        DDLBootstrapService.createDBTables();
        DMLBootstrapService.insertDummyData();
    }

}
