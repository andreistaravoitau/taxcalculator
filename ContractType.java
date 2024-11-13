package com.bartoszwalter.students.taxes;

import java.util.HashMap;
import java.util.Map;

import static com.bartoszwalter.students.taxes.TaxConstants.*;

public class ContractType {
    public static final Map<String, String> CONTRACT_TYPES = new HashMap<>();

    static {
        CONTRACT_TYPES.put(CONTRACT_EMPLOYMENT_KEY, CONTRACT_EMPLOYMENT_DESC);
        CONTRACT_TYPES.put(CONTRACT_CIVIL_KEY, CONTRACT_CIVIL_DESC);
    }

    public static String getContractTypeDescription(String code) {
        return CONTRACT_TYPES.getOrDefault(code.toUpperCase(), "Unknown Type");
    }

}
