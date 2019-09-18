package com.freytes;

public class Main {

    public static void main(String[] args) {
        //Calling alphaParser Class
        userInput callClass = new userInput();
        callClass.instructions();
        callClass.firstName();
        callClass.lastName();
        callClass.reportName();
        callClass.collectNumbers();
        callClass.reportPrint();
        callClass.exitProgram();
    }

    // Methods for receiving input values
    void printName(String receivedName) {
        System.out.println(receivedName);
    }

    void printReport(String receivedReport) {
        System.out.println(receivedReport);
    }

}