package servlet;

import com.quickaccount.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/message", name = "message")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setAttribute("message", MessageService.getInstance().generateMessage());
        System.out.println(MessageService.getInstance().generateMessage());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(req, resp);
    }
}