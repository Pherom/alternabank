package com.alternabank.engine.customer.dto;

import com.alternabank.engine.account.dto.AccountDetails;
import com.alternabank.engine.customer.CustomerManager;
import com.alternabank.engine.loan.LoanManager;
import com.alternabank.engine.loan.dto.LoanDetails;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerDetails {

    private final String customerAsString;
    private final String name;
    private final AccountDetails accountDetails;
    private final Set<LoanDetails> postedLoanDetails;
    private final Set<LoanDetails> investedLoanDetails;

    public CustomerDetails(CustomerManager.Customer customer) {
        customerAsString = customer.toString();
        name = customer.getName();
        accountDetails = customer.getAccount().toAccountDetails();
        postedLoanDetails = customer.getPostedLoansIDs().stream().map(loanID -> LoanManager.getInstance().getLoan(loanID).toLoanDetails()).collect(Collectors.toSet());
        investedLoanDetails = customer.getInvestedLoansIDs().stream().map(loanID -> LoanManager.getInstance().getLoan(loanID).toLoanDetails()).collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public Set<LoanDetails> getPostedLoanDetails() {
        return Collections.unmodifiableSet(postedLoanDetails);
    }

    public Set<LoanDetails> getInvestedLoanDetails() {
        return Collections.unmodifiableSet(investedLoanDetails);
    }

    @Override
    public String toString() {
        return customerAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDetails that = (CustomerDetails) o;
        return Objects.equals(customerAsString, that.customerAsString) && Objects.equals(name, that.name) && Objects.equals(accountDetails, that.accountDetails) && Objects.equals(postedLoanDetails, that.postedLoanDetails) && Objects.equals(investedLoanDetails, that.investedLoanDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerAsString, name, accountDetails, postedLoanDetails, investedLoanDetails);
    }
}