package com.quickaccount.service;

import com.quickaccount.entity.Contractor;
import com.quickaccount.entity.ContractorIndividual;
import com.quickaccount.entity.ContractorLegalEntity;

public interface ContractorService {
    Contractor saveContractorIndividual(ContractorIndividual contractorIndividual);
    Contractor saveContractorLegalEntity(ContractorLegalEntity contractorLegalEntity);
}
