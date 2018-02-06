package servlet;

import com.quickaccount.CurrencyService;

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
        req.setAttribute("currency", CurrencyService.getInstance().getAllCurrency());
        //System.out.println(CurrencyService.getInstance().getAllCurrency());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/currency.jsp").forward(req,resp);
    }
}
