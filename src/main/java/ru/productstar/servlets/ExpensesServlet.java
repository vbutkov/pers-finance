package ru.productstar.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;
import ru.productstar.servlets.services.TransactionInMemory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ExpensesServlet extends HttpServlet {

    private TransactionInMemory moneyTransaction;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        context.log("[ExpensesServlet] doGet");

        List<Transaction> expenses = new LinkedList<>((List) context.getAttribute("expenses"));
        int freeMoney = (int) context.getAttribute("freeMoney");
        moneyTransaction = new TransactionInMemory(expenses, freeMoney);

        expenses = moneyTransaction.expense(req);
        freeMoney = moneyTransaction.getFreeMoney();

        context.setAttribute("freeMoney", freeMoney);
        context.setAttribute("expenses", expenses);
        context.log("[ExpensesServlet] Expenses were added");

        req.getRequestDispatcher("/summary").forward(req, resp);

    }
}

