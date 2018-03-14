package controller;

import com.quickaccount.entity.ContractorIndividual;
import com.quickaccount.entity.ContractorLegalEntity;
import com.quickaccount.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContractorController {
    private ContractorService contractorService;

    @Autowired
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @ModelAttribute("contractorLegalEntity")
    public ContractorLegalEntity initContractorLegalEntity() {
        return new ContractorLegalEntity();
    }

    @ModelAttribute("contractorIndividual")
    public ContractorIndividual initContractorIndividual() {
        return new ContractorIndividual();
    }

    @GetMapping("/contractors")
    public String getContractorPage() {
        return "contractors";
    }

    @GetMapping("/addcontractor")
    public String showContractorLegalEntityPage() {
        return "contractors";
    }

    @PostMapping("/contractorlegalentity")
    public String addContractorLegalEntity(ContractorLegalEntity contractorLegalEntity) {
        return "contractors";
    }

    @PostMapping("/contractorindividual")
    public String addContractorIndividual(ContractorIndividual contractorIndividual) {
        return "contractors";
    }

    @PostMapping("/addcontractor")
    public String getAddContractorPage(@RequestParam String contractorOption) {
        if ("contractorlegalentity".equals(contractorOption)) {
            return "addcontractorlegalentity";
        } else if ("contractorindividual".equals(contractorOption)) {
            return "addcontractorindividual";
        }
        return "contractors";
    }
}
