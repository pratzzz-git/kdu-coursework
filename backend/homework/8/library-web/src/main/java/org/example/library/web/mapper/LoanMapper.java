package org.example.library.web.mapper;

import org.example.library.api.dto.response.LoanResponse;
import org.example.library.domain.entity.Loan;

public final class LoanMapper {

    private LoanMapper() {}

    public static LoanResponse toResponse(Loan loan) {
        LoanResponse response = new LoanResponse();
        response.setLoanId(loan.getId());
        response.setBookId(loan.getBook().getId());
        response.setBorrowedAt(loan.getBorrowedAt());
        response.setReturnedAt(loan.getReturnedAt());
        return response;
    }
}
