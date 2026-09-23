package com.ah.week1.assign1;

public class DriverMain {

    public static void main(String[] args) {

        // Create objects for each task
        GradeCalculation gradeCalculator = new GradeCalculation();
        MilesToKilometers milesConverter = new MilesToKilometers();
        LitersToGallons litersConverter = new LitersToGallons();
        CADToUSD currencyConverter = new CADToUSD();

        // Task A - Calculate Grade
        gradeCalculator.calculateGrades();

        System.out.println();

        // Task B - Miles to Kilometers
        milesConverter.convert();

        System.out.println();

        // Task C - Liters to Gallons
        litersConverter.convert();

        System.out.println();

        // Task D - CAD to USD
        currencyConverter.convert();
    }
}