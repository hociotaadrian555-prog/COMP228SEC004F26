package com.ah.week1.assign1;

import java.util.Scanner;

public class MilesToKilometers {

	public void convert() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("    Miles To Kilometers    ");
		
		System.out.print("Enter Miles: ");
		double miles = input.nextDouble();
		
		double kilometers = miles * 1.60934;
		
		System.out.printf("%.2f miles = %.2f kilometers%n",	miles, kilometers);
	}

}
