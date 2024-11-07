package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;

// Class to store and retrieve tax calculation results
public class TaxResult {
    private BigDecimal socialSecurityTax;
    private BigDecimal healthSecurityTax;
    private BigDecimal sicknessSecurityTax;
    private BigDecimal healthTaxNinePercent;
    private BigDecimal healthTaxSevenSeventyFivePercent;
    private BigDecimal taxDeductibleExpenses;
    private BigDecimal advanceTax;
    private BigDecimal netIncome;

    // Getter and setter for social security tax
    public BigDecimal getSocialSecurityTax() {
        return socialSecurityTax;
    }

    public void setSocialSecurityTax(BigDecimal socialSecurityTax) {
        this.socialSecurityTax = socialSecurityTax;
    }

    // Getter and setter for health security tax
    public BigDecimal getHealthSecurityTax() {
        return healthSecurityTax;
    }

    public void setHealthSecurityTax(BigDecimal healthSecurityTax) {
        this.healthSecurityTax = healthSecurityTax;
    }

    // Getter and setter for sickness security tax
    public BigDecimal getSicknessSecurityTax() {
        return sicknessSecurityTax;
    }

    public void setSicknessSecurityTax(BigDecimal sicknessSecurityTax) {
        this.sicknessSecurityTax = sicknessSecurityTax;
    }

    // Getter and setter for 9% health tax
    public BigDecimal getHealthTaxNinePercent() {
        return healthTaxNinePercent;
    }

    public void setHealthTaxNinePercent(BigDecimal healthTaxNinePercent) {
        this.healthTaxNinePercent = healthTaxNinePercent;
    }

    // Getter and setter for 7.75% health tax
    public BigDecimal getHealthTaxSevenSeventyFivePercent() {
        return healthTaxSevenSeventyFivePercent;
    }

    public void setHealthTaxSevenSeventyFivePercent(BigDecimal healthTaxSevenSeventyFivePercent) {
        this.healthTaxSevenSeventyFivePercent = healthTaxSevenSeventyFivePercent;
    }

    // Getter and setter for tax-deductible expenses
    public BigDecimal getTaxDeductibleExpenses() {
        return taxDeductibleExpenses;
    }

    public void setTaxDeductibleExpenses(BigDecimal taxDeductibleExpenses) {
        this.taxDeductibleExpenses = taxDeductibleExpenses;
    }

    // Getter and setter for advance tax
    public BigDecimal getAdvanceTax() {
        return advanceTax;
    }

    public void setAdvanceTax(BigDecimal advanceTax) {
        this.advanceTax = advanceTax;
    }

    // Getter and setter for net income
    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }
}
