package com.pismo.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionResponseDto {
    @JsonProperty("transaction_id")
    private Integer transactionId;

    @JsonProperty("account_id")
    private Integer accountId;

    @JsonProperty("operation_type_id")
    private Integer operationTypeId;

    private BigDecimal amount;
}
