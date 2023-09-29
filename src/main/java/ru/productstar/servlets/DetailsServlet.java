package ru.productstar.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;

import java.io.IOException;
import java.util.List;

public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        context.log("[DetailsServlet] doGet");

        resp.getWriter().println("Expenses: ");
        for (Transaction expense : (List<Transaction>) context.getAttribute("expenses")) {
            resp.getWriter().println(String.format("- %s(%d)", expense.getName(), expense.getSum()));
        }
        resp.getWriter().println("\n");

        List<Transaction> incomes = (List<Transaction>) context.getAttribute("incomes");
        System.out.println(incomes);
        if (incomes != null) {
            resp.getWriter().println("Incomes: ");
            for (Transaction income : incomes) {
                resp.getWriter().println(String.format("- %s(%d)", income.getName(), income.getSum()));
            }
            resp.getWriter().println("\n");
        }
    }
}
