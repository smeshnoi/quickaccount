package com.quickaccount.service;

import com.quickaccount.entity.Contractor;
import com.quickaccount.entity.ContractorIndividual;
import com.quickaccount.entity.ContractorLegalEntity;
import com.quickaccount.entity.User;
import com.quickaccount.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContractorServiceImpl implements ContractorService {
    private final ContractorRepository contractorRepository;

    @Autowired
    public ContractorServiceImpl(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Contractor saveContractorIndividual(ContractorIndividual contractorIndividual, User user) {
        contractorIndividual.setTypeContractor("INDIVIDUAL");
        System.out.println(contractorIndividual);
        contractorIndividual.setUserContractor(user);
        contractorRepository.save(contractorIndividual);
        return null;
    }

    @Override
    public Contractor saveContractorLegalEntity(ContractorLegalEntity contractorLegalEntity, User user) {
        contractorLegalEntity.setTypeContractor("COMPANY");
        contractorLegalEntity.setUserContractor(user);
        contractorRepository.save(contractorLegalEntity);
        return null;
    }

    @Override
    public List<Contractor> findContractorsByUser(Contractor contractor, User user) {
        contractorRepository.findAllByUserContractorOrderByContractorName(user);
        return null;
    }
}
