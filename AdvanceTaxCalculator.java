package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class AdvanceTaxCalculator implements TaxComponentCalculator {
    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);

    @Override
    public BigDecimal calculate(BigDecimal taxableIncome) {
        return taxableIncome.multiply(TaxConstants.TAX_PERCENTAGE)
                .divide(BigDecimal.valueOf(100), MC);
    }
}
