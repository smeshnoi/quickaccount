package com.quickaccount.service;

import com.quickaccount.entity.Contractor;
import com.quickaccount.entity.ContractorIndividual;
import com.quickaccount.entity.ContractorLegalEntity;
import com.quickaccount.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContractorServiceImpl implements ContractorService {
    private final ContractorRepository contractorRepository;

    @Autowired
    public ContractorServiceImpl(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Contractor saveContractorIndividual(ContractorIndividual contractorIndividual) {
        contractorIndividual.setTypeContractor("INDIVIDUAL");

        System.out.println(contractorIndividual);
        //contractorRepository.save(contractorIndividual);
        return null;
    }

    @Override
    public Contractor saveContractorLegalEntity(ContractorLegalEntity contractorLegalEntity) {
        contractorLegalEntity.setTypeContractor("COMPANY");
        return null;
    }
}
