package com.calvyn.banking.services.Impl;

import com.calvyn.banking.dto.AccountDto;
import com.calvyn.banking.models.Account;
import com.calvyn.banking.repositories.AccountRepository;
import com.calvyn.banking.services.AccountService;
import com.calvyn.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        //block a count update -> iban cannot be changed
/*            throw new OperationNonPermittedException(
                 "Account cannot be updated",
                  "Save account",
                  "Account",
                  "Update not permetted"
            );
        }
           validator.validate(dto);
           Account account = AccountDto.toEntity(dto);
           account.setIban(generateRadomIban());
        return account.getId(); */

        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        if(dto.getId() == null){
            account.setIban(generateRadomIban());
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {

        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No account wa found with ID:" + id));
    }

    @Override
    public void delete(Integer id) {
     accountRepository.deleteById(id);
    }

    private String generateRadomIban(){
        //todo generate iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        //check if the iban aleredy exists
       boolean ibanExists = accountRepository.findByIban(iban).isPresent();
       if(ibanExists){
          generateRadomIban();
       }
        return iban;
    }
}
