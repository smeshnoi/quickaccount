package servlet;

import com.quickaccount.CurrencyService;
import config.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/currency", name = "currency")
public class CurrencyServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrencyService currencyService = ApplicationContextHolder.getBean(CurrencyService.class);
        req.setAttribute("currency", currencyService.getAll());
        //currencyService.getAll();
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/currency.jsp").forward(req,resp);
    }
}
