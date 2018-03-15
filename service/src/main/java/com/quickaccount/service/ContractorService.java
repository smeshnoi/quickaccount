package com.quickaccount.service;

import com.quickaccount.entity.Contractor;
import com.quickaccount.entity.ContractorIndividual;
import com.quickaccount.entity.ContractorLegalEntity;
import com.quickaccount.entity.User;

import java.util.List;

public interface ContractorService {
    Contractor saveContractorIndividual(ContractorIndividual contractorIndividual, User user);
    Contractor saveContractorLegalEntity(ContractorLegalEntity contractorLegalEntity, User user);
    List<Contractor> findContractorsByUser(Contractor contractor, User user);
}
