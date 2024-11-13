package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import static com.bartoszwalter.students.taxes.TaxConstants.CONTRACT_EMPLOYMENT_KEY;

public class Main {
    public static void main(String[] args) {
        BigDecimal income;
        String contractTypeCode;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter income: ");
            if (!scanner.hasNextBigDecimal()) {
                System.out.println("Please enter a valid number for income.");
                return;
            }
            income = scanner.nextBigDecimal();

            if (income.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Income must be a positive number.");
                return;
            }

            System.out.print("Contract Type: (E)mployment, (C)ivil: ");
            contractTypeCode = scanner.next().trim().toUpperCase();

            if (!ContractType.CONTRACT_TYPES.containsKey(contractTypeCode)) {
                System.out.println("Unknown contract type!");
                return;
            }

        } catch (Exception ex) {
            System.out.println("Invalid input.");
            return;
        }

        TaxCalculator taxCalculator = new TaxCalculator();
        TaxResult result = taxCalculator.calculateTaxes(income, contractTypeCode);

        displayResults(income, contractTypeCode, result);
    }

    private static void displayResults(BigDecimal income, String contractTypeCode, TaxResult result) {
        System.out.println("\n=== Tax Calculation Result ===");
        System.out.println("Income: " + formatCurrency(income));
        System.out.println("Contract Type: " + ContractType.getContractTypeDescription(contractTypeCode));

        System.out.println("\n--- Social Contributions ---");
        System.out.println("Social Security Tax (9.76%): " + formatCurrency(result.getSocialSecurityTax()));
        System.out.println("Health Security Tax (1.5%): " + formatCurrency(result.getHealthSecurityTax()));
        System.out.println("Sickness Security Tax (2.45%): " + formatCurrency(result.getSicknessSecurityTax()));

        System.out.println("\n--- Health Taxes ---");
        System.out.println("Health Tax (9%): " + formatCurrency(result.getHealthTaxNinePercent()));
        System.out.println("Health Tax (7.75%): " + formatCurrency(result.getHealthTaxSevenSeventyFivePercent()));

        System.out.println("\n--- Tax Calculations ---");
        System.out.println("Tax Deductible Expenses (20%): " + formatCurrency(result.getTaxDeductibleExpenses()));
        System.out.println("Taxable Income: " + formatCurrency(income.subtract(result.getTaxDeductibleExpenses())));
        System.out.println("Advance Tax (18%): " + formatCurrency(result.getAdvanceTax()));

        if (contractTypeCode.equals(CONTRACT_EMPLOYMENT_KEY)) {
            System.out.println("Tax-Free Amount: " + formatCurrency(TaxConstants.TAX_FREE_AMOUNT));
            BigDecimal taxToPay = result.getAdvanceTax().subtract(TaxConstants.TAX_FREE_AMOUNT);
            taxToPay = taxToPay.max(BigDecimal.ZERO);
            System.out.println("Tax to Pay after Tax-Free Amount: " + formatCurrency(taxToPay));
        } else {
            System.out.println("Tax to Pay: " + formatCurrency(result.getAdvanceTax()));
        }

        System.out.println("\n--- Net Income ---");
        System.out.println("Net Income: " + formatCurrency(result.getNetIncome()));
    }

    private static String formatCurrency(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP).toString();
    }
}
