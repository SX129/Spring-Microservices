package com.microservice.companyms.company;

import com.microservice.companyms.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    void addCompany(Company company);
    boolean updateCompany(Long id, Company company);
    boolean deleteCompany(Long id);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
