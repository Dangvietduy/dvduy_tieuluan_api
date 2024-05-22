package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Company;
import com.web.internship_api.entities.Teacher;
import com.web.internship_api.models.CompanyModel;
import com.web.internship_api.repositories.CompanyRepositories;
import com.web.internship_api.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyRepositories companyRepositories;


	@Override
	public Optional<Company> findByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		return companyRepositories.findById(companyId);
	}



	@Override
	public Company createCompany(CompanyModel model) {
		Optional<Company> optional = this.findByCompanyId(model.getId());
		if(optional.isEmpty()) {
			Company company = new Company();
			company.setAddress(model.getAddress());
			company.setEmail(model.getEmail());
			company.setIndustry(model.getIndustry());
			company.setName(model.getName());
			company.setPhone(model.getPhone());
			return companyRepositories.save(company);
		}
		return null;
	}



	@Override
	public Company updateCompany(CompanyModel model) {
		Optional<Company> optional = this.findByCompanyId(model.getId());
		if(optional.isPresent()) {
			Company company = optional.get();
			company.setAddress(model.getAddress());
			company.setEmail(model.getEmail());
			company.setIndustry(model.getIndustry());
			company.setName(model.getName());
			company.setPhone(model.getPhone());
			return companyRepositories.save(company);
		}
		return null;
	}



	@Override
	public Company deleteCompany(int id) {
		Optional<Company> company = companyRepositories.findById(id);
		if(company.isPresent()) {
			companyRepositories.delete(company.get());
			return company.get();
		}
		return null;
	}



	@Override
	public List<Company> findAll() {
		return companyRepositories.findAll();
	}



	@Override
	public List<Company> searchCompany(CompanyModel model) {
		List<Company> company = companyRepositories.searchCompany(model.getId(), model.getName());
		return company;
	}
}
