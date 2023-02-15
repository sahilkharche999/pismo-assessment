package com.pismo.assessment.controller;

import com.pismo.assessment.dto.TransactionRequestDto;
import com.pismo.assessment.dto.TransactionResponseDto;
import com.pismo.assessment.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Api(value = "Transactions API", tags = "Transactions API")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ApiOperation("Create Transaction")
    public ResponseEntity<TransactionResponseDto> save(@RequestBody TransactionRequestDto dto) {
        return ResponseEntity.status(201).body(transactionService.save(dto));
    }
}
