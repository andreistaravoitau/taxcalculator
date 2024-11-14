package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class HealthTaxCalculator implements TaxComponentCalculator {
    private final BigDecimal rate;
    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);

    public HealthTaxCalculator(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public BigDecimal calculate(BigDecimal incomeAfterContributions) {
        return incomeAfterContributions.multiply(rate)
                .divide(BigDecimal.valueOf(100), MC);
    }
}
