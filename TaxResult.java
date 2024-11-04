package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;

public class TaxResult {
    private BigDecimal socialSecurityTax;
    private BigDecimal healthSecurityTax;
    private BigDecimal sicknessSecurityTax;
    private BigDecimal healthTaxNinePercent;
    private BigDecimal healthTaxSevenSeventyFivePercent;
    private BigDecimal taxDeductibleExpenses;
    private BigDecimal advanceTax;
    private BigDecimal netIncome;

    public BigDecimal getSocialSecurityTax() {
        return socialSecurityTax;
    }

    public void setSocialSecurityTax(BigDecimal socialSecurityTax) {
        this.socialSecurityTax = socialSecurityTax;
    }

    public BigDecimal getHealthSecurityTax() {
        return healthSecurityTax;
    }

    public void setHealthSecurityTax(BigDecimal healthSecurityTax) {
        this.healthSecurityTax = healthSecurityTax;
    }

    public BigDecimal getSicknessSecurityTax() {
        return sicknessSecurityTax;
    }

    public void setSicknessSecurityTax(BigDecimal sicknessSecurityTax) {
        this.sicknessSecurityTax = sicknessSecurityTax;
    }

    public BigDecimal getHealthTaxNinePercent() {
        return healthTaxNinePercent;
    }

    public void setHealthTaxNinePercent(BigDecimal healthTaxNinePercent) {
        this.healthTaxNinePercent = healthTaxNinePercent;
    }

    public BigDecimal getHealthTaxSevenSeventyFivePercent() {
        return healthTaxSevenSeventyFivePercent;
    }

    public void setHealthTaxSevenSeventyFivePercent(BigDecimal healthTaxSevenSeventyFivePercent) {
        this.healthTaxSevenSeventyFivePercent = healthTaxSevenSeventyFivePercent;
    }

    public BigDecimal getTaxDeductibleExpenses() {
        return taxDeductibleExpenses;
    }

    public void setTaxDeductibleExpenses(BigDecimal taxDeductibleExpenses) {
        this.taxDeductibleExpenses = taxDeductibleExpenses;
    }

    public BigDecimal getAdvanceTax() {
        return advanceTax;
    }

    public void setAdvanceTax(BigDecimal advanceTax) {
        this.advanceTax = advanceTax;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }
}
