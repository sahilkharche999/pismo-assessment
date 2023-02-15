package com.pismo.assessment.service.serviceImpl;

import com.pismo.assessment.dto.TransactionRequestDto;
import com.pismo.assessment.entity.Account;
import com.pismo.assessment.repository.AccountRepository;
import com.pismo.assessment.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void testSave() {
        Account account = new Account();
        account.setAccountId(3);
        account.setDocumentNumber("42");
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findByAccountId(anyInt())).thenReturn(ofResult);
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setAccountId(3);
        transactionRequestDto.setAmount(BigDecimal.valueOf(42L));
        transactionRequestDto.setOperationTypeId(1);

//        TransactionResponseDto

//        assertSame()
    }

    @Test
    void testSavex() {
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
    void testSave1() {
        Account account = mock(Account.class);
        doNothing().when(account).setAccountId(anyInt());
        doNothing().when(account).setDocumentNumber((String) any());
        account.setAccountId(3);
        account.setDocumentNumber("42");
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findByAccountId(anyInt())).thenReturn(ofResult);

        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setAccountId(3);
        transactionRequestDto.setAmount(BigDecimal.valueOf(42L));
        transactionRequestDto.setOperationTypeId(123);
        assertThrows(ResponseStatusException.class, () -> transactionServiceImpl.save(transactionRequestDto));
//        verify(accountRepository).findByAccountId(anyInt());
//        verify(account).setAccountId(anyInt());
//        verify(account).setDocumentNumber((String) any());
    }

    @Test
    void testSave2() {
        when(accountRepository.findByAccountId(anyInt())).thenReturn(Optional.empty());
        Account account = mock(Account.class);
        doNothing().when(account).setAccountId(anyInt());
        doNothing().when(account).setDocumentNumber((String) any());
        account.setAccountId(3);
        account.setDocumentNumber("42");

        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setAccountId(3);
        transactionRequestDto.setAmount(BigDecimal.valueOf(42L));
        transactionRequestDto.setOperationTypeId(123);
        assertThrows(ResponseStatusException.class, () -> transactionServiceImpl.save(transactionRequestDto));
        verify(accountRepository).findByAccountId(anyInt());
        verify(account).setAccountId(anyInt());
        verify(account).setDocumentNumber((String) any());
    }
}

