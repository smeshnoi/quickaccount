package com.quickaccount.service;

import com.quickaccount.entity.TypeAccount;
import com.quickaccount.repository.TypeAccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeAccountServiceImpl implements TypeAccountService {

    private final TypeAccountRepository typeAccountRepository;

    public TypeAccountServiceImpl(TypeAccountRepository typeAccountRepository) {
        this.typeAccountRepository = typeAccountRepository;
    }

    @Override
    public List<TypeAccount> findAll() {
        return typeAccountRepository.findAll();
    }
}
