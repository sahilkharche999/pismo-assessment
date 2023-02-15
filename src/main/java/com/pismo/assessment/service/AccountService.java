package com.pismo.assessment.service;

import com.pismo.assessment.entity.Account;

public interface AccountService {

    Account save(Account account);

    Account getById(Integer id);
}
