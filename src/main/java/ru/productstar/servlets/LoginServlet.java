package ru.productstar.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final String passwd = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        context.log("[LoginServlet] doPost");

        String reqPasswd = req.getParameter("password");
        if (passwd.equals(reqPasswd)) {
            context.log("[LoginServlet] password id correct");
            HttpSession session = req.getSession();
            session.setAttribute("userLogin", true);
            session.setMaxInactiveInterval(30);
            resp.sendRedirect("/summary");
        } else {
            resp.getWriter().println("Wrong password");
        }
    }
}
