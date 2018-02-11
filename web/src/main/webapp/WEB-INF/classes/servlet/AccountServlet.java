package servlet;

import com.quickaccount.AccountService;

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
        int count = AccountService.getInstance().getAll().size();
        String text = req.getParameter("findAccount");
        String typeAccount = req.getParameter("typeAccount");
        if ((text != null) || (typeAccount != null)) {
            int limitPage = Integer.parseInt(req.getParameter("limitPage"));
            int allPage =  (int)Math.ceil((double)count / limitPage);
            int page = 0;
            if(req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            } else {
                page = 1;
            }

            req.setAttribute("listAccount", AccountService.getInstance().getAllByParameter(text, limitPage, page, typeAccount));
            List<Integer> listPages = new ArrayList<>();
            count = AccountService.getInstance().getAllByParameter(text, limitPage, page, typeAccount).size();
            allPage =  (int)Math.ceil((double)count / limitPage);
            for(int i = 1; i <= allPage; i++) {
                listPages.add(i);
            }
            req.setAttribute("listPages", listPages);
        } else {
            req.setAttribute("listAccount", AccountService.getInstance().getAll());
        }

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(req,resp);
    }
}
