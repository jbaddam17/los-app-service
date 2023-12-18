package com.loanorignx.app.dto.appreq;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class LOSAppRequestABC {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String dob;
    private String ssn;
    private BigDecimal annualIncome;
    private BigDecimal requestedAmount;
    private String residenceType;
    private String employmentType;
    private String loanPurpose;
    private Long applicationNumber;
    private String applicationDate;
}
