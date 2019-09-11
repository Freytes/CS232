package com.freytes;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

class alphaParser {

    //Declare Variables
    String fn1;
    String ln1;
    char[] ln1_temporary = new char[50];
    int ln_index = 0;
    String rname;
    int[] n1 = new int[7];
    int finNumbervar = 7;
    boolean errormsg = false;

    /* To-do:
     * - Add message for blank values in firstname and lastname *done
     * - Add Report Formatting *done
     * - Add single character limit for last name
     * - Add all arrays *done
     *   - median of all arrays *done
     *  - Seperate functions * done
     *
     */

    //Declaring sendName method
    private Main sendName = new Main();

    //Declaring sendReport method
    private Main sendReport = new Main();

    //Scanner Object to read the text
    private Scanner keyword = new Scanner(System.in);

    //Begin Name
    String firstName() {
        do {
            //User Input First Name
            System.out.println("Please Enter your First Name:");
            fn1 = keyword.nextLine();

            if (!((fn1 == null) || "".equals(fn1.trim()) || fn1.matches(("\\d+")))) {
                fn1 = fn1.replaceAll("[^a-zA-Z]", "");
                sendName.printName(fn1);
                errormsg = false;
            } else {
                System.out.println("Invalid Input, please enter alphabetic values only.\n");
                errormsg = true;
            }

        } while (errormsg);
        return fn1;
    }


 /*   void lastName() {
        //Begin Last Name
        do {
            //User Input Last Name
            System.out.println("Please Enter your Last Name: (Once done please type exit)");
            ln1 = keyword.nextLine();

            if (!((ln1 == null) || "".equals(ln1.trim()) || ln1.matches(("\\d+")))) {
                ln1 = ln1.replaceAll("[^a-zA-Z]", "");
                sendName.printName(ln1);
                errormsg = false;
            } else {
                System.out.println("Invalid Input, please enter alphabetic values only.\n");
                errormsg = true;
            }

        } while (errormsg);
        //End Last Name
    }*/

    String lastName() {
        //Begin Last Name
        while (true) {
            //User Input Last Name
            System.out.println("Please Enter your Last Name: (Once done please type exit)");
            Character ln_input = keyword.next().charAt(0);
            ln1 = ln_input.toString();

            if (ln1.contains("#") || ln_input == 50) {
                ln1 = new String(ln1_temporary);
                ln1 = ln1.trim();
                System.out.println("Last Name:" + ln1);

            }

            if (!((ln1 == null) || "".equals(ln1.trim()) || ln1.matches(("\\d+")))) {
                ln1 = ln1.replaceAll("[^a-zA-Z]", "");
                ln1_temporary[ln_index++] = ln_input;

            } else {
                System.out.println("Invalid Input, please enter alphabetic values only.\n");

            }
        }
    }
    //End Last Name


    String reportName() {
        //Begin Report Name
        do {

            System.out.println("Please Enter your Report Name:");

            rname = keyword.nextLine();

            if (!((rname == null) || "".equals(rname.trim()))) {

                sendReport.printReport(rname);
                errormsg = false;
            } else {
                System.out.println("Invalid Input, please enter a value.\n");
                errormsg = true;
            }
        } while (errormsg);
        return rname;
    }
    //End Report Name

    String collectNumbers() {
        // Do/While Loop which checks for invalid characters and captures all integer values
        do {
            try {
                for (int i = 0; i < finNumbervar; i++) {

                    //User Input number
                    System.out.print("Enter integer : \n");
                    System.out.print("Enter only numerical values \n");
                    // create scanner object for numerical values
                    Scanner numScanner = new Scanner(System.in);

                    //Add Values to Array
                    n1[i] = numScanner.nextInt();

                    errormsg = false;
                }
            } catch (InputMismatchException e) {

                // accept integer only.
                System.out.println("Invalid Input, please enter numerical values only.\n");

                errormsg = true;
            }
        } while (errormsg);
        return String.valueOf(n1);
    }

    void reportPrint() {
        // Calculate Array Values

        int n1_added = n1[0] + n1[1] + n1[2] + n1[3] + n1[4] + n1[5] + n1[6];
        float n1_average = (float) (n1[0] + n1[1] + n1[2] + n1[3] + n1[4] + n1[5] + n1[6]) / 2;
        int n1_max = IntStream.of(n1).max().orElse(Integer.MIN_VALUE);
        int n1_min = IntStream.of(n1).min().orElse(Integer.MAX_VALUE);

        System.out.println("Report Name:" + rname);

        System.out.println("Full Name: " + fn1 + " " + ln1);

        System.out.println("------------------------------------------------");

        System.out.println("|\tHighest Value: \t|\t" + n1_max + "\t");

        System.out.println("------------------------------------------------");

        System.out.println("|\tLowest Value: \t|\t" + n1_min + "\t");

        System.out.println("------------------------------------------------");

        System.out.println("|\tAverage: \t\t|\t" + n1_average + "\t");

        System.out.println("------------------------------------------------");

        System.out.println("|\tGrand Total: \t|\t" + n1_added + "\t");

        System.out.println("------------------------------------------------");

        //End Report Name
        return;
    }
}