package com.techelevator;
import java.util.Scanner;

/*
 Using the provided methods below, complete the code in the main method
 to prompt the user for the total bill, and the amount tendered.
 It should then display the change required.

 If the bill is $50 or more, an automatic gratuity of 10% of the bill is added.

 Please enter the amount of the bill: 23.65
 Please enter the amount tendered: 100.00
 The change required is 76.35

 OOOOOOOO~OR~RRRRRRRRR

 You make a cat menu!

 */
public class MakeChange {
	public static void main(String[] args) {

		MakeChangeLore.getStartMenu();

		MakeChangeLore.meetLyle();
		MakeChangeLore.meetPaintBrush();
		MakeChangeLore.meetSpaceMan();

		double whichBaby = MakeChangeLore.getBillInDollars();
			if (whichBaby == 1){
			System.out.println("You chose Lyle!");
			} else if (whichBaby == 2){
			System.out.println("You chose PaintBrush!");
			}else {
			System.out.println("You chose SpaceMan!");
			}
		double totalBillInDollars = MakeChangeLore.getBillWithServiceFee();
//			if (whichBaby  == 2){
//				totalBillInDollars = totalBillInDollars * 1.65;
//			}else if (whichBaby == 3){
//				totalBillInDollars = 42.0;
//			}

		double totalPayment = MakeChangeLore.getAmountTendered();

		double totalChange = totalPayment - totalBillInDollars;

		System.out.println("Congratulations on your new cat! They cost you: $" + totalBillInDollars);
		System.out.println("You gave us: $" + String.format("%.2f",totalPayment) + " in cash.");
		System.out.println("You are being returned: $" + String.format("%.2f",totalChange) + " and a CAT!");
		System.out.println();
		MakeChangeLore.endMenu();
	}

}
