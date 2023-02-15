package com.pismo.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class TransactionRequestDto implements Serializable {

    @JsonProperty("account_id")
    private Integer accountId;

    @JsonProperty("operation_type_id")
    private Integer operationTypeId;

    private BigDecimal amount;
}