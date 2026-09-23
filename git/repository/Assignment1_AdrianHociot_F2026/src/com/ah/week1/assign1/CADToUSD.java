package com.ah.week1.assign1;

import java.util.Scanner;

public class CADToUSD {

    public void convert() {

        Scanner input = new Scanner(System.in);

        System.out.println("     CAD to USD Converter     ");

        // Exchange rate obtained from Google
        double exchangeRate = 0.73;

        System.out.print("Enter amount in CAD: ");
        double cad = input.nextDouble();

        double usd = cad * exchangeRate;

        System.out.printf("%.2f CAD = %.2f USD%n", cad, usd);

        System.out.println("Exchange Rate: 1 CAD = " + exchangeRate + " USD");
    }
}