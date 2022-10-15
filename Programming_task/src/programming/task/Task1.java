package programming.task;

import java.util.Scanner;

public class Task1 {
	
	//Global variables declaration
	public static int[] myArray = new int[10];
	public static int index = 0;
	public static Scanner sc;

	public static void main(String[] args) {	
		sc = new Scanner(System.in);
		
		//Get user's input
		getIntegers();
		
		//Print the user's data
		printTheData();
		//Close the scanner
		sc.close();
	}
	public static void getIntegers() {
		while(index<10) {
			System.out.println("Please enter your number: " +(index+1));
			//Check if the input is integer
			if(sc.hasNextInt()) {
				myArray[index]= sc.nextInt();
				index++;
			}else {
				//if the input is incorrect, then repeat
				sc.nextLine();
			}
		}
	}	
	public static void printTheData() {
		final String ZERO="is zero", EVEN="is even", ODD="is odd";
		String answer ="";
		
		for(int i=0; i<myArray.length;i++) {
			//Check if the number is ODD, EVEN or ZERO
			if(myArray[i]==0) {
				answer="Integer with index "+(i+1)+": " + ZERO;
			}else if(myArray[i]%2==0) {
				answer="Integer with index "+(i+1)+": " + EVEN;
			}else {
				answer="Integer with index "+(i+1)+": " + ODD;
			}
			//Print the answer
			System.out.println(answer);
		}
	}

}
