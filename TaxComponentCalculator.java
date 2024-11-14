package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;

public interface TaxComponentCalculator {
    BigDecimal calculate(BigDecimal income);
}
