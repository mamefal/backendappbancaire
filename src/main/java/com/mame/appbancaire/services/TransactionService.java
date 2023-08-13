package com.mame.appbancaire.services;

import com.mame.appbancaire.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {

    List<TransactionDto> findAllByUserId(Integer userId);

    List<TransactionDto> findAllById(Integer userId);
}
