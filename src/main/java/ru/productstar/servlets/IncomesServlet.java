package ru.productstar.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;
import ru.productstar.servlets.services.TransactionInMemory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/incomes/add")
public class IncomesServlet extends HttpServlet {
    private TransactionInMemory moneyTransaction;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        context.log("[IncomesServlet] init");

        List<Transaction> incomes = new ArrayList<>();
        context.setAttribute("incomes", incomes);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        context.log("[IncomesServlet] doGet");

        List<Transaction> incomes = new ArrayList<>((List) context.getAttribute("incomes"));
        int freeMoney = (int) context.getAttribute("freeMoney");
        moneyTransaction = new TransactionInMemory(incomes, freeMoney);

        incomes = moneyTransaction.incomes(req);
        freeMoney = moneyTransaction.getFreeMoney();

        context.setAttribute("freeMoney", freeMoney);
        context.setAttribute("incomes", incomes);
        context.log("[IncomesServlet] Incomes were added");

        req.getRequestDispatcher("/summary").forward(req, resp);

    }
}
