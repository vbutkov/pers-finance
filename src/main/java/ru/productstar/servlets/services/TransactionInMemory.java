package ru.productstar.servlets.services;

import jakarta.servlet.http.HttpServletRequest;
import ru.productstar.servlets.model.Transaction;

import java.util.List;

public class TransactionInMemory {
    List<Transaction> historyMoveMoney;
    private int freeMoney;

    public int getFreeMoney() {
        return freeMoney;
    }

    public TransactionInMemory(List<Transaction> historyMoveMoney, int freeMoney) {
        this.historyMoveMoney = historyMoveMoney;
        this.freeMoney = freeMoney;
    }


    public List<Transaction> expense(HttpServletRequest req) {
        int summa = transaction(req);
        freeMoney -= summa;

        return historyMoveMoney;
    }

    public List<Transaction> incomes(HttpServletRequest req) {
        int summa = transaction(req);
        freeMoney += summa;

        return historyMoveMoney;
    }

    private int transaction(HttpServletRequest req) {
        int summa = 0;
        for (var key : req.getParameterMap().keySet()) {
            int value = Integer.parseInt(req.getParameter(key));
            if (value > 0) {
                summa += value;
                historyMoveMoney.add(new Transaction(key, value));
            }
        }

        return summa;
    }
}
