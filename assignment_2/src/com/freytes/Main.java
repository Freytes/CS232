package com.freytes;
public class Main {

	//Declare Variables
	String fn1;
	String ln1;
	char [] lname = new char[50];
	String rname;
	int[] n1 = new int[7];
	int finNumbervar = 7;
	boolean errormsg = false;
	int countVal = 0;

	public static void main(String[] args) {
		//Calling alphaParser Class
		alphaParser callClass = new alphaParser();
		callClass.firstName();
		callClass.lastName();
		callClass.reportName();
		callClass.collectNumbers();
		callClass.reportPrint();
	}

	void printName(String receivedName){
		System.out.println(receivedName);
	}
	void printReport(String receivedReport){
		System.out.println(receivedReport);
	}

}