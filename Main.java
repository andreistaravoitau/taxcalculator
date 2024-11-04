package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        double income;
        ContractType contractType;

        char input;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter income: ");
            income = Double.parseDouble(reader.readLine());

            System.out.print("Contract Type: (E)mployment, (C)ivil: ");
            input = reader.readLine().toUpperCase().charAt(0);

        } catch (Exception ex) {
            System.out.println("Invalid input.");
            ex.printStackTrace();
            return;
        }

        TaxCalculator taxCalculator = new TaxCalculator();
        switch (input) {
            case 'E':
                contractType = ContractType.EMPLOYMENT;
                taxCalculator.calculateForEmploymentContract(income);
                break;
            case 'C':
                contractType = ContractType.CIVIL;
                taxCalculator.calculateForCivilContract(income);
                break;
            default:
                System.out.println("Unknown contract type!");
                return;
        }
    }
}
