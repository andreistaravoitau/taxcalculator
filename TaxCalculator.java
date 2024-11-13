
package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TaxCalculator {

	private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);

	// Calculates taxes based on income and contract type
	public TaxResult calculateTaxes(BigDecimal income, String contractTypeCode) {
		TaxResult result = new TaxResult();

		// Calculate social contributions
		BigDecimal socialSecurityTax = calculateSocialSecurityTax(income);
		BigDecimal healthSecurityTax = calculateHealthSecurityTax(income);
		BigDecimal sicknessSecurityTax = calculateSicknessSecurityTax(income);
		BigDecimal totalSocialContributions = socialSecurityTax
				.add(healthSecurityTax)
				.add(sicknessSecurityTax);

		result.setSocialSecurityTax(socialSecurityTax);
		result.setHealthSecurityTax(healthSecurityTax);
		result.setSicknessSecurityTax(sicknessSecurityTax);

		// Calculate income after contributions
		BigDecimal incomeAfterContributions = income.subtract(totalSocialContributions);

		// Calculate health taxes
		BigDecimal healthTaxNinePercent = calculateHealthTax(incomeAfterContributions, TaxConstants.HEALTH_TAX_NINE_PERCENT);
		BigDecimal healthTaxSevenSeventyFivePercent = calculateHealthTax(incomeAfterContributions, TaxConstants.HEALTH_TAX_SEVEN_SEVENTY_FIVE_PERCENT);

		result.setHealthTaxNinePercent(healthTaxNinePercent);
		result.setHealthTaxSevenSeventyFivePercent(healthTaxSevenSeventyFivePercent);

		// Calculate tax-deductible expenses
		BigDecimal taxDeductibleExpenses = calculateTaxDeductibleExpenses(income);
		result.setTaxDeductibleExpenses(taxDeductibleExpenses);

		// Calculate taxable income and round it
		BigDecimal taxableIncome = income.subtract(taxDeductibleExpenses);
		BigDecimal roundedTaxableIncome = taxableIncome.setScale(0, RoundingMode.HALF_UP);

		// Calculate advance tax
		BigDecimal advanceTax = calculateAdvanceTax(roundedTaxableIncome);
		result.setAdvanceTax(advanceTax);

		// Calculate final tax to pay based on contract type
		BigDecimal taxToPay = calculateTaxToPay(advanceTax, contractTypeCode);

		// Calculate net income
		BigDecimal netIncome = income.subtract(totalSocialContributions)
				.subtract(healthTaxNinePercent)
				.subtract(taxToPay);

		result.setNetIncome(netIncome);

		return result;
	}

	private BigDecimal calculateSocialSecurityTax(BigDecimal income) {
		return income.multiply(TaxConstants.SOCIAL_SECURITY_PERCENTAGE)
				.divide(new BigDecimal("100"), MC);
	}

	private BigDecimal calculateHealthSecurityTax(BigDecimal income) {
		return income.multiply(TaxConstants.HEALTH_SECURITY_PERCENTAGE)
				.divide(new BigDecimal("100"), MC);
	}

	private BigDecimal calculateSicknessSecurityTax(BigDecimal income) {
		return income.multiply(TaxConstants.SICKNESS_SECURITY_PERCENTAGE)
				.divide(new BigDecimal("100"), MC);
	}

	// Calculate health tax based on a given rate
	private BigDecimal calculateHealthTax(BigDecimal incomeAfterContributions, BigDecimal rate) {
		return incomeAfterContributions.multiply(rate)
				.divide(new BigDecimal("100"), MC);
	}

	private BigDecimal calculateTaxDeductibleExpenses(BigDecimal income) {
		return income.multiply(TaxConstants.TAX_DEDUCTIBLE_EXPENSES_PERCENTAGE)
				.divide(new BigDecimal("100"), MC);
	}

	private BigDecimal calculateAdvanceTax(BigDecimal taxableIncome) {
		return taxableIncome.multiply(TaxConstants.TAX_PERCENTAGE)
				.divide(new BigDecimal("100"), MC);
	}

	// Determines final tax amount, considering contract type
	private BigDecimal calculateTaxToPay(BigDecimal advanceTax, String contractType) {
		if (contractType.equals("E")) {
			BigDecimal taxToPay = advanceTax.subtract(TaxConstants.TAX_FREE_AMOUNT);
			return taxToPay.max(BigDecimal.ZERO);
		} else {
			return advanceTax;
		}
	}
}
