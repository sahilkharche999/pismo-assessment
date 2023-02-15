package com.pismo.assessment.service.serviceImpl;

import com.pismo.assessment.entity.Account;
import com.pismo.assessment.repository.AccountRepository;
import com.pismo.assessment.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    @Override
    public Account save(Account account) {
        if (account.getDocumentNumber() == null || account.getDocumentNumber().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid document number");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account getById(Integer id) {
        return accountRepository.findByAccountId(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }
}
