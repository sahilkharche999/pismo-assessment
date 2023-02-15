package com.pismo.assessment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.assessment.enums.OperationTypes;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("transaction_id")
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonProperty("operation_type_id")
    @Enumerated(EnumType.ORDINAL)
    private OperationTypes operationTypeId;

    private BigDecimal amount;

    @CreationTimestamp
    private LocalDateTime eventDate;

}
