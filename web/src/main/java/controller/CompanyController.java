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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@SessionAttributes("currentCompany")
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
    public String addCompany(@Valid Company company, User user, Authentication authentication, Principal principal, Model model, Errors error) {
        if (error.getErrorCount() == 0) {
            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userService.getUserbyLogin(principal.getName());
            company.setUserCompany(user);
            companyService.save(company);
            return "redirect:companies";
        } else {
            //model.addAttribute("error", error.getFieldValue("companyName"));
            System.out.println(error.getErrorCount());
            //model.addAttribute("company", company);
            return "addcompany";
        }
    }

    @GetMapping(value = "/editcompany/{id}")
    public String showEditCompanyPage(Model model, Company company, @PathVariable("id") String id) {
        Long companyId = Long.parseLong(id);
        model.addAttribute("company", companyService.findCompanyById(companyId));
        model.addAttribute("currentCompany", companyService.findCompanyById(companyId));
        return "editcompany";
    }

    @PostMapping(value = "/editcompany/{id}")
    public String editCompany(Model model, Company company, @SessionAttribute("currentCompany") Company currentCompany, @PathVariable("id") Long id) {
        currentCompany.setId(id);
        currentCompany.setCompanyName(company.getCompanyName());
        currentCompany.setDescription(company.getDescription());
        currentCompany.setContact(company.getContact());
        companyService.update(currentCompany, id);
        return "editcompany";
    }
}
