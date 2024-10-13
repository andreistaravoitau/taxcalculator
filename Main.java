package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        double income;
        char contractType;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter income: ");
            income = Double.parseDouble(reader.readLine());

            System.out.print("Contract Type: (E)mployment, (C)ivil: ");
            contractType = reader.readLine().toUpperCase().charAt(0);

        } catch (Exception ex) {
            System.out.println("Invalid input.");
            ex.printStackTrace();
            return;
        }

        TaxCalculator taxCalculator = new TaxCalculator();
        if (contractType == 'E') {
            System.out.println("Employment Contract");
            taxCalculator.calculateForEmploymentContract(income);
        } else if (contractType == 'C') {
            System.out.println("Civil Contract");
            taxCalculator.calculateForCivilContract(income);
        } else {
            System.out.println("Unknown contract type!");
        }
    }
}
