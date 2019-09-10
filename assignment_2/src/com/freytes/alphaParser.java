package com.freytes;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class alphaParser {

    /* To-do:
     * - Add message for blank values in firstname and lastname *done
     * - Add Report Formatting *done
     * - Add single character limit for last name
     * - Add all arrays *done
     *   - median of all arrays *done
     *  - Seperate functions * done
     *
     */

    //Declare Variables from Main Class
    private Main mainVar = new Main();

    public void alphaParser() {

        //Declaring sendName method
        Main sendName = new Main();

        //Declaring sendReport method
        Main sendReport = new Main();

        //Scanner Object to read the text
        Scanner keyword = new Scanner(System.in);

        //Begin Name
        do {
            //User Input First Name
            System.out.println("Please Enter your First Name:");

            mainVar.fn1 = keyword.nextLine();

            if (!((mainVar.fn1 == null) || "".equals(mainVar.fn1.trim()) || mainVar.fn1.matches(("\\d+")))) {
                mainVar.fn1 = mainVar.fn1.replaceAll("[^a-zA-Z]", "");
                sendName.printName(mainVar.fn1);
                mainVar.errormsg = false;
            } else {
                System.out.println("Invalid Input, please enter alphabetic values only.\n");
                mainVar.errormsg = true;
            }

        } while (mainVar.errormsg);
        

        //Begin Last Name

        do {
            //User Input Last Name
            System.out.println("Please Enter your Last Name: (Once done please type exit)");


            mainVar.ln1 = keyword.nextLine();

            if (!((mainVar.ln1 == null) || "".equals(mainVar.ln1.trim()) || mainVar.ln1.matches(("\\d+")))) {
                mainVar.ln1 = mainVar.ln1.replaceAll("[^a-zA-Z]", "");
                sendName.printName(mainVar.ln1);
                mainVar.errormsg = false;
            } else {
                System.out.println("Invalid Input, please enter alphabetic values only.\n");
                mainVar.errormsg = true;
            }

        } while (mainVar.errormsg);
        //End Last Name

        //Begin Report Name
        do {

            System.out.println("Please Enter your Report Name:");

            mainVar.rname = keyword.nextLine();

            if (!((mainVar.rname == null) || "".equals(mainVar.rname.trim()))) {

                sendReport.printReport(mainVar.rname);
                mainVar.errormsg = false;
            } else {
                System.out.println("Invalid Input, please enter a value.\n");
                mainVar.errormsg = true;
            }
        } while (mainVar.errormsg);
        //End Report Name

        // Do/While Loop which checks for invalid characters and captures all integer values
        do {
            try {
                for (int i = 0; i < mainVar.finNumbervar; i++) {

                    //User Input number
                    System.out.print("Enter integer : \n");

                    // create scanner object for numerical values
                    Scanner numScanner = new Scanner(System.in);

                    //Add Values to Array
                    mainVar.n1[i] = numScanner.nextInt();

                    mainVar.errormsg = false;
                }
            } catch (InputMismatchException e) {

                // accept integer only.
                System.out.println("Invalid Input, please enter numerical values only.\n");

                mainVar.errormsg = true;
            }
        } while (mainVar.errormsg);

        // Calculate Array Values

        int n1_added = mainVar.n1[0] + mainVar.n1[1] + mainVar.n1[2] + mainVar.n1[3] + mainVar.n1[4] + mainVar.n1[5] + mainVar.n1[6];
        float n1_average = (float) (mainVar.n1[0] + mainVar.n1[1] + mainVar.n1[2] + mainVar.n1[3] + mainVar.n1[4] + mainVar.n1[5] + mainVar.n1[6]) / 2;
        int n1_max = IntStream.of(mainVar.n1).max().orElse(Integer.MIN_VALUE);
        int n1_min = IntStream.of(mainVar.n1).min().orElse(Integer.MAX_VALUE);

        System.out.println("Report Name:" + mainVar.rname);

        System.out.println("Full Name: " + mainVar.fn1 + " " + mainVar.ln1);

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

    }
}