package com.ah.week1.assign1;

import java.util.Scanner;

public class GradeCalculation {
	
	public void calculateGrades() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("     Grade Calcuator    ");
		System.out.print("Enter Your Course Name: ");
		String coursename = input.nextLine();
		
		System.out.print("Enter Your Final Percentage: ");
		double percentage = input.nextDouble();
		
		String grade;
		
		if (percentage >= 80) {
			grade = "A";
		} else if (percentage >= 70) {
			grade = "B";
		} else if (percentage >= 60) {
			grade = "C";
		} else if(percentage >= 50) {
			grade = "D";
		} else {
			grade = "F";
		}
		
		System.out.println();
		System.out.println("Course: " + coursename);
		System.out.println("Percentage: " + percentage + "%");
		System.out.println("Grade: " + grade);
	}

	
}