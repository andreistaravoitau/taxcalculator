package com.bartoszwalter.students.taxes;

import java.text.DecimalFormat;

public class TaxCalculator {
	private double socialSecurityTax;
	private double healthSecurityTax;
	private double sicknessSecurityTax;
	private double healthTax9Percent;
	private double healthTax7_75Percent;
	private double taxDeductibleExpenses;
	private double advanceTax;
	private double netIncome;

	private final DecimalFormat df00 = new DecimalFormat("#.00");
	private final DecimalFormat df = new DecimalFormat("#");

	public void calculateForEmploymentContract(double income) {
		System.out.println("Income: " + df00.format(income));
		calculateCommonDeductions(income);
		calculateEmploymentSpecificTaxes(income);
	}

	public void calculateForCivilContract(double income) {
		System.out.println("Income: " + df00.format(income));
		calculateCommonDeductions(income);
		calculateCivilSpecificTaxes(income);
	}

	private void calculateCommonDeductions(double income) {
		double taxableIncome = calculateSocialSecurityTaxes(income);
		System.out.println("Social Security: " + df00.format(socialSecurityTax));
		System.out.println("Health Insurance: " + df00.format(healthSecurityTax));
		System.out.println("Sickness Insurance: " + df00.format(sicknessSecurityTax));

		calculateHealthTaxes(taxableIncome);
		System.out.println("Health Tax (9%): " + df00.format(healthTax9Percent) + ", Health Tax (7.75%): " + df00.format(healthTax7_75Percent));
	}

	private void calculateEmploymentSpecificTaxes(double income) {
		taxDeductibleExpenses = calculateTaxDeductibleExpenses(income, TaxConstants.TAX_DEDUCTIBLE_EXPENSES_PERCENTAGE);
		printTaxDeductibleExpenses();

		double taxableIncome = getTaxableIncome(income);
		calculateAdvanceTax(taxableIncome, true);
	}

	private void calculateCivilSpecificTaxes(double income) {
		taxDeductibleExpenses = calculateTaxDeductibleExpenses(income, TaxConstants.TAX_DEDUCTIBLE_EXPENSES_PERCENTAGE);
		printTaxDeductibleExpenses();

		double taxableIncome = getTaxableIncome(income);
		calculateAdvanceTax(taxableIncome, false);
	}

	private void printTaxDeductibleExpenses() {
		System.out.println("Tax Deductible Expenses: " + df00.format(taxDeductibleExpenses));
	}

	private double calculateSocialSecurityTaxes(double income) {
		socialSecurityTax = (income * TaxConstants.SOCIAL_SECURITY_PERCENTAGE) / 100;
		healthSecurityTax = (income * TaxConstants.HEALTH_SECURITY_PERCENTAGE) / 100;
		sicknessSecurityTax = (income * TaxConstants.SICKNESS_SECURITY_PERCENTAGE) / 100;
		return income - socialSecurityTax - healthSecurityTax - sicknessSecurityTax;
	}

	private void calculateHealthTaxes(double taxableIncome) {
		healthTax9Percent = (taxableIncome * TaxConstants.HEALTH_TAX_9_PERCENT) / 100;
		healthTax7_75Percent = (taxableIncome * TaxConstants.HEALTH_TAX_7_75_PERCENT) / 100;
	}

	private double calculateTaxDeductibleExpenses(double income, double percentage) {
		return (income * percentage) / 100;
	}

	private double getTaxableIncome(double income) {
		return income - taxDeductibleExpenses;
	}

	private void calculateAdvanceTax(double taxableIncome, boolean isEmployment) {
		double roundedIncome = Double.parseDouble(df.format(taxableIncome));
		System.out.println("Taxable Income: " + df00.format(taxableIncome) + ", Rounded: " + df.format(roundedIncome));

		calculateAdvanceTax(roundedIncome);
		System.out.println("Advance Tax (18%): " + df00.format(advanceTax));

		if (isEmployment) {
			calculateReducedTax();
		} else {
			calculateCivilTax();
		}
	}

	private void calculateAdvanceTax(double taxableIncome) {
		advanceTax = (taxableIncome * TaxConstants.TAX_PERCENTAGE) / 100;
	}

	private void calculateReducedTax() {
		System.out.println("Tax-Free Income: " + df00.format(TaxConstants.TAX_FREE_AMOUNT));
		double taxPaid = advanceTax - TaxConstants.TAX_FREE_AMOUNT;
		System.out.println("Reduced Tax: " + df00.format(taxPaid));
		calculateNetIncome(taxPaid);
	}

	private void calculateCivilTax() {
		calculateNetIncome(advanceTax);
	}

	private void calculateNetIncome(double taxPaid) {
		netIncome = socialSecurityTax + healthSecurityTax + sicknessSecurityTax + healthTax9Percent + taxPaid;
		System.out.println("Net Income: " + df00.format(netIncome));
	}
}
