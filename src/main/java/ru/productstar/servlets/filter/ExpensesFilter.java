package ru.productstar.servlets.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class ExpensesFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        ServletContext context = request.getServletContext();
        context.log("[ExpensesFilter] doFilter");

        int freeMoney = (int) context.getAttribute("freeMoney");
        for (var k : request.getParameterMap().keySet()) {
            freeMoney -= Integer.parseInt(request.getParameter(k));
            if (freeMoney < 0) {
                response.getWriter().println("Not enough money");
                return;
            }
        }

        filter.doFilter(request, response);
    }
}
