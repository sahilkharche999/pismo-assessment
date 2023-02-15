package com.pismo.assessment.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pismo.assessment.entity.Account;
import com.pismo.assessment.repository.AccountRepository;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Mock
    Account account;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void testSave() {
        Account account = new Account();
        account.setAccountId(3);
        account.setDocumentNumber("42");
        when(accountRepository.save((Account) any())).thenReturn(account);
        assertSame(account, accountServiceImpl.save(account));
        assertEquals(3, account.getAccountId());
        assertEquals("42", account.getDocumentNumber());
    }

    @Test
    void testSave2() {
        Account account = new Account();
        account.setAccountId(3);
        Exception exception = assertThrows(ResponseStatusException.class, () -> accountServiceImpl.save(account));
        assertTrue(exception.getMessage().contains("Invalid document number"));
    }

    @Test
    void testGetById() {
        Account account = new Account();
        account.setAccountId(3);
        account.setDocumentNumber("42");
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findByAccountId(anyInt())).thenReturn(ofResult);
        assertSame(account, accountServiceImpl.getById(1));
    }

    @Test
    void testGetById2() {
        when(accountRepository.findByAccountId(anyInt())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> accountServiceImpl.getById(1));
        assertTrue(exception.getMessage().contains("Account not found"));
    }

}

