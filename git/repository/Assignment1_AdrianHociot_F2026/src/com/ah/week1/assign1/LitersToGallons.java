package com.ah.week1.assign1;

import java.util.Scanner;

public class LitersToGallons {
	
	public void convert() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("     Liters To Gallons     ");
		
		System.out.print("Enter Liters: ");
		double liters = input.nextDouble();
		
		double gallons = liters * 0.264172;
		
		System.out.printf("%.2f liters = %.2f gallons", liters, gallons);
	
	}
}
