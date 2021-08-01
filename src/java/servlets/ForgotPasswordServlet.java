package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("forgot_email");

        AccountService as = new AccountService();
        String path = getServletContext().getRealPath("/WEB-INF");
        boolean result = as.forgotPassword(email, path);

        if (result) {
            request.setAttribute("userMessage", "Password recovery successful!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else if (!result) {
            request.setAttribute("userMessage", "Password recovery failed, try again");
            getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
        }
    }

}
