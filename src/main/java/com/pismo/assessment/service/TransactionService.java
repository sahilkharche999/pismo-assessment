package com.pismo.assessment.service;

import com.pismo.assessment.dto.TransactionRequestDto;
import com.pismo.assessment.dto.TransactionResponseDto;
import com.pismo.assessment.entity.Transaction;

public interface TransactionService {
    TransactionResponseDto save(TransactionRequestDto transaction);
}
