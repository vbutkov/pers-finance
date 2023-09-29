package ru.productstar.servlets.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    ServletContext context;
    private static final Set<String> PUBLIC_PAGE = Set.of("/index.jsp", "/login");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        context = request.getServletContext();
        context.log("[AuthorizedFilter] doFilter");

        String page = ((HttpServletRequest) request).getRequestURI();
        if (isPublicPage(page) || isAuthorization(request)) {
            chain.doFilter(request, response);
        } else {
            context.log("[AuthorizedFilter] redirect to /index.jsp");
            ((HttpServletResponse) response).sendRedirect("/index.jsp");
        }
    }

    private boolean isAuthorization(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) (request)).getSession();
        Object userLogin = session.getAttribute("userLogin");
        context.log("[AuthorizedFilter] Session: " + session);
        return userLogin != null;
    }

    private boolean isPublicPage(String page) {
        return PUBLIC_PAGE.stream().anyMatch(page::startsWith);
    }
}
