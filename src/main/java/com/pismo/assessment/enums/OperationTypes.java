package com.pismo.assessment.enums;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public enum OperationTypes {
    PURCHASE(1), INSTALLMENT_PURCHASE(2), WITHDRAWAL(3), PAYMENT(4);

    private final int id;

    OperationTypes(int id) {
        this.id = id;
    }

    public static final Set<OperationTypes> DEBT_OPERATION_TYPES = Set.of(PURCHASE, INSTALLMENT_PURCHASE, WITHDRAWAL);

    public static OperationTypes of(int id) {
        for (OperationTypes ot : OperationTypes.values()) {
            if (ot.getId() == id) {
                return ot;
            }
        }
        return null;
    }
}
