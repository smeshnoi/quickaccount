package controller;

import com.quickaccount.entity.ContractorIndividual;
import com.quickaccount.entity.ContractorLegalEntity;
import com.quickaccount.entity.User;
import com.quickaccount.service.ContractorService;
import com.quickaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ContractorController {
    private ContractorService contractorService;
    private UserService userService;

    @Autowired
    public ContractorController(ContractorService contractorService, UserService userService) {
        this.contractorService = contractorService;
        this.userService = userService;
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
    public String getContractorPage(Model model, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        model.addAttribute("contractors", contractorService.findContractorsByUser(userbyLogin));
        return "contractors";
    }

    @GetMapping("/addcontractor")
    public String showContractorLegalEntityPage() {
        return "contractors";
    }

    @PostMapping("/contractorlegalentity")
    public String addContractorLegalEntity(ContractorLegalEntity contractorLegalEntity, Principal principal) {
        contractorService.saveContractorLegalEntity(contractorLegalEntity, userService.getUserbyLogin(principal.getName()));
        return "contractors";
    }

    @PostMapping("/contractorindividual")
    public String addContractorIndividual(ContractorIndividual contractorIndividual, Principal principal) {
        contractorService.saveContractorIndividual(contractorIndividual, userService.getUserbyLogin(principal.getName()));
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
