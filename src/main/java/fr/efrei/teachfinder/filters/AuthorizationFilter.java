package fr.efrei.teachfinder.filters;

import fr.efrei.teachfinder.entities.SessionUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    public static final Logger log = LogManager.getLogger(AuthorizationFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean redirectLogin = false;

        String path = httpRequest.getServletPath();
        String method = httpRequest.getMethod();

        log.info("HTTP request. SERVLET: " + path + ";  METHOD: " + method + "\n");

        if (session != null) {
            SessionUser user = (SessionUser) session.getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                redirectLogin = true;
            }
        } else {
            redirectLogin = true;
        }

        if (redirectLogin) {
            httpResponse.sendRedirect("/TeachFinder/login");
        }
    }
}
