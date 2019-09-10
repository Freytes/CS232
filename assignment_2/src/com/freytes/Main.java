package com.freytes;
public class Main {

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