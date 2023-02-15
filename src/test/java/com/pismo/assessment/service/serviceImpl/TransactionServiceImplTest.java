package com.pismo.assessment.service.serviceImpl;

import com.pismo.assessment.dto.TransactionRequestDto;
import com.pismo.assessment.dto.TransactionResponseDto;
import com.pismo.assessment.entity.Account;
import com.pismo.assessment.entity.Transaction;
import com.pismo.assessment.enums.OperationTypes;
import com.pismo.assessment.repository.AccountRepository;
import com.pismo.assessment.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TransactionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Test
    void saveTestOperationTypeNotPayment() {
        Account account = new Account();
        account.setAccountId(3);
        account.setDocumentNumber("42");
        Optional<Account> ofResult = Optional.of(account);

        Transaction transaction = Transaction.builder()
                .transactionId(1)
                .account(account)
                .operationTypeId(OperationTypes.of(1))
                .amount(BigDecimal.valueOf(-42L))
                .build();

        when(accountRepository.findByAccountId(anyInt())).thenReturn(ofResult);
        when(transactionRepository.save(any())).thenReturn(transaction);

        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setAccountId(3);
        transactionRequestDto.setAmount(BigDecimal.valueOf(42L));
        transactionRequestDto.setOperationTypeId(1);

        TransactionResponseDto expectedResponse = TransactionResponseDto.builder()
                .transactionId(1)
                .operationTypeId(1)
                .accountId(3)
                .amount(BigDecimal.valueOf(-42L))
                .build();

        TransactionResponseDto actualResponse = transactionServiceImpl.save(transactionRequestDto);

        assertEquals(expectedResponse.getTransactionId(), actualResponse.getTransactionId());
        assertEquals(expectedResponse.getAccountId(), actualResponse.getAccountId());
        assertEquals(expectedResponse.getAmount(), actualResponse.getAmount());
        assertEquals(expectedResponse.getOperationTypeId(), actualResponse.getOperationTypeId());
    }

    @Test
    void saveTestOperationTypePayment() {
        Account account = new Account();
        account.setAccountId(3);
        account.setDocumentNumber("42");
        Optional<Account> ofResult = Optional.of(account);

        Transaction transaction = Transaction.builder()
                .transactionId(1)
                .account(account)
                .operationTypeId(OperationTypes.of(4))
                .amount(BigDecimal.valueOf(42L))
                .build();

        when(accountRepository.findByAccountId(anyInt())).thenReturn(ofResult);
        when(transactionRepository.save(any())).thenReturn(transaction);

        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setAccountId(3);
        transactionRequestDto.setAmount(BigDecimal.valueOf(42L));
        transactionRequestDto.setOperationTypeId(4);

        TransactionResponseDto expectedResponse = TransactionResponseDto.builder()
                .transactionId(1)
                .operationTypeId(4)
                .accountId(3)
                .amount(BigDecimal.valueOf(42L))
                .build();

        TransactionResponseDto actualResponse = transactionServiceImpl.save(transactionRequestDto);

        assertEquals(expectedResponse.getTransactionId(), actualResponse.getTransactionId());
        assertEquals(expectedResponse.getAccountId(), actualResponse.getAccountId());
        assertEquals(expectedResponse.getAmount(), actualResponse.getAmount());
        assertEquals(expectedResponse.getOperationTypeId(), actualResponse.getOperationTypeId());
    }

    @Test
    void saveTestExceptionInvalidOperationType() {
        Account account = new Account();
        account.setAccountId(3);
        account.setDocumentNumber("42");
        Optional<Account> ofResult = Optional.of(account);

        when(accountRepository.findByAccountId(anyInt())).thenReturn(ofResult);

        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setAccountId(3);
        transactionRequestDto.setAmount(BigDecimal.valueOf(42L));
        transactionRequestDto.setOperationTypeId(123);

        Exception exception = assertThrows(ResponseStatusException.class, () -> transactionServiceImpl.save(transactionRequestDto));

        assertTrue(exception.getMessage().contains("Operation Type Id invalid or not found"));
    }

    @Test
    void saveTestExceptionInvalidAccountId() {
        when(accountRepository.findByAccountId(anyInt())).thenReturn(null);

        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setAccountId(3);
        transactionRequestDto.setAmount(BigDecimal.valueOf(42L));
        transactionRequestDto.setOperationTypeId(1);

        Exception exception = assertThrows(ResponseStatusException.class, () -> transactionServiceImpl.save(transactionRequestDto));

        assertTrue(exception.getMessage().contains("Account id invalid or not found"));
    }

}

