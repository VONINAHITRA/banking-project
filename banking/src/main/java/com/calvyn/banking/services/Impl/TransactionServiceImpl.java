package com.calvyn.banking.services.Impl;

import com.calvyn.banking.dto.TransactionDto;
import com.calvyn.banking.models.Transaction;
import com.calvyn.banking.models.TransactionType;
import com.calvyn.banking.repositories.TransactionRepository;
import com.calvyn.banking.services.TransactionService;
import com.calvyn.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private ObjectsValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
         Transaction transaction = TransactionDto.toEntiry(dto);
        // transaction.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(transactionType(transaction.getType()))));
         BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionType(transaction.getType()));
         BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
         transaction.setAmount(amount);

        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDto::fromEntiry)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id)
                .map(TransactionDto::fromEntiry)
                .orElseThrow(()-> new EntityNotFoundException("Not Transation was found with ID" + id));
    }

    @Override
    public void delete(Integer id) {
       transactionRepository.deleteById(id);
    }

    private int getTransactionType(TransactionType type){
        return TransactionType.TRANSFERT == type ? 1 : -1;
    }
}
