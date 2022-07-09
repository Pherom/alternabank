package com.alternabank.webapp.servlets.loan;

import com.alternabank.dto.loan.LoanDetails;
import com.alternabank.engine.customer.CustomerManager;
import com.alternabank.webapp.util.WebAppUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "BorrowerLoansServlet", urlPatterns = "/borrower-loans")
public class BorrowerLoansServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String username = WebAppUtils.getUsername(req);
        if (username != null) {
            CustomerManager customerManager = WebAppUtils.getCustomerManager(getServletContext());
            Set<LoanDetails> postedLoans;
            synchronized (getServletContext()) {
                postedLoans = customerManager.getCustomersByName().get(username).postedLoansToDTO();
            }
            String postedLoansJSON = WebAppUtils.GSON_INSTANCE.toJson(postedLoans);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().print(postedLoansJSON);
        }
        else resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
