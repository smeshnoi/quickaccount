package controller;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.User;
import com.quickaccount.service.CompanyService;
import com.quickaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CompanyController {
    private CompanyService companyService;

    private UserService userService;

    @Autowired
    public CompanyController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @ModelAttribute("company")
    public Company initCompany() {
        return new Company();
    }

    @GetMapping(value = "/addcompany")
    public String showAddCompanyPage() {
        return "addcompany";
    }


    @GetMapping(value = "/companies")
    public String showCompaniesPage(Model model, Principal principal) {
        User user = userService.getUserbyLogin(principal.getName());
        model.addAttribute("companies", companyService.findAllByUserCompany(user));
        return "company";
    }

    @PostMapping(value = "/companies")
    public String addCompany(Company company, User user, Authentication authentication, Principal principal) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserbyLogin(principal.getName());
        company.setUserCompany(user);
        companyService.save(company);
        return "company";
    }

    @GetMapping(value = "/editcompany/{id}")
    public String showEditCompanyPage(Model model, Company company, @PathVariable("id") String id) {
        Long companyId = Long.parseLong(id);
        model.addAttribute("company", companyService.findCompanyById(companyId));
        return "editcompany";
    }

    @PostMapping(value = "/editcompany/{id}")
    public String editCompany(Model model, Company company, @PathVariable("id") String id) {
        //System.out.println(company + " " + id);
        Long companyId = Long.parseLong(id);
        companyService.update(company, companyId);
        return "editcompany";
    }
}
