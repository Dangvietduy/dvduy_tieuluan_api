package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.Company;
import com.web.internship_api.models.CompanyModel;

public interface CompanyService {
	List<Company> findAll();
	Optional<Company> findByCompanyId(int companyId);
	List<Company> searchCompany(CompanyModel model);
	Company createCompany(CompanyModel model);
	Company updateCompany(CompanyModel model);
	Company deleteCompany(int id);
}
