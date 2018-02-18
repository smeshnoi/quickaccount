package servlet;

import com.quickaccount.AccountService;
import com.quickaccount.entity.Account;
import config.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/account", name = "account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountService accountServiceImpl = ApplicationContextHolder.getBean(AccountService.class);
        int count = accountServiceImpl.getAll().size();
        String text = req.getParameter("findAccount");
        String typeAccount = req.getParameter("typeAccount");
        if ((text != null) || (typeAccount != null)) {
            int limitPage = Integer.parseInt(req.getParameter("limitPage"));

            int page = 0;
            if(req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            } else {
                page = 1;
            }
            List<Account> allByParameter = accountServiceImpl.getAllByParameter(text, limitPage, page, typeAccount);

            int allPage =  (int)Math.ceil((double)count / limitPage);

            List<Integer> listPages = new ArrayList<>();

            for(int i = 1; i <= allPage; i++) {
                listPages.add(i);
            }
            req.setAttribute("text", text);
            req.setAttribute("listPages", listPages);
            req.setAttribute("listAccount", allByParameter);
        } else {
            req.setAttribute("listAccount", accountServiceImpl.getAll());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(req,resp);
    }
}
