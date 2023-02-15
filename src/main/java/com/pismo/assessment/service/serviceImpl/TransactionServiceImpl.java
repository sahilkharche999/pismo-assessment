package com.pismo.assessment.service.serviceImpl;

import com.pismo.assessment.dto.TransactionRequestDto;
import com.pismo.assessment.dto.TransactionResponseDto;
import com.pismo.assessment.entity.Transaction;
import com.pismo.assessment.enums.OperationTypes;
import com.pismo.assessment.repository.AccountRepository;
import com.pismo.assessment.repository.TransactionRepository;
import com.pismo.assessment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public TransactionResponseDto save(TransactionRequestDto transactionRequestDto) {
        var acc = accountRepository.findByAccountId(transactionRequestDto.getAccountId());
        if (acc == null || acc.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account id invalid or not found");
        }
        OperationTypes operationType = OperationTypes.of(transactionRequestDto.getOperationTypeId());
        if (operationType == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operation Type Id invalid or not found");
        }
        Transaction transaction = Transaction.builder().account(acc.get())
                .operationTypeId(operationType)
                .build();
        if (OperationTypes.DEBT_OPERATION_TYPES.contains(operationType)) {
            transaction.setAmount(transactionRequestDto.getAmount().multiply(new BigDecimal(-1)));
        } else {
            transaction.setAmount(transactionRequestDto.getAmount());
        }
        Transaction res = transactionRepository.save(transaction);
        return TransactionResponseDto.builder().transactionId(res.getTransactionId())
                .accountId(res.getAccount().getAccountId()).operationTypeId(res.getOperationTypeId().getId()).amount(res.getAmount()).build();
    }
}
