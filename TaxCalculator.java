package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {

	private final SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator();
	private final HealthSecurityTaxCalculator healthSecurityTaxCalculator = new HealthSecurityTaxCalculator();
	private final SicknessSecurityTaxCalculator sicknessSecurityTaxCalculator = new SicknessSecurityTaxCalculator();
	private final TaxDeductibleExpensesCalculator taxDeductibleExpensesCalculator = new TaxDeductibleExpensesCalculator();
	private final AdvanceTaxCalculator advanceTaxCalculator = new AdvanceTaxCalculator();

	private final HealthTaxCalculator healthTaxNinePercentCalculator = new HealthTaxCalculator(TaxConstants.HEALTH_TAX_NINE_PERCENT);
	private final HealthTaxCalculator healthTaxSevenSeventyFivePercentCalculator = new HealthTaxCalculator(TaxConstants.HEALTH_TAX_SEVEN_SEVENTY_FIVE_PERCENT);

	public TaxResult calculateTaxes(BigDecimal income) {
		TaxResult result = new TaxResult();

		BigDecimal socialSecurityTax = socialSecurityTaxCalculator.calculate(income);
		BigDecimal healthSecurityTax = healthSecurityTaxCalculator.calculate(income);
		BigDecimal sicknessSecurityTax = sicknessSecurityTaxCalculator.calculate(income);
		BigDecimal totalSocialContributions = socialSecurityTax.add(healthSecurityTax).add(sicknessSecurityTax);

		result.setSocialSecurityTax(socialSecurityTax);
		result.setHealthSecurityTax(healthSecurityTax);
		result.setSicknessSecurityTax(sicknessSecurityTax);

		BigDecimal incomeAfterContributions = income.subtract(totalSocialContributions);

		BigDecimal healthTaxNinePercent = healthTaxNinePercentCalculator.calculate(incomeAfterContributions);
		BigDecimal healthTaxSevenSeventyFivePercent = healthTaxSevenSeventyFivePercentCalculator.calculate(incomeAfterContributions);

		result.setHealthTaxNinePercent(healthTaxNinePercent);
		result.setHealthTaxSevenSeventyFivePercent(healthTaxSevenSeventyFivePercent);

		BigDecimal taxDeductibleExpenses = taxDeductibleExpensesCalculator.calculate(income);
		result.setTaxDeductibleExpenses(taxDeductibleExpenses);

		BigDecimal taxableIncome = income.subtract(taxDeductibleExpenses).setScale(0, RoundingMode.HALF_UP);
		BigDecimal advanceTax = advanceTaxCalculator.calculate(taxableIncome);
		result.setAdvanceTax(advanceTax);

		result.setNetIncome(income.subtract(totalSocialContributions)
				.subtract(healthTaxNinePercent)
				.subtract(advanceTax));

		return result;
	}
}
